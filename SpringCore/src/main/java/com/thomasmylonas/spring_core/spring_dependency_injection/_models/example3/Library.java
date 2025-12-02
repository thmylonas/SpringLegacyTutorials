package com.thomasmylonas.spring_core.spring_dependency_injection._models.example3;

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
public class Library {

    private Person manager;
    private List<Teacher> teachers;
    private Set<Book> books;
    private Map<String, String> teacherBookMapping;
    private Properties teacherInfo;
}
