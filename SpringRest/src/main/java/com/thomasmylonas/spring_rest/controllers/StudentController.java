package com.thomasmylonas.spring_rest.controllers;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * A simple sample API, on the Student entity
 */
@Controller
@RequestMapping(path = "/api/student/")
@Slf4j
public class StudentController /*extends AbstractController*/ {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // http://localhost:8080/SpringMvcJsfPfWebApp/api/student/1
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Student> getStudentById(@PathVariable(name = "id") Long id) {
        Student student = studentService.findStudentById(id);
        log.info("The student with id = " + id + ", is the\n" + student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // http://localhost:8080/SpringMvcJsfPfWebApp/api/student/all-students
    @RequestMapping(path = "all-students", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);
    }

    // http://localhost:8080/SpringMvcJsfPfWebApp/api/student
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Student> saveNewStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    // http://localhost:8080/SpringMvcJsfPfWebApp/api/student/1
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Student> updateExistingStudent(@RequestBody Student student,
                                                         @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(studentService.updateStudent(student, id), HttpStatus.OK);
    }

    // http://localhost:8080/SpringMvcJsfPfWebApp/api/student/1
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteExistingStudent(@PathVariable(name = "id") Long id) {
        studentService.deleteStudent(id);
    }
}
