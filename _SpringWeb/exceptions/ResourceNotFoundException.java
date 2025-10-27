package com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is the ResourceNotFoundException which carries the "type" (Class) and the "ID" of the resource.
 * In its creation it sets up the super-RuntimeException's message, with the "type" (Class) and the "ID" of the resource
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceClass;
    private final Long resourceId;

    public ResourceNotFoundException(String clazz, Long id) {
        super(String.format("The entity '%s' with ID '%d' is not found!", clazz, id));
        this.resourceClass = clazz;
        this.resourceId = id;
    }

    public String getResourceClass() {
        return resourceClass;
    }

    public Long getResourceId() {
        return resourceId;
    }
}
