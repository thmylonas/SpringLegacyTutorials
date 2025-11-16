package com.thomasmylonas.spring_rest.services;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.exceptions.ResourceNotFoundException;

import java.util.List;

public interface StudentService {

    Student findStudentById(Long id) throws ResourceNotFoundException;

    List<Student> findAllStudents();

    Student saveStudent(Student student);

    Student updateStudent(Student student, Long id) throws ResourceNotFoundException;

    void deleteStudent(Long id) throws ResourceNotFoundException;
}
