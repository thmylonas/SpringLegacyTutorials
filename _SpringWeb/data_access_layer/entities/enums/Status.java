package com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.entities.enums;

public enum Status {

    ADVANCED_STUDENT("Advanced Student"),
    MEDIUM_STUDENT("Medium Student"),
    JUNIOR_STUDENT("Junior Student");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
