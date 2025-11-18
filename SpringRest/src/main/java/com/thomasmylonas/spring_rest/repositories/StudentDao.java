package com.thomasmylonas.spring_rest.repositories;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.exceptions.ResourceNotFoundException;
import com.thomasmylonas.spring_rest.helpers.HelperClass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Component(value = "studentDao")
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class StudentDao {

    private static final String ROLLBACK_MESSAGE = "Because of an 'Exception': '{}', the transaction is rolled back!";
    private static EntityManager em;

    private final EntityManagerFactory entityManagerFactory;

    @PostConstruct
    private void init() {
        em = createEntityManager();
    }

    public Student findById(Long id) throws IllegalArgumentException, ResourceNotFoundException {

        if (id == null || id < 0) {
            throw new IllegalArgumentException("The id is not valid!");
        }
        Student entityResult = em.find(Student.class, id);
        if (entityResult == null) {
            throw new ResourceNotFoundException(Student.class.getSimpleName(), id);
        }
        return entityResult;
    }

    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

    public Student save(Student student) throws IllegalArgumentException {

        if (student == null) {
            throw new IllegalArgumentException("The student is not valid (is null)!");
        }
        try {
            em.getTransaction().begin();

            em.persist(student); // After "EntityManager::persist" the "entity" contains the "ID" with which is persisted
            em.flush();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error(ROLLBACK_MESSAGE, e.getMessage());
            return student; // The initial "student" (without "id")
        }
        return student; // The saved Student (contains the auto-generated "id", after "EntityManager::persist/EntityManager::flush")
    }

    public List<Student> saveAll(List<Student> students) {

        try {
            em.getTransaction().begin();

            students.forEach(em::persist); // "student -> em.persist(student)"
            em.flush();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error(ROLLBACK_MESSAGE, e.getMessage());
            return students; // The initial "students" (without "ids")
        }
        return students; // The saved Students (contain the auto-generated "ids", after "EntityManager::persist/EntityManager::flush")
    }

    public Student update(Student student, Long id) throws IllegalArgumentException, ResourceNotFoundException {

        Student updatedStudent = null;
        if (id == null || id < 0 || student == null) {
            throw new IllegalArgumentException("The arguments are not valid!");
        }
        try {
            em.getTransaction().begin();

            Student studentToUpdate = findById(id); // ResourceNotFoundException (IllegalArgumentException: will never be thrown)
            updatedStudent = updateStudentWithGivenObject(studentToUpdate, student);

            em.getTransaction().commit();
        } catch (ResourceNotFoundException e) {
            em.getTransaction().rollback();
            log.error(ROLLBACK_MESSAGE + " - The Student to update, is not found", e.getMessage());
            throw new ResourceNotFoundException(Student.class.getSimpleName(), id);
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error(ROLLBACK_MESSAGE, e.getMessage());
        }
        return updatedStudent; // It can never be null
    }

    public void deleteById(Long id) throws IllegalArgumentException {

        if (id == null || id < 0) {
            throw new IllegalArgumentException("The id is not valid!");
        }
        try {
            em.getTransaction().begin();

            Student entityToDelete = findById(id); // ResourceNotFoundException (IllegalArgumentException: will never be thrown)
            em.remove(entityToDelete);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error(ROLLBACK_MESSAGE, e.getMessage());
        }
    }

    private EntityManager createEntityManager() {

        try {
            return entityManagerFactory.createEntityManager(); // IllegalStateException
        } catch (Exception e) {
            throw new RuntimeException("Error creating EntityManager", e);
        }
    }

    private Student updateStudentWithGivenObject(Student studentToUpdate, Student student) {

        if (!HelperClass.isStringNullOrEmpty(student.getLastName())) {
            studentToUpdate.setLastName(student.getLastName());
        }
        if (!HelperClass.isStringNullOrEmpty(student.getFirstName())) {
            studentToUpdate.setFirstName(student.getFirstName());
        }
        if (student.getDateOfBirth() != null) {
            studentToUpdate.setDateOfBirth(student.getDateOfBirth());
        }
        if (student.getAbsences() != null) {
            studentToUpdate.setAbsences(student.getAbsences());
        }
        if (!HelperClass.isStringNullOrEmpty(student.getDepartmentId())) {
            studentToUpdate.setDepartmentId(student.getDepartmentId());
        }
        if (student.getStatus() != null) {
            studentToUpdate.setStatus(student.getStatus());
        }
        return studentToUpdate; // The updated Student
    }
}
