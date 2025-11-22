package com.thomasmylonas.spring_rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the RequestedResourceNotFoundException which carries the "type" (Class) and the "ID" of the resource.
 * In its creation it sets up the super-RuntimeException's message, with the "type" (Class) and the "ID" of the resource
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RequestedResourceNotFoundException extends RuntimeException {

    public RequestedResourceNotFoundException(String clazz, Long resourceId) {
        super(String.format("%s: The '%s' with the ID '%d' is not found!",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")), clazz, resourceId));
    }
}
