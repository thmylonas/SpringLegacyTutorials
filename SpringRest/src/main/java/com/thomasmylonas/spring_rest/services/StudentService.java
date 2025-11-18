package com.thomasmylonas.spring_rest.services;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.exceptions.ResourceNotFoundException;
import com.thomasmylonas.spring_rest.models_dtos.comment_dtos.StudentRequestDto;
import com.thomasmylonas.spring_rest.models_dtos.comment_dtos.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto findStudentById(Long id) throws IllegalArgumentException, ResourceNotFoundException;

    List<StudentResponseDto> findAllStudents();

    StudentResponseDto saveStudent(StudentRequestDto studentRequestDto) throws IllegalArgumentException;

    List<StudentResponseDto> saveAllStudents(List<StudentRequestDto> postRequestDtos) throws IllegalArgumentException;

    StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, Long id) throws IllegalArgumentException, ResourceNotFoundException;

    void deleteStudent(Long id) throws IllegalArgumentException;
}
