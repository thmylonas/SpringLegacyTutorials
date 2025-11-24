package com.thomasmylonas.spring_rest.services;

import com.thomasmylonas.spring_rest.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.spring_rest.models_dtos.student_dtos.StudentRequestDto;
import com.thomasmylonas.spring_rest.models_dtos.student_dtos.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto findStudentById(Long id) throws IllegalArgumentException, RequestedResourceNotFoundException;

    List<StudentResponseDto> findAllStudents();

    StudentResponseDto saveStudent(StudentRequestDto studentRequestDto) throws IllegalArgumentException;

    List<StudentResponseDto> saveAllStudents(List<StudentRequestDto> studentRequestDtos) throws IllegalArgumentException;

    StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, Long id) throws IllegalArgumentException, RequestedResourceNotFoundException;

    void deleteStudentById(Long id) throws IllegalArgumentException;
}
