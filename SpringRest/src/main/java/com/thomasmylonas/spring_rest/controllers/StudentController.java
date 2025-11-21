package com.thomasmylonas.spring_rest.controllers;

import com.thomasmylonas.spring_rest.models_dtos.comment_dtos.StudentRequestDto;
import com.thomasmylonas.spring_rest.models_dtos.comment_dtos.StudentResponseDto;
import com.thomasmylonas.spring_rest.models_dtos.response_models.ResponseHandler;
import com.thomasmylonas.spring_rest.models_dtos.response_models.ResponseSuccess;
import com.thomasmylonas.spring_rest.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(path = "/api/v1/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private static final String REQUEST_MAPPING = "/api/v1/students";
    private final StudentService studentService;

    @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> findStudentById(@PathVariable(name = "id") Long id) { // "http://localhost:8080/api/v1/students/1"
        StudentResponseDto studentResponseDto = studentService.findStudentById(id);
        final String message = "Success: The Student with the ID '" + id + "' is found!";
        return ResponseHandler.buildResponse(message, HttpStatus.OK, Map.of("student_response", studentResponseDto));
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> findAllStudents() { // "http://localhost:8080/api/v1/students"
        List<StudentResponseDto> studentResponseDtos = studentService.findAllStudents();
        final String message = "Success: The Student are found!";
        return ResponseHandler.buildResponse(message, HttpStatus.OK, Map.of("students_response", studentResponseDtos));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> saveStudent(@RequestBody StudentRequestDto studentRequestDto) { // "http://localhost:8080/api/v1/students"

        StudentResponseDto savedStudentResponseDto = studentService.saveStudent(studentRequestDto);
        final String message = "Created: The Student has been created successfully!";

        String savedStudentUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REQUEST_MAPPING + "/{id}")
                .buildAndExpand(savedStudentResponseDto.id())
                .toUriString();
        return ResponseHandler.buildResponse(message, HttpStatus.CREATED, savedStudentUri, Map.of("saved_student_response", savedStudentResponseDto));
    }

    @RequestMapping(path = {"/all"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveAllPosts(@RequestBody List<StudentRequestDto> studentRequestDtos) { // "http://localhost:8080/api/v1/posts/all"
        List<StudentResponseDto> savedStudentResponseDtos = studentService.saveAllStudents(studentRequestDtos);
        final String message = "Created: The Students have been created successfully!";
        return ResponseHandler.buildResponse(message, HttpStatus.CREATED, Map.of("saved_students_response", savedStudentResponseDtos));
    }

    @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> updateStudent(@RequestBody StudentRequestDto studentRequestDto, @PathVariable(name = "id") Long id, ServletRequest servletRequest) { // "http://localhost:8080/api/v1/students/{id}"
        StudentResponseDto updatedStudentResponseDto = studentService.updateStudent(studentRequestDto, id);
        final String message = "Success: The Student with ID '" + id + "' has been updated successfully!";
        return ResponseHandler.buildResponse(message, HttpStatus.OK, Map.of("updated_student_response", updatedStudentResponseDto));
    }

    @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> deleteStudentById(@PathVariable(name = "id") Long id) { // "http://localhost:8080/api/v1/students/{id}"
        studentService.deleteStudentById(id);
        final String message = "Success: The Student with ID '" + id + "' has been deleted successfully!";
        return ResponseHandler.buildResponse(message, HttpStatus.OK, Map.of("message", message));
    }
}
