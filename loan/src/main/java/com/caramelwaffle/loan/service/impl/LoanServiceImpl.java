package com.caramelwaffle.loan.service.impl;

import com.caramelwaffle.loan.entity.LoanEntity;
import com.caramelwaffle.loan.exception.LoanMobileNumberAlreadyExistException;
import com.caramelwaffle.loan.exception.ResourceNotFoundException;
import com.caramelwaffle.loan.model.LoanData;
import com.caramelwaffle.loan.repository.LoanRepository;
import com.caramelwaffle.loan.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    @Override
    public void createLoan(LoanData loanData) {
        Optional<LoanEntity> existedMobileNumberLoan = loanRepository.findByMobileNumber(loanData.getMobileNumber());
        if (existedMobileNumberLoan.isPresent()) {
            throw new LoanMobileNumberAlreadyExistException("Loan already registered with given mobileNumber " +
                    existedMobileNumberLoan.get().getMobileNumber());
        }
        loanRepository.save(loanData.toEntity(new LoanEntity()));
    }

    @Override
    public LoanData findLoan(Long loanId) {
        LoanEntity entity = loanRepository.findById(loanId).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loanId", loanId.toString())
        );
        return entity.toData(new LoanData());
    }

    @Override
    public LoanData findLoanByMobileNumber(String mobileNumber) {
        LoanEntity entity = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Mobile number", mobileNumber)
        );
        return entity.toData(new LoanData());
    }

    @Override
    public boolean updateLoan(LoanData loanData) {
        LoanEntity entity = loanRepository.findById(loanData.getLoanId()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loanData", loanData.getLoanId().toString())
        );
        LoanEntity updatedEntity = loanData.toEntity(entity);
        loanRepository.save(updatedEntity);
        return true;
    }

    @Override
    public boolean deleteLoan(Long loanId) {
        LoanEntity entity = loanRepository.findById(loanId).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanId", loanId.toString())
        );
        loanRepository.delete(entity);
        return true;
    }
}
