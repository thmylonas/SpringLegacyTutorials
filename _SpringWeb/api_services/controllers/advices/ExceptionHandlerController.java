package com.thomasmylonas.spring_mvc_jsf_pf_web_app.servlet_dispatcher_web_components.controllers.advices;

import com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.exceptions.ResourceNotFoundException;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.exceptions.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ResponseError> handleResourceNotFoundException(RuntimeException e) {

        // The e (ResourceNotFoundException) carries the super-RuntimeException's message, with the "type" (Class) and the "ID" of the resource
        String additionalMessage = "Additional message: The exception '" + e.getClass().getSimpleName() + "' is thrown!";
        return new ResponseEntity<>(new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND.value(), additionalMessage), HttpStatus.NOT_FOUND);
    }
}
