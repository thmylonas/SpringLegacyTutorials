package com.thomasmylonas.spring_rest.student_alt_api_services;

import com.thomasmylonas.jsf_primefaces_tutorials.data_access_layer.entities.Student;
import com.thomasmylonas.jsf_primefaces_tutorials.data_access_layer.repositories.StudentDao;
import com.thomasmylonas.jsf_primefaces_tutorials.helpers.TestDataProvider;
import com.thomasmylonas.jsf_primefaces_tutorials.service_layer.services._base.AbstractService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "dbStudentService")
@SessionScoped
@Getter
@Setter
public class DbStudentService extends AbstractService implements StudentService {

    @ManagedProperty(value = "#{studentDao}")
    private StudentDao studentDao;

    private TestDataProvider testDataProvider;

    @PostConstruct
    private void init() {
        testDataProvider = new TestDataProvider();
        initStudentsList();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentDao.findById(id); // IllegalArgumentException, ResourceNotFoundException
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public Student saveStudent(Student student) {

        if (student == null) {
            throw new IllegalArgumentException("The student is not valid (is null)!");
        }
        Student studentToSave = Student.builder()
                .lastName(student.getLastName())
                .firstName(student.getFirstName())
                .dateOfBirth(student.getDateOfBirth())
                .absences(student.getAbsences())
                .departmentId(student.getDepartmentId())
                .status(student.getStatus())
                .build();
        return studentDao.save(studentToSave); // IllegalArgumentException
    }

    @Override
    public void updateStudent(Student student, Long id) {
        studentDao.update(student, id); // IllegalArgumentException, ResourceNotFoundException
    }

    @Override
    public void deleteStudent(Long id) {
        studentDao.deleteById(id); // IllegalArgumentException, ResourceNotFoundException
    }

    private void initStudentsList() {
        List<Student> students = testDataProvider.generateDBStudents();
        studentDao.saveAll(students);
    }
}
