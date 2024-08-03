package com.caramelwaffle.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardMobileNumberAlreadyExistException extends RuntimeException {
    public CardMobileNumberAlreadyExistException(String message){
        super(message);
    }
}
