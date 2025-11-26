package com.thomasmylonas.spring_rest.models_dtos.student_dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomasmylonas.spring_rest.entities.enums.Status;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentResponseDto(
        @JsonProperty(value = "id")
        Long id,

        @JsonProperty(value = "last_name")
        String lastName,

        @JsonProperty(value = "first_name")
        String firstName,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        @JsonProperty(value = "date_of_birth")
        LocalDate dateOfBirth,

        @JsonProperty(value = "absences")
        Integer absences,

        @JsonProperty(value = "department_id")
        String departmentId,

        @JsonProperty(value = "status")
        Status status
) {
}
