package com.caramelwaffle.loan.entity;

import com.caramelwaffle.loan.model.LoanData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "loan")
public class LoanEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private int totalLoan;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "outstanding_amount")
    private int outstandingAmount;

    public LoanData toData(LoanData entry){
        entry.setLoanId(loanId);
        entry.setMobileNumber(mobileNumber);
        entry.setLoanType(loanType);
        entry.setTotalLoan(totalLoan);
        entry.setAmountPaid(amountPaid);
        entry.setOutstandingAmount(outstandingAmount);
        entry.setLoanNumber(loanNumber);
        return entry;
    }
}
