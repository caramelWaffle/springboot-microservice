package com.caramelwaffle.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanMobileNumberAlreadyExistException extends RuntimeException {
    public LoanMobileNumberAlreadyExistException(String message){
        super(message);
    }
}
