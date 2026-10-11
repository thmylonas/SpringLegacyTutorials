package com.thomasmylonas.spring_rest.api.services.mappers;

import com.thomasmylonas.spring_rest.api.entities.Student;
import com.thomasmylonas.spring_rest.api.dtos.student_dtos.StudentRequestDto;
import com.thomasmylonas.spring_rest.api.dtos.student_dtos.StudentResponseDto;
import org.springframework.stereotype.Service;

@Service
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
