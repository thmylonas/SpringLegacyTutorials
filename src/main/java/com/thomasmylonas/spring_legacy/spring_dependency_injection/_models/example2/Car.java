package com.thomasmylonas.spring_legacy.spring_dependency_injection._models.example2;

import com.thomasmylonas.spring_legacy._base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings(value = "all") // IntelliJ bug: "Could not autowire. No beans of X type found"
public class Car implements BaseModel {

    private String type;
    private String name;
    private int velocity;
    private int carAge;

    @Override
    public String toString() {
        return "The car " + name + " with type " + type + ", "
                + "has maximum velocity " + velocity + " km/h "
                + "and carAge " + carAge;
    }
}
