package com.thomasmylonas.spring_core.spring_dependency_injection._models.example2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings(value = "all") // IntelliJ bug: "Could not autowire. No beans of X type found"
public class Employee {

    private String firstName;
    private String lastName;
    private int age;
    private Car car;
    private Job job;

    @Override
    public String toString() {
        return firstName + " " + lastName + " is today " + age + " years old. "
                + "Owner of the car: {" + car + "}, and works in the job: {" + job + "}";
    }
}
