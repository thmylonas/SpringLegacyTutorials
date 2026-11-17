package com.thomasmylonas.spring_rest.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Status {

    ADVANCED_STUDENT("Advanced Student"),
    MEDIUM_STUDENT("Medium Student"),
    JUNIOR_STUDENT("Junior Student");

    private final String value;

    public static Status valueOfStatus(String status) {
        return Arrays.stream(values())
                .filter(s -> s.getValue().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The status argument in not valid!"));
    }
}
