package com.thomasmylonas.spring_rest.student_alt_api_services;

import com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.entities.StudentAlt;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.exceptions.ResourceNotFoundException;

import java.util.List;

public interface StudentAltService {

    StudentAlt getStudentAltById(Long id) throws ResourceNotFoundException;

    List<StudentAlt> getAllStudentsAlt();

    StudentAlt saveStudentAlt(StudentAlt studentAlt);

    StudentAlt updateStudentAlt(StudentAlt studentAlt, Long id) throws ResourceNotFoundException;

    void deleteStudentAlt(Long id) throws ResourceNotFoundException;
}
