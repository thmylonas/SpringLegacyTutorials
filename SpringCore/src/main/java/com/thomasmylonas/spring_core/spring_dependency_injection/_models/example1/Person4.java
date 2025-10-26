package com.thomasmylonas.spring_core.spring_dependency_injection._models.example1;

import com.thomasmylonas.spring_core._base.BaseModel;
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
public class Person4 implements BaseModel {

    private String name4;
    private int age4;
    private String info4;
    private double salary4;
}
