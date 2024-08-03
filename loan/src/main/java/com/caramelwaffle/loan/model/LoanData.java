package com.caramelwaffle.loan.model;

import com.caramelwaffle.loan.entity.LoanEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(
        name = "CardData",
        description = "Schema to hold card information"
)
public class LoanData {
    private Long loanId;

    @Schema(description = "Mobile Number of the customer", example = "9345432123")
    @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Loan Number of the customer")
    @Pattern(regexp="(^$|\\d{12})",message = "Loan number must be 12 digits")
    @NotEmpty
    private String loanNumber;

    @Schema(description = "Type of the loan")
    @NotEmpty(message = "LoanType can not be a null or empty")
    private String loanType;

    @PositiveOrZero(message = "Total loan amount should be equal or greater than zero")
    @Schema(
            description = "Total loan available against a card", example = "100000"
    )
    private int totalLoan;

    @PositiveOrZero(message = "Amount paid should be equal or greater than zero")
    @Schema(
            description = "Amount paid available against a card", example = "100000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding amount should be equal or greater than zero")
    @Schema(
            description = "Total loan available against a card", example = "100000"
    )
    private int outstandingAmount;

    public LoanEntity toEntity(LoanEntity entry) {
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
