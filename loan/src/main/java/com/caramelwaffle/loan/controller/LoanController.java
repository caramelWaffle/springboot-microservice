package com.caramelwaffle.loan.controller;

import com.caramelwaffle.loan.model.LoanData;
import com.caramelwaffle.loan.model.ResponseData;
import com.caramelwaffle.loan.service.LoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoanController {
    private LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@Valid @RequestBody LoanData loanData) {
        loanService.createLoan(loanData);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseData(
                        String.valueOf(HttpStatus.CREATED.value()),
                        HttpStatus.CREATED.getReasonPhrase()
                ));
    }

    @GetMapping("/find")
    public ResponseEntity<LoanData> find(@RequestParam Long id) {
        LoanData loanData = loanService.findLoan(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanData);
    }

    @GetMapping("/findByMobileNumber")
    public ResponseEntity<LoanData> findByMobileNumber(
            @RequestParam
            @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ) {
        LoanData loanData = loanService.findLoanByMobileNumber(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanData);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData> update(@Valid @RequestBody LoanData loanData) {
        boolean isSuccess = loanService.updateLoan(loanData);
        if (isSuccess) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseData(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseData(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()),
                            HttpStatus.EXPECTATION_FAILED.getReasonPhrase()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData> delete(@RequestParam Long loanId) {
        boolean isSuccess = loanService.deleteLoan(loanId);
        if (isSuccess) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseData(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseData(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()),
                            HttpStatus.EXPECTATION_FAILED.getReasonPhrase()));
        }
    }
}
