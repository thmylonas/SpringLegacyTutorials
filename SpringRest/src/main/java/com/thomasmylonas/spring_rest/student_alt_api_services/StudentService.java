package com.thomasmylonas.spring_rest.student_alt_api_services;

import com.thomasmylonas.jsf_primefaces_tutorials.data_access_layer.entities.Student;
import com.thomasmylonas.jsf_primefaces_tutorials.service_layer.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface StudentService {

    Student findStudentById(Long id) throws ResourceNotFoundException;

    List<Student> findAllStudents();

    Student saveStudent(Student student);

    void updateStudent(Student student, Long id) throws ResourceNotFoundException;

    void deleteStudent(Long id) throws ResourceNotFoundException;
}
