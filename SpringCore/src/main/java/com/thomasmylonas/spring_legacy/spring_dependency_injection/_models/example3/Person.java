package com.thomasmylonas.spring_legacy.spring_dependency_injection._models.example3;

import com.thomasmylonas.spring_legacy._base.BaseModel;
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
public class Person implements BaseModel {

    private String name;
    private int age;
    private String address;
    private String phoneNumber;
}
