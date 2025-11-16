package com.thomasmylonas.spring_rest.student_alt_api_services;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.entities.enums.Status;
import com.thomasmylonas.spring_rest.exceptions.ResourceNotFoundException;
import com.thomasmylonas.spring_rest.helpers.HelperClass;
import com.thomasmylonas.spring_rest.repositories.StudentDao;
import com.thomasmylonas.spring_rest.student_alt_api_services._base.AbstractStudentAltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@Service(value = "dbStudentAltService")
public class DbStudentAltService extends AbstractStudentAltService {

    private final StudentDao studentDao;

    @Autowired
    public DbStudentAltService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @PostConstruct
    private void init() {
        initStudentsAltList();
    }

    @Override
    public Student findStudentById(Long id) throws ResourceNotFoundException {

        if (id == null) {
            return null;
        }
//        return studentDao.findById(id).
//                orElseThrow(() -> new ResourceNotFoundException(Student.class.getSimpleName(), id));
        return null;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public Student saveStudent(Student studentAlt) {

        if (studentAlt == null) {
            return null;
        }
        return studentDao.save(studentAlt);
    }

    @Override
    public Student updateStudent(Student studentAlt, Long id) throws ResourceNotFoundException {

        if (id == null || studentAlt == null) {
            return null;
        }
        Student studentAltToBeUpdated = null; //studentDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(Student.class.getSimpleName(), id));
        updateStudentWithGivenObject(studentAltToBeUpdated, studentAlt);
        return studentDao.save(studentAltToBeUpdated);
    }

    @Override
    public void deleteStudent(Long id) throws ResourceNotFoundException {

        if (id == null) {
            return;
        }
        studentDao.deleteById(id);
    }

    private void initStudentsAltList() {

        studentDao.save(Student.builder()
                .lastName("Mylonas")
                .firstName("Thomas")
                .dateOfBirth(HelperClass.buildDate(1972, Calendar.SEPTEMBER, 24))
                .absences(1)
                .departmentId("Dept1")
                .status(Status.ADVANCED_STUDENT)
                .build());
        studentDao.save(Student.builder()
                .lastName("Lorem")
                .firstName("Ipsum")
                .dateOfBirth(HelperClass.buildDate(1979, Calendar.APRIL, 30))
                .absences(5)
                .departmentId("Dept1")
                .status(Status.JUNIOR_STUDENT)
                .build());
        studentDao.save(Student.builder()
                .lastName("Chan")
                .firstName("Jacky")
                .dateOfBirth(HelperClass.buildDate(1982, Calendar.MAY, 9))
                .absences(3)
                .departmentId("Dept2")
                .status(Status.JUNIOR_STUDENT)
                .build());
        studentDao.save(Student.builder()
                .lastName("Parker")
                .firstName("Peter")
                .dateOfBirth(HelperClass.buildDate(1990, Calendar.OCTOBER, 15))
                .absences(7)
                .departmentId("Dept3")
                .status(Status.MEDIUM_STUDENT)
                .build());
        studentDao.save(Student.builder()
                .lastName("Murdock")
                .firstName("Matt")
                .dateOfBirth(HelperClass.buildDate(2000, Calendar.FEBRUARY, 2))
                .absences(2)
                .departmentId("Dept4")
                .status(Status.ADVANCED_STUDENT)
                .build());
    }
}
