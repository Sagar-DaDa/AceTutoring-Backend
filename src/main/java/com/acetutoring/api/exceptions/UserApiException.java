package com.acetutoring.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class UserApiException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
