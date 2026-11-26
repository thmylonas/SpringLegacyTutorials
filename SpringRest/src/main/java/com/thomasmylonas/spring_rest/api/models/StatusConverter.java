package com.thomasmylonas.spring_rest.api.models;

import com.thomasmylonas.spring_rest.api.enums.Status;
import com.thomasmylonas.spring_rest.helpers.HelperClass;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return status == null ? null : status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String statusValue) {
        return HelperClass.isStringNullOrEmpty(statusValue) ? null : Status.valueOfStatus(statusValue);
    }
}
