package com.thomasmylonas.spring_legacy.spring_dependency_injection_alt._models;

import com.thomasmylonas.spring_legacy._base.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings(value = "all") // IntelliJ bug: "Could not autowire. No beans of X type found"
public class Address implements BaseModel {

    private String street;
    private String number;
    private String postCode;
}
