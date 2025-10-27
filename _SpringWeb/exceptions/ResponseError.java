package com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.exceptions;

public class ResponseError {

    private final String errorMessage;
    private final int errorCode;
    private final String additionalMessage;
    //private final Exception exception;

    public ResponseError(String errorMessage, int errorCode, String additionalMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.additionalMessage = additionalMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }
}
