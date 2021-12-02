package com.resume.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnexpectedProjectException extends RuntimeException {

    public UnexpectedProjectException(String s) {
        super(s);
    }
}
