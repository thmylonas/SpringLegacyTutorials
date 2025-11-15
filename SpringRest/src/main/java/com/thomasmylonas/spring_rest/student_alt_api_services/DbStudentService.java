package com.thomasmylonas.spring_rest.student_alt_api_services;

import com.thomasmylonas.spring_rest.entities.StudentAlt;
import com.thomasmylonas.spring_rest.repositories.StudentDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "dbStudentService")
@Getter
@Setter
public class DbStudentService implements StudentService {

    @Autowired
    private StudentDao studentDao;

    private TestDataProvider testDataProvider;

    public DbStudentService() {
        testDataProvider = new TestDataProvider();
        initStudentsList();
    }

    @Override
    public StudentAlt findStudentById(Long id) {
        return studentDao.findById(id); // IllegalArgumentException, ResourceNotFoundException
    }

    @Override
    public List<StudentAlt> findAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public StudentAlt saveStudent(StudentAlt student) {

        if (student == null) {
            throw new IllegalArgumentException("The student is not valid (is null)!");
        }
        StudentAlt studentToSave = StudentAlt.builder()
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
    public void updateStudent(StudentAlt student, Long id) {
        studentDao.update(student, id); // IllegalArgumentException, ResourceNotFoundException
    }

    @Override
    public void deleteStudent(Long id) {
        studentDao.deleteById(id); // IllegalArgumentException, ResourceNotFoundException
    }

    private void initStudentsList() {
        List<StudentAlt> students = TestDataProvider.STUDENTS;
        studentDao.saveAll(students);
    }
}
