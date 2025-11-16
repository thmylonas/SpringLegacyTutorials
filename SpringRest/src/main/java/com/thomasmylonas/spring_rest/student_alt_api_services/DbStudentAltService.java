package com.thomasmylonas.spring_rest.student_alt_api_services;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.exceptions.ResourceNotFoundException;
import com.thomasmylonas.spring_rest.repositories.StudentDao;
import com.thomasmylonas.spring_rest.student_alt_api_services._base.AbstractStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service(value = "dbStudentService")
public class DbStudentAltService extends AbstractStudentService {

    private final StudentDao studentDao;

    @Autowired
    public DbStudentAltService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @PostConstruct
    private void init() {
        initStudentsList();
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
    public Student saveStudent(Student student) {

        if (student == null) {
            return null;
        }
        return studentDao.save(student);
    }

    @Override
    public Student updateStudent(Student student, Long id) throws ResourceNotFoundException {

        if (id == null || student == null) {
            return null;
        }
        Student studentToBeUpdated = null; //studentDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(Student.class.getSimpleName(), id));
        updateStudentWithGivenObject(studentToBeUpdated, student);
        return studentDao.save(studentToBeUpdated);
    }

    @Override
    public void deleteStudent(Long id) throws ResourceNotFoundException {

        if (id == null) {
            return;
        }
        studentDao.deleteById(id);
    }

    private void initStudentsList() {
        List<Student> students = TestDataProvider.STUDENTS;
        studentDao.saveAll(students);
    }
}
