package com.thomasmylonas.spring_core.spring_dependency_injection_alt._models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings(value = "all") // IntelliJ bug: "Could not autowire. No beans of X type found"
public class Address {

    private String street;
    private String number;
    private String postCode;
}
