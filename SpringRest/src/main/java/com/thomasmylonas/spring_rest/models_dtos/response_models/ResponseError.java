package com.thomasmylonas.spring_rest.models_dtos.response_models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.Date;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseError(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        Date timestamp,

        String statusCode,
        String message,
        String path // "request URL"
) {
}
