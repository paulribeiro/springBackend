package com.resume.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnexpectedElementException extends RuntimeException {

    public UnexpectedElementException(String s) {
        super(s);
    }
}