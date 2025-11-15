package com.thomasmylonas.spring_rest.entities.enums;

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
