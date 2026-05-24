package com.thomasmylonas.spring_rest.models;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.Map;

@Service
public class ResponseBuilder {

    public ResponseEntity<ResponseSuccess> buildResponseSuccess(String message, HttpStatus status, Map<String, ?> data) {
        return buildResponseSuccess(message, status, ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), data);
    }

    public ResponseEntity<ResponseSuccess> buildResponseSuccess(String message, HttpStatus status, String path, Map<String, ?> data) {

        ResponseSuccess responseSuccess = ResponseSuccess.builder()
                .timestamp(new Date())
                .statusCode(status.toString())
                .message(message)
                .path(path)
                .data(data)
                .build();
        return ResponseEntity.status(status).header(HttpHeaders.LOCATION, path).body(responseSuccess);
    }

    public ResponseEntity<ResponseError> buildResponseError(Exception e, HttpStatus httpStatus, Map<String, ?> errorMessages) {

        ResponseError responseError = ResponseError.builder()
                .timestamp(new Date())
                .statusCode(httpStatus.toString())
                .errorMessages(errorMessages)
                .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString()) // "request.getDescription(false)"
                .build();
        return ResponseEntity.status(httpStatus).body(responseError);
    }
}
