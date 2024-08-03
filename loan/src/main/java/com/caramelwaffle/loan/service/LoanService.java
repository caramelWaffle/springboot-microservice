package com.caramelwaffle.loan.service;

import com.caramelwaffle.loan.model.LoanData;

public interface LoanService {
    public void createLoan(LoanData loanData);

    LoanData findLoan(Long loanId);

    LoanData findLoanByMobileNumber(String mobileNumber);

    boolean updateLoan(LoanData loanData);

    boolean deleteLoan(Long loanId);
}
