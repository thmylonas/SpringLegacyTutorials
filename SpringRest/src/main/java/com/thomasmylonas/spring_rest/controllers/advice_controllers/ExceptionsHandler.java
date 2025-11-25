package com.thomasmylonas.spring_rest.controllers.advice_controllers;

import com.thomasmylonas.spring_rest.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.spring_rest.models_dtos.response_models.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {RequestedResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND) // 404: "Not Found"
    @ResponseBody
    public ResponseEntity<ResponseError> handleRequestedResourceNotFoundException(RequestedResourceNotFoundException e, WebRequest request) {
        String message = "The resource not found: " + e.getMessage();
        return buildResponseError(e, message, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400: "Bad Request"
    @ResponseBody
    public ResponseEntity<ResponseError> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request) {
        String message = "The request arguments are not valid: " + e.getMessage();
        return buildResponseError(e, message, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) // 500: "Internal Server Error"
    @ResponseBody
    public ResponseEntity<ResponseError> handleAllUncaughtExceptions(Exception e, WebRequest request) {
        String message = "Unknown error occurred: " + e.getMessage();
        return buildResponseError(e, message, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ResponseError> buildResponseError(Exception e, String message, HttpStatus status, WebRequest request) {

        ResponseError responseError = ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(status.toString())
                .message(message)
                .path(request.getDescription(false))
                .build();
        return ResponseEntity.status(status).body(responseError);
    }
}
