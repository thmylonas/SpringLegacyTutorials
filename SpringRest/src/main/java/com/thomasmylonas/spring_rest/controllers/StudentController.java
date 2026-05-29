package com.thomasmylonas.spring_rest.controllers;

import com.thomasmylonas.spring_rest.dtos.student_dtos.StudentRequestDto;
import com.thomasmylonas.spring_rest.dtos.student_dtos.StudentResponseDto;
import com.thomasmylonas.spring_rest.models.ResponseBuilder;
import com.thomasmylonas.spring_rest.models.ResponseSuccess;
import com.thomasmylonas.spring_rest.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = {"/api/v1/students"})
@RequiredArgsConstructor
public class StudentController {

    private static final String REQUEST_MAPPING = "/api/v1/students";

    private final StudentService studentService;
    private final ResponseBuilder responseBuilder;

    @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> findStudentById(@PathVariable(name = "id") Long studentId) { // "http://localhost:8080/api/v1/students/{id}"

        if (studentId == null || studentId < 0) {
            throw new IllegalArgumentException("The id is not valid!");
        }
        StudentResponseDto studentResponseDto = studentService.findStudentById(studentId);
        final String message = "Success: The Student with the ID '" + studentId + "' is found!";
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("student_response", studentResponseDto));
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> findAllStudents() { // "http://localhost:8080/api/v1/students"
        List<StudentResponseDto> studentResponseDtos = studentService.findAllStudents();
        final String message = "Success: The Students are found!";
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("students_response", studentResponseDtos));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> saveStudent(@RequestBody StudentRequestDto studentRequestDto) { // "http://localhost:8080/api/v1/students"

        if (studentRequestDto == null) {
            throw new IllegalArgumentException("The student is not valid (is null)!");
        }
        StudentResponseDto savedStudentResponseDto = studentService.saveStudent(studentRequestDto);
        final String message = "Created: The Student has been created successfully!";

        String savedStudentUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REQUEST_MAPPING + "/{id}")
                .buildAndExpand(savedStudentResponseDto.id())
                .toUriString();
        return responseBuilder.buildResponseSuccess(HttpStatus.CREATED, message, savedStudentUri, Map.of("saved_student_response", savedStudentResponseDto));
    }

    @RequestMapping(path = {"/all"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveAllStudents(@RequestBody List<StudentRequestDto> studentRequestDtos) { // "http://localhost:8080/api/v1/students/all"

        if (studentRequestDtos == null || studentRequestDtos.isEmpty() || studentRequestDtos.contains(null)) {
            throw new IllegalArgumentException("The students are not valid!");
        }
        List<StudentResponseDto> savedStudentResponseDtos = studentService.saveAllStudents(studentRequestDtos);
        final String message = "Created: The Students have been created successfully!";
        return responseBuilder.buildResponseSuccess(HttpStatus.CREATED, message, Map.of("saved_students_response", savedStudentResponseDtos));
    }

    @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> updateStudent(@RequestBody StudentRequestDto studentRequestDto, @PathVariable(name = "id") Long studentId) { // "http://localhost:8080/api/v1/students/{id}"

        if (studentId == null || studentId < 0 || studentRequestDto == null) {
            throw new IllegalArgumentException("The arguments are not valid!");
        }
        StudentResponseDto updatedStudentResponseDto = studentService.updateStudent(studentRequestDto, studentId);
        final String message = "Success: The Student with ID '" + studentId + "' has been updated successfully!";
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("updated_student_response", updatedStudentResponseDto));
    }

    @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> deleteStudentById(@PathVariable(name = "id") Long studentId) { // "http://localhost:8080/api/v1/students/{id}"

        if (studentId == null || studentId < 0) {
            throw new IllegalArgumentException("The id is not valid!");
        }
        studentService.deleteStudentById(studentId);
        final String message = "Success: The Student with ID '" + studentId + "' has been deleted successfully!";
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("message", message));
    }
}
