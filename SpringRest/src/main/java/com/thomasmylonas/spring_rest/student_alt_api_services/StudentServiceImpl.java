package com.thomasmylonas.spring_rest.student_alt_api_services;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.repositories.StudentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service(value = "studentService")
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    @PostConstruct
    private void init() {
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
        /*Student studentToSave = Student.builder()
                .lastName(student.getLastName())
                .firstName(student.getFirstName())
                .dateOfBirth(student.getDateOfBirth())
                .absences(student.getAbsences())
                .departmentId(student.getDepartmentId())
                .status(student.getStatus())
                .build();*/
        return studentDao.save(student); // IllegalArgumentException
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        studentDao.update(student, id); // IllegalArgumentException, ResourceNotFoundException
        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        studentDao.deleteById(id); // IllegalArgumentException, ResourceNotFoundException
    }

    private void initStudentsList() {
        List<Student> students = TestDataProvider.STUDENTS;
        studentDao.saveAll(students);
    }
}
