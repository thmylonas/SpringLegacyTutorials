package com.thomasmylonas.spring_core.spring_dependency_injection._models.example1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuppressWarnings(value = "all") // IntelliJ bug: "Could not autowire. No beans of X type found"
public class Person3 {

    private String name3;
    private int age3;
    private String info3;
    private double salary3;
}
