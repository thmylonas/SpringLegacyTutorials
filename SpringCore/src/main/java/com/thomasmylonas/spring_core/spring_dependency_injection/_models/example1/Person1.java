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
public class Person1 {

    private String name1;
    private int age1;
    private String info1;
    private double salary1;
}
