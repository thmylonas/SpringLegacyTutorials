package com.thomasmylonas.spring_rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomasmylonas.spring_rest.helpers.HelperClass;
import lombok.Builder;

import java.util.Date;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseSuccess(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = HelperClass.DEFAULT_TIMEZONE)
        @JsonProperty(value = "timestamp")
        Date timestamp,

        @JsonProperty(value = "status_code")
        String statusCode,

        @JsonProperty(value = "message")
        String message,

        @JsonProperty(value = "path")
        String path, // "request URL"

        @JsonProperty(value = "data")
        Map<String, ?> data // The resource to be returned
) {
}
