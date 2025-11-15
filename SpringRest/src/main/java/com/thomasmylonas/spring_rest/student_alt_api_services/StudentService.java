package com.thomasmylonas.spring_rest.student_alt_api_services;

import com.thomasmylonas.spring_rest.entities.StudentAlt;
import com.thomasmylonas.spring_rest.exceptions.ResourceNotFoundException;

import java.util.List;

public interface StudentService {

    StudentAlt findStudentById(Long id) throws ResourceNotFoundException;

    List<StudentAlt> findAllStudents();

    StudentAlt saveStudent(StudentAlt student);

    void updateStudent(StudentAlt student, Long id) throws ResourceNotFoundException;

    void deleteStudent(Long id) throws ResourceNotFoundException;
}
