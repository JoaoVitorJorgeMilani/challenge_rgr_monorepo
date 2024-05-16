package com.rgr.webtransferback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoTaxFoundException extends RuntimeException {
    public NoTaxFoundException(String message) {
        super(message);
    }
}

