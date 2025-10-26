package com.thomasmylonas.spring_core.spring_dependency_injection._models.example2;

import com.thomasmylonas.spring_core._base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings(value = "all") // IntelliJ bug: "Could not autowire. No beans of X type found"
public class Job implements BaseModel {

    private String name;
    private double salary;

    @Override
    public String toString() {
        return "The job " + name + " has salary " + salary + " Euro";
    }
}
