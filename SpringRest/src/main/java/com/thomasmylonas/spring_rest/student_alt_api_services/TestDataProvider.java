package com.thomasmylonas.spring_rest.student_alt_api_services;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.entities.enums.Status;
import com.thomasmylonas.spring_rest.helpers.HelperClass;

import java.util.Calendar;
import java.util.List;

public class TestDataProvider {

    public static final List<Student> STUDENTS = List.of(
            Student.builder()
                    .lastName("Mylonas")
                    .firstName("Thomas")
                    .dateOfBirth(HelperClass.buildDate(1972, Calendar.SEPTEMBER, 24))
                    .absences(1)
                    .departmentId("Dept1")
                    .status(Status.ADVANCED_STUDENT)
                    .build(),
            Student.builder()
                    .lastName("Lorem")
                    .firstName("Ipsum")
                    .dateOfBirth(HelperClass.buildDate(1979, Calendar.APRIL, 30))
                    .absences(5)
                    .departmentId("Dept2")
                    .status(Status.JUNIOR_STUDENT)
                    .build(),
            Student.builder()
                    .lastName("Chan")
                    .firstName("Jacky")
                    .dateOfBirth(HelperClass.buildDate(1982, Calendar.MAY, 9))
                    .absences(3)
                    .departmentId("Dept3")
                    .status(Status.JUNIOR_STUDENT)
                    .build(),
            Student.builder()
                    .lastName("Parker")
                    .firstName("Peter")
                    .dateOfBirth(HelperClass.buildDate(1990, Calendar.OCTOBER, 15))
                    .absences(7)
                    .departmentId("Dept4")
                    .status(Status.MEDIUM_STUDENT)
                    .build(),
            Student.builder()
                    .lastName("Murdock")
                    .firstName("Matt")
                    .dateOfBirth(HelperClass.buildDate(2000, Calendar.FEBRUARY, 2))
                    .absences(2)
                    .departmentId("Dept5")
                    .status(Status.ADVANCED_STUDENT)
                    .build()
    );
}
