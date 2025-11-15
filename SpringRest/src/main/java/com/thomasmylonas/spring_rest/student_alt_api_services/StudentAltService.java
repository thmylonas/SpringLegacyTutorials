package com.thomasmylonas.spring_rest.student_alt_api_services;

import com.thomasmylonas.spring_rest.entities.StudentAlt;
import com.thomasmylonas.spring_rest.exceptions.ResourceNotFoundException;

import java.util.List;

public interface StudentAltService {

    StudentAlt getStudentAltById(Long id) throws ResourceNotFoundException;

    List<StudentAlt> findAllStudentsAlt();

    StudentAlt saveStudentAlt(StudentAlt studentAlt);

    StudentAlt updateStudentAlt(StudentAlt studentAlt, Long id) throws ResourceNotFoundException;

    void deleteStudentAlt(Long id) throws ResourceNotFoundException;
}
