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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = {"/api/v1/students"})
@RequiredArgsConstructor
public class StudentController {

    private static final String REQUEST_MAPPING = "/api/v1/students";

    private final StudentService studentService;
    private final ResponseBuilder responseBuilder;

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/students/{id}"
     *
     * @param studentId The "studentId"
     * @return The ResponseEntity<ResponseSuccess>
     * @throws IllegalArgumentException The IllegalArgumentException might be thrown
     */
    @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> findStudentById(@PathVariable(value = "id") Long studentId) throws IllegalArgumentException {

        if (studentId == null || studentId < 0) {
            throw new IllegalArgumentException("The id is not valid!"); // IllegalArgumentException
        }
        final String message = "Success: The Student with ID " + studentId + " is found!";
        StudentResponseDto studentResponseDto = studentService.findStudentById(studentId);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("student_response", studentResponseDto));
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/students"
     *
     * @return The ResponseEntity<ResponseSuccess>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> findAllStudents() {
        final String message = "Success: The Students are found!";
        List<StudentResponseDto> studentResponseDtos = studentService.findAllStudents();
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("students_response", studentResponseDtos));
    }

    /**
     * Endpoint:
     * - POST, "http://localhost:8080/api/v1/students"
     *
     * @param studentRequestDto The "studentRequestDto"
     * @return The ResponseEntity<ResponseSuccess>
     * @throws IllegalArgumentException The IllegalArgumentException might be thrown
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> saveStudent(@RequestBody StudentRequestDto studentRequestDto) throws IllegalArgumentException {

        if (studentRequestDto == null) {
            throw new IllegalArgumentException("The student is not valid (is null)!"); // IllegalArgumentException
        }
        final String message = "Created: The Student has been created successfully!";
        StudentResponseDto savedStudentResponseDto = studentService.saveStudent(studentRequestDto);
        String savedStudentUri = ServletUriComponentsBuilder
                .fromCurrentContextPath() // "http://localhost:8080"
                .path(REQUEST_MAPPING + "/{id}")
                .buildAndExpand(savedStudentResponseDto.id())
                .toUriString();
        return responseBuilder.buildResponseSuccess(HttpStatus.CREATED, message, savedStudentUri, Map.of("saved_student_response", savedStudentResponseDto));
    }

    /**
     * Endpoint:
     * - POST, "http://localhost:8080/api/v1/students/all"
     *
     * @param studentRequestDtos The "studentRequestDtos"
     * @return The ResponseEntity<ResponseSuccess>
     * @throws IllegalArgumentException The IllegalArgumentException might be thrown
     */
    @RequestMapping(path = {"/all"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> saveAllStudents(@RequestBody List<StudentRequestDto> studentRequestDtos) throws IllegalArgumentException {

        if (studentRequestDtos == null || studentRequestDtos.isEmpty() || studentRequestDtos.contains(null)) {
            throw new IllegalArgumentException("The students are not valid!"); // IllegalArgumentException
        }
        final String message = "Created: The Students have been created successfully!";
        List<StudentResponseDto> savedStudentResponseDtos = studentService.saveAllStudents(studentRequestDtos);
        return responseBuilder.buildResponseSuccess(HttpStatus.CREATED, message, Map.of("saved_students_response", savedStudentResponseDtos));
    }

    /**
     * Endpoint:
     * - PUT, "http://localhost:8080/api/v1/students/{id}"
     *
     * @param studentRequestDto The "studentRequestDto"
     * @param studentId         The "studentId"
     * @return The ResponseEntity<ResponseSuccess>
     * @throws IllegalArgumentException The IllegalArgumentException might be thrown
     */
    @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> updateStudent(@RequestBody StudentRequestDto studentRequestDto, @PathVariable(value = "id") Long studentId) throws IllegalArgumentException {

        if (studentId == null || studentId < 0 || studentRequestDto == null) {
            throw new IllegalArgumentException("The arguments are not valid!"); // IllegalArgumentException
        }
        final String message = "Success: The Student with ID " + studentId + " has been updated successfully!";
        StudentResponseDto updatedStudentResponseDto = studentService.updateStudent(studentRequestDto, studentId);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("updated_student_response", updatedStudentResponseDto));
    }

    /**
     * Endpoint:
     * - DELETE, "http://localhost:8080/api/v1/students/{id}"
     *
     * @param studentId The "studentId"
     * @return The ResponseEntity<ResponseSuccess>
     * @throws IllegalArgumentException The IllegalArgumentException might be thrown
     */
    @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseSuccess> deleteStudentById(@PathVariable(value = "id") Long studentId) throws IllegalArgumentException {

        if (studentId == null || studentId < 0) {
            throw new IllegalArgumentException("The id is not valid!"); // IllegalArgumentException
        }
        final String message = "Success: The Student with ID " + studentId + " has been deleted successfully!";
        studentService.deleteStudentById(studentId);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("message", message));
    }
}
