package com.thomasmylonas.spring_rest.services;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.models_dtos.comment_dtos.StudentRequestDto;
import com.thomasmylonas.spring_rest.models_dtos.comment_dtos.StudentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentMapper {

    public Student toStudent(StudentRequestDto studentRequestDto) {
        return Student.builder()
                .lastName(studentRequestDto.lastName())
                .firstName(studentRequestDto.firstName())
                .dateOfBirth(studentRequestDto.dateOfBirth())
                .absences(studentRequestDto.absences())
                .departmentId(studentRequestDto.departmentId())
                .status(studentRequestDto.status())
                .build();
    }

    public StudentResponseDto fromStudent(Student student) {
        return StudentResponseDto.builder()
                .id(student.getId())
                .lastName(student.getLastName())
                .firstName(student.getFirstName())
                .dateOfBirth(student.getDateOfBirth())
                .absences(student.getAbsences())
                .departmentId(student.getDepartmentId())
                .status(student.getStatus())
                .build();
    }
}
