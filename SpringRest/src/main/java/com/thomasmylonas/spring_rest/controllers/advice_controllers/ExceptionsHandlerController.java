package com.thomasmylonas.spring_rest.controllers.advice_controllers;

import com.thomasmylonas.spring_rest.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.spring_rest.models.ResponseBuilder;
import com.thomasmylonas.spring_rest.models.ResponseError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionsHandlerController {

    private final ResponseBuilder responseBuilder;

    @ExceptionHandler(value = {RequestedResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND) // 404: "Not Found"
    @ResponseBody
    public ResponseEntity<ResponseError> handleRequestedResourceNotFoundException(RequestedResourceNotFoundException e) {
        String message = "The resource not found: " + e.getMessage();
        return responseBuilder.buildResponseError(HttpStatus.NOT_FOUND, Map.of("error_message", message));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400: "Bad Request"
    @ResponseBody
    public ResponseEntity<ResponseError> handleIllegalArgumentException(IllegalArgumentException e) {
        String message = "The request arguments are not valid: " + e.getMessage();
        return responseBuilder.buildResponseError(HttpStatus.NOT_FOUND, Map.of("error_message", message));
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) // 500: "Internal Server Error"
    @ResponseBody
    public ResponseEntity<ResponseError> handleAllUncaughtExceptions(Exception e) {
        String message = "Unknown error occurred: " + e.getMessage();
        return responseBuilder.buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("error_message", message));
    }
}
