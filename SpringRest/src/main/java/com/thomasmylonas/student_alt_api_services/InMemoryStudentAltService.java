package com.thomasmylonas.student_alt_api_services;

import com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.entities.StudentAlt;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.entities.enums.Status;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.helpers.HelperClass;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.exceptions.ResourceNotFoundException;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.services.api_services.student_alt_api_services._base.AbstractStudentAltService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service(value = "inMemoryStudentAltService")
public class InMemoryStudentAltService extends AbstractStudentAltService {

    private List<StudentAlt> inMemoryStudentsAltList;

    @PostConstruct
    private void init() {
        initInMemoryStudentsAltList();
    }

    @Override
    public StudentAlt getStudentAltById(Long id) throws ResourceNotFoundException {
        return filterInMemoryStudentsAltListById(id);
    }

    @Override
    public List<StudentAlt> getAllStudentsAlt() {
        return inMemoryStudentsAltList;
    }

    @Override
    public StudentAlt saveStudentAlt(StudentAlt studentAlt) {

        if (studentAlt == null) {
            return null;
        }
        inMemoryStudentsAltList.add(studentAlt);
        return studentAlt;
    }

    @Override
    public StudentAlt updateStudentAlt(StudentAlt studentAlt, Long id) throws ResourceNotFoundException {

        if (id == null || studentAlt == null) {
            return null;
        }
        StudentAlt studentAltToBeUpdated = getStudentAltById(id); // This might throw a "ResourceNotFoundException"
        updateStudentAltWithGivenObject(studentAltToBeUpdated, studentAlt);
        return studentAltToBeUpdated;
    }

    @Override
    public void deleteStudentAlt(Long id) throws ResourceNotFoundException {

        StudentAlt studentAltToDelete = getStudentAltById(id); // This might throw a "ResourceNotFoundException"
        if (studentAltToDelete == null) {
            return;
        }
        inMemoryStudentsAltList.remove(studentAltToDelete);
    }

    private StudentAlt filterInMemoryStudentsAltListById(Long id) {

        // Filter the "inMemoryStudentsAltList" by ID
        return id == null ? null :
                inMemoryStudentsAltList
                        .stream()
                        .filter(studentAlt -> studentAlt.getId().equals(id))
                        .findAny()
                        .orElseThrow(() -> new ResourceNotFoundException(StudentAlt.class.getSimpleName(), id));
    }

    private void initInMemoryStudentsAltList() {

        inMemoryStudentsAltList = new ArrayList<>();

        inMemoryStudentsAltList.add(new StudentAlt.Builder()
                .id(1L)
                .lastName("Mylonas")
                .firstName("Thomas")
                .dateOfBirth(HelperClass.getDate(1972, Calendar.SEPTEMBER, 24))
                .absences(1)
                .departmentId("Dept1")
                .status(Status.ADVANCED_STUDENT)
                .build());
        inMemoryStudentsAltList.add(new StudentAlt.Builder()
                .id(2L)
                .lastName("Lorem")
                .firstName("Ipsum")
                .dateOfBirth(HelperClass.getDate(1979, Calendar.APRIL, 30))
                .absences(5)
                .departmentId("Dept1")
                .status(Status.JUNIOR_STUDENT)
                .build());
        inMemoryStudentsAltList.add(new StudentAlt.Builder()
                .id(3L)
                .lastName("Chan")
                .firstName("Jacky")
                .dateOfBirth(HelperClass.getDate(1982, Calendar.MAY, 9))
                .absences(3)
                .departmentId("Dept2")
                .status(Status.JUNIOR_STUDENT)
                .build());
        inMemoryStudentsAltList.add(new StudentAlt.Builder()
                .id(4L)
                .lastName("Parker")
                .firstName("Peter")
                .dateOfBirth(HelperClass.getDate(1990, Calendar.OCTOBER, 15))
                .absences(7)
                .departmentId("Dept3")
                .status(Status.MEDIUM_STUDENT)
                .build());
        inMemoryStudentsAltList.add(new StudentAlt.Builder()
                .id(5L)
                .lastName("Murdock")
                .firstName("Matt")
                .dateOfBirth(HelperClass.getDate(2000, Calendar.FEBRUARY, 2))
                .absences(2)
                .departmentId("Dept4")
                .status(Status.ADVANCED_STUDENT)
                .build());
    }
}
