package com.thomasmylonas.spring_rest.services;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.spring_rest.helpers.TestDataProvider;
import com.thomasmylonas.spring_rest.dtos.student_dtos.StudentRequestDto;
import com.thomasmylonas.spring_rest.dtos.student_dtos.StudentResponseDto;
import com.thomasmylonas.spring_rest.daos.StudentDao;
import com.thomasmylonas.spring_rest.services.mappers.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
    public StudentResponseDto findStudentById(Long id) throws IllegalArgumentException, RequestedResourceNotFoundException {
        Student student = studentDao.findById(id); // IllegalArgumentException, RequestedResourceNotFoundException
        return studentMapper.fromStudent(student);
    }

    @Override
    public List<StudentResponseDto> findAllStudents() {
        List<Student> students = studentDao.findAll();
        return students.stream().map(studentMapper::fromStudent).toList();
    }

    @Override
    public StudentResponseDto saveStudent(StudentRequestDto studentRequestDto) throws IllegalArgumentException {

        if (studentRequestDto == null) {
            throw new IllegalArgumentException("The studentRequestDto is not valid (is null)!"); // IllegalArgumentException
        }
        Student student = studentMapper.toStudent(studentRequestDto);
        Student savedStudent = studentDao.save(student); // IllegalArgumentException
        return studentMapper.fromStudent(savedStudent);
    }

    @Override
    public List<StudentResponseDto> saveAllStudents(List<StudentRequestDto> studentRequestDtos) throws IllegalArgumentException {

        if (studentRequestDtos == null || studentRequestDtos.contains(null)) {
            throw new IllegalArgumentException("The studentRequestDtos are not valid (are null)!"); // IllegalArgumentException
        }
        List<Student> students = studentRequestDtos.stream().map(studentMapper::toStudent).toList();
        List<Student> savedStudents = studentDao.saveAll(students);
        return savedStudents.stream().map(studentMapper::fromStudent).toList();
    }

    @Override
    public StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, Long id) throws IllegalArgumentException, RequestedResourceNotFoundException {

        if (studentRequestDto == null) {
            throw new IllegalArgumentException("The studentRequestDto is not valid (is null)!"); // IllegalArgumentException
        }
        Student student = studentMapper.toStudent(studentRequestDto);
        Student updatedStudent = studentDao.update(student, id); // IllegalArgumentException, RequestedResourceNotFoundException
        return studentMapper.fromStudent(updatedStudent);
    }

    @Override
    public void deleteStudentById(Long id) throws IllegalArgumentException {
        studentDao.deleteById(id); // IllegalArgumentException
    }

    private void initStudentsList() {
        List<Student> students = new ArrayList<>(TestDataProvider.STUDENTS);
        studentDao.saveAll(students);
    }
}
