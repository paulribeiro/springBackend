package com.resume.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnexpectedCompetenceException extends RuntimeException {

    public UnexpectedCompetenceException(String s) {
        super(s);
    }
}
