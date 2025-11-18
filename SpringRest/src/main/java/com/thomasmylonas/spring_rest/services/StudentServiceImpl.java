package com.thomasmylonas.spring_rest.services;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.helpers.TestDataProvider;
import com.thomasmylonas.spring_rest.models_dtos.comment_dtos.StudentRequestDto;
import com.thomasmylonas.spring_rest.models_dtos.comment_dtos.StudentResponseDto;
import com.thomasmylonas.spring_rest.repositories.StudentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service(value = "studentService")
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final StudentMapper studentMapper;

    @PostConstruct
    private void init() {
        initStudentsList();
    }

    @Override
    public StudentResponseDto findStudentById(Long id) {
        Student student = studentDao.findById(id); // IllegalArgumentException, ResourceNotFoundException
        return studentMapper.fromStudent(student);
    }

    @Override
    public List<StudentResponseDto> findAllStudents() {
        List<Student> students = studentDao.findAll();
        return students.stream().map(studentMapper::fromStudent).toList();
    }

    @Override
    public StudentResponseDto saveStudent(StudentRequestDto studentRequestDto) {

        if (studentRequestDto == null) {
            throw new IllegalArgumentException("The studentRequestDto is not valid (is null)!");
        }
        Student student = studentMapper.toStudent(studentRequestDto);
        Student savedStudent = studentDao.save(student); // IllegalArgumentException
        return studentMapper.fromStudent(savedStudent);
    }

    @Override
    public List<StudentResponseDto> saveAllStudents(List<StudentRequestDto> studentRequestDtos) {

        if (studentRequestDtos == null || studentRequestDtos.contains(null)) {
            throw new IllegalArgumentException("The studentRequestDtos are not valid (are null)!");
        }
        List<Student> students = studentRequestDtos.stream().map(studentMapper::toStudent).toList();
        List<Student> savedStudents = studentDao.saveAll(students);
        return savedStudents.stream().map(studentMapper::fromStudent).toList();
    }

    @Override
    public StudentResponseDto updateStudent(StudentRequestDto newStudentRequestDto, Long id) {

        if (newStudentRequestDto == null) {
            throw new IllegalArgumentException("The newStudentRequestDto is not valid (is null)!");
        }
        Student newStudent = studentMapper.toStudent(newStudentRequestDto);
        Student updatedStudent = studentDao.update(newStudent, id); // IllegalArgumentException, ResourceNotFoundException
        return studentMapper.fromStudent(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentDao.deleteById(id); // IllegalArgumentException
    }

    private void initStudentsList() {
        List<Student> students = TestDataProvider.STUDENTS;
        studentDao.saveAll(students);
    }
}
