package com.thomasmylonas.spring_core.spring_dependency_injection_alt._models;

import com.thomasmylonas.spring_core._base.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings(value = "all") // IntelliJ bug: "Could not autowire. No beans of X type found"
public class Student implements BaseModel {

    private String firstName;
    private String lastName;
    private int age;

    private Address nikomedeiasAddress;
}
