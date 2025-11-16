package com.thomasmylonas.spring_rest.controllers;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Student> findStudentById(@PathVariable(name = "id") Long id) { // "http://localhost:8080/api/v1/students/1"
        Student student = studentService.findStudentById(id);
        log.info("The student with id = " + id + ", is the\n" + student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<List<Student>> findAllStudents() { // "http://localhost:8080/api/v1/students/all-students"
        return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) { // "http://localhost:8080/api/v1/students"
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable(name = "id") Long id) { // "http://localhost:8080/api/v1/students/{id}"
        return new ResponseEntity<>(studentService.updateStudent(student, id), HttpStatus.OK);
    }

    @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void deleteStudentById(@PathVariable(name = "id") Long id) { // "http://localhost:8080/api/v1/students/{id}"
        studentService.deleteStudent(id);
    }
}
