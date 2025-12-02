package com.thomasmylonas.spring_core.spring_dependency_injection._models.example1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuppressWarnings(value = "all") // IntelliJ bug: "Could not autowire. No beans of X type found"
public class GroupOfPeople {

    /// /////////// Simple Types fields ////////////////

    private String groupDescription;
    private String groupInfo;
    private int groupSize;

    /// /////////// Beans fields ///////////////////////

    private Person1 person1;
    private Person2 person2;
    private Person3 person3;
    private Person4 person4;

    /// /////////// Collections fields /////////////////

    private List<String> peopleNames;
    private Set<Person1> people;
    private Map<String, Integer> peopleNameAgeMapping;
    private Properties peopleInfo;
}
