package com.acetutoring.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message){super(message);}
}
