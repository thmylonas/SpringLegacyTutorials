package com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.services.api_services.student_alt_api_services;

import com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.entities.StudentAlt;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.entities.enums.Status;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.repositories.StudentAltRepository;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.helpers.HelperClass;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.exceptions.ResourceNotFoundException;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.services.api_services.student_alt_api_services._base.AbstractStudentAltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@Service(value = "dbStudentAltService")
public class DbStudentAltService extends AbstractStudentAltService {

    private final StudentAltRepository studentAltRepository;

    @Autowired
    public DbStudentAltService(StudentAltRepository studentAltRepository) {
        this.studentAltRepository = studentAltRepository;
    }

    @PostConstruct
    private void init() {
        initStudentsAltList();
    }

    @Override
    public StudentAlt getStudentAltById(Long id) throws ResourceNotFoundException {

        if (id == null) {
            return null;
        }
        return studentAltRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(StudentAlt.class.getSimpleName(), id));
    }

    @Override
    public List<StudentAlt> getAllStudentsAlt() {
        return studentAltRepository.findAll();
    }

    @Override
    public StudentAlt saveStudentAlt(StudentAlt studentAlt) {

        if (studentAlt == null) {
            return null;
        }
        return studentAltRepository.save(studentAlt);
    }

    @Override
    public StudentAlt updateStudentAlt(StudentAlt studentAlt, Long id) throws ResourceNotFoundException {

        if (id == null || studentAlt == null) {
            return null;
        }
        StudentAlt studentAltToBeUpdated = studentAltRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(StudentAlt.class.getSimpleName(), id));
        updateStudentAltWithGivenObject(studentAltToBeUpdated, studentAlt);
        return studentAltRepository.save(studentAltToBeUpdated);
    }

    @Override
    public void deleteStudentAlt(Long id) throws ResourceNotFoundException {

        if (id == null) {
            return;
        }
        studentAltRepository.deleteById(id);
    }

    private void initStudentsAltList() {

        studentAltRepository.save(new StudentAlt.Builder()
                .lastName("Mylonas")
                .firstName("Thomas")
                .dateOfBirth(HelperClass.getDate(1972, Calendar.SEPTEMBER, 24))
                .absences(1)
                .departmentId("Dept1")
                .status(Status.ADVANCED_STUDENT)
                .build());
        studentAltRepository.save(new StudentAlt.Builder()
                .lastName("Lorem")
                .firstName("Ipsum")
                .dateOfBirth(HelperClass.getDate(1979, Calendar.APRIL, 30))
                .absences(5)
                .departmentId("Dept1")
                .status(Status.JUNIOR_STUDENT)
                .build());
        studentAltRepository.save(new StudentAlt.Builder()
                .lastName("Chan")
                .firstName("Jacky")
                .dateOfBirth(HelperClass.getDate(1982, Calendar.MAY, 9))
                .absences(3)
                .departmentId("Dept2")
                .status(Status.JUNIOR_STUDENT)
                .build());
        studentAltRepository.save(new StudentAlt.Builder()
                .lastName("Parker")
                .firstName("Peter")
                .dateOfBirth(HelperClass.getDate(1990, Calendar.OCTOBER, 15))
                .absences(7)
                .departmentId("Dept3")
                .status(Status.MEDIUM_STUDENT)
                .build());
        studentAltRepository.save(new StudentAlt.Builder()
                .lastName("Murdock")
                .firstName("Matt")
                .dateOfBirth(HelperClass.getDate(2000, Calendar.FEBRUARY, 2))
                .absences(2)
                .departmentId("Dept4")
                .status(Status.ADVANCED_STUDENT)
                .build());
    }
}
