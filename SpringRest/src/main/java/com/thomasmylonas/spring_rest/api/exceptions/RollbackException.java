package com.thomasmylonas.spring_rest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class RollbackException extends RuntimeException {

    public RollbackException(String message, Exception cause) {
        super(message, cause);
    }
}
