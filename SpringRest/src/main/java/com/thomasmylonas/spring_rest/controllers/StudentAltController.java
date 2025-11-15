package com.thomasmylonas.spring_rest.controllers;

import com.thomasmylonas.spring_rest.entities.StudentAlt;
import com.thomasmylonas.spring_rest.student_alt_api_services.StudentAltService;
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
 * A simple sample API, on the StudentAlt entity (alternative)
 */
@Controller
@RequestMapping(path = "/api/student-alt/")
@Slf4j
public class StudentAltController /*extends AbstractController*/ {

    private final StudentAltService dbStudentAltService;

    @Autowired
    public StudentAltController(StudentAltService dbStudentAltService) {
        this.dbStudentAltService = dbStudentAltService;
    }

    // http://localhost:8080/SpringMvcJsfPfWebApp/api/student-alt/1
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<StudentAlt> getStudentAltById(@PathVariable(name = "id") Long id) {
        StudentAlt studentAlt = dbStudentAltService.getStudentAltById(id);
        log.info("The studentAlt with id = " + id + ", is the\n" + studentAlt);
        return new ResponseEntity<>(studentAlt, HttpStatus.OK);
    }

    // http://localhost:8080/SpringMvcJsfPfWebApp/api/student-alt/all-students-alt
    @RequestMapping(path = "all-students-alt", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<StudentAlt>> getAllStudentsAlt() {
        return new ResponseEntity<>(dbStudentAltService.findAllStudentsAlt(), HttpStatus.OK);
    }

    // http://localhost:8080/SpringMvcJsfPfWebApp/api/student-alt
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<StudentAlt> saveNewStudentAlt(@RequestBody StudentAlt studentAlt) {
        return new ResponseEntity<>(dbStudentAltService.saveStudentAlt(studentAlt), HttpStatus.CREATED);
    }

    // http://localhost:8080/SpringMvcJsfPfWebApp/api/student-alt/1
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<StudentAlt> updateExistingStudentAlt(@RequestBody StudentAlt studentAlt,
                                                               @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(dbStudentAltService.updateStudentAlt(studentAlt, id), HttpStatus.OK);
    }

    // http://localhost:8080/SpringMvcJsfPfWebApp/api/student-alt/1
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteExistingStudentAlt(@PathVariable(name = "id") Long id) {
        dbStudentAltService.deleteStudentAlt(id);
    }
}
