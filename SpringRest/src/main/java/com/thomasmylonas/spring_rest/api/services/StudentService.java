package com.thomasmylonas.spring_rest.api.services;

import com.thomasmylonas.spring_rest.api.exceptions.GeneralRollbackException;
import com.thomasmylonas.spring_rest.api.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.spring_rest.api.dtos.student_dtos.StudentRequestDto;
import com.thomasmylonas.spring_rest.api.dtos.student_dtos.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto findStudentById(Long id) throws IllegalArgumentException, RequestedResourceNotFoundException;

    List<StudentResponseDto> findAllStudents();

    StudentResponseDto saveStudent(StudentRequestDto studentRequestDto) throws IllegalArgumentException, GeneralRollbackException;

    List<StudentResponseDto> saveAllStudents(List<StudentRequestDto> studentRequestDtos) throws IllegalArgumentException, GeneralRollbackException;

    StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, Long id) throws IllegalArgumentException, RequestedResourceNotFoundException, GeneralRollbackException;

    void deleteStudentById(Long id) throws IllegalArgumentException, RequestedResourceNotFoundException, GeneralRollbackException;
}
