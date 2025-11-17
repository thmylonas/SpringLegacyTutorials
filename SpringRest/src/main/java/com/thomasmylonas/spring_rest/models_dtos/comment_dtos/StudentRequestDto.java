package com.thomasmylonas.spring_rest.models_dtos.comment_dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomasmylonas.spring_rest.entities.enums.Status;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentRequestDto(
        Long id,

        @JsonProperty(value = "last_name")
        String lastName,

        @JsonProperty(value = "first_name")
        String firstName,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        @JsonProperty(value = "date_of_birth")
        LocalDate dateOfBirth,

        Integer absences,

        @JsonProperty(value = "department_id")
        String departmentId,

        Status status
) {
}

/*
{
  "last_name": "Soldatova",
  "first_name": "Aleksandra",
  "date_of_birth": "20-01-2003",
  "absences": 5,
  "department_id": Soldatova_Department",
  "status": "Junior Student"
}

[
  {
    "last_name": "Kuek",
    "first_name": "Karolina Miroslavina",
    "date_of_birth": "01-12-1982",
    "absences": 6,
    "department_id": Kuek_Department",
    "status": "Advanced Student"
  },
  {
    "last_name": "Manakhimova",
    "first_name": "Jasmine Lvovna",
    "date_of_birth": "02-11-1980",
    "absences": 5,
    "department_id": Manakhimova_Department",
    "status": "Medium Student"
  },
  {
    "last_name": "Liubanya",
    "first_name": "Liubanya",
    "date_of_birth": "03-10-1990",
    "absences": 4,
    "department_id": Liubanya_Department",
    "status": "Junior Student"
  },
  {
    "last_name": "Devyatova",
    "first_name": "Marina",
    "date_of_birth": "04-09-1975",
    "absences": 3,
    "department_id": "Devyatova_Department",
    "status": "Advanced Student"
  }
]
*/

/*
Aleksandra Soldatova, 2003 (22)

Karolina Miroslavina Kuek, 1982 (43)
Jasmine Lvovna Manakhimova, 1980 (45)
Liubanya, 1990 (35)
Marina Devyatova, 1975 (50)

"Advanced Student"
"Medium Student"
"Junior Student"
*/
