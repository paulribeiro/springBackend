package com.resume.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnexpectedFormationException extends RuntimeException {

    public UnexpectedFormationException(String s) {
        super(s);
    }
}
