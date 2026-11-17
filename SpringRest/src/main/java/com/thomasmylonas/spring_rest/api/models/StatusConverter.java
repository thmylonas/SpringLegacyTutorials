package com.thomasmylonas.spring_rest.api.models;

import com.thomasmylonas.spring_rest.api.enums.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String statusValue) {
        return Status.valueOfStatus(statusValue);
    }
}
