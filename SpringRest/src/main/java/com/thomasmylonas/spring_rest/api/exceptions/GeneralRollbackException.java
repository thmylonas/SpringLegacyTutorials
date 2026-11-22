package com.thomasmylonas.spring_rest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Exception is thrown in case that anything fails while transaction happens
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralRollbackException extends RuntimeException {

    public GeneralRollbackException(String message, Exception cause) {
        super(message, cause);
    }
}
