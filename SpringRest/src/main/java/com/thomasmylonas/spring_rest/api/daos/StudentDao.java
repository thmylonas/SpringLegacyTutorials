package com.thomasmylonas.spring_rest.api.daos;

import com.thomasmylonas.spring_rest.api.entities.Student;
import com.thomasmylonas.spring_rest.api.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.spring_rest.api.exceptions.RollbackException;
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

/**
 * A basic implementation of a legacy "DAO" class, with the next features:
 * - Based on "JPA/Hibernate v5.x.x" (or newer versions),
 * - Without the use of "Spring_Data_JPA" ("org.springframework.data.jpa.repository.JpaRepository<T, ID>")
 * - Only the basic "CRUD" operations are implemented (ideal for Restful_Services/APIs)
 * <p>
 * This class is used in the next projects:
 * - The "SpringLegacyTutorials/SpringRest"
 * - The "JsfPrimefacesTutorials"
 */
@Component(value = "studentDao")
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class StudentDao {

    private final EntityManagerFactory entityManagerFactory; // Autowired by "@RequiredArgsConstructor" (as final)
    private EntityManager em; // Created into "StudentDao::init" (NOT autowired - must NOT be final - "@RequiredArgsConstructor")

    @PostConstruct
    private void init() {
        em = createEntityManager();
    }

    /**
     * The method finds a Student with ID "id"
     *
     * @param id The ID of the Student to find
     * @return The Student with ID "id"
     * @throws IllegalArgumentException           If the "id" is not valid
     * @throws RequestedResourceNotFoundException If the Student with ID "id", is not found
     */
    public Student findById(Long id) throws IllegalArgumentException, RequestedResourceNotFoundException {

        if (id == null || id < 0) {
            throw new IllegalArgumentException("The id is not valid!"); // IllegalArgumentException
        }
        Student student = em.find(Student.class, id); // IllegalArgumentException
        if (student == null) {
            throw new RequestedResourceNotFoundException(Student.class.getSimpleName(), id); // RequestedResourceNotFoundException
        }
        return student; // It can never be null
    }

    /**
     * The method finds all the Students
     *
     * @return All the Students
     */
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

    /**
     * The method saves a Student in the DB
     * If "EntityTransaction::commit" happens, the saved "student" (containing the auto-generated "id", after "EntityManager::persist/EntityManager::flush") is returned
     * If "EntityTransaction::rollback" happens, the initial "student" (without "id") is returned
     *
     * @param student The "student" to save
     * @return The saved "student" which contains the auto-generated "id" or the initial "student" (without "id")
     * @throws IllegalArgumentException If the "student" is not valid
     * @throws RollbackException        If any other error occurs
     */
    public Student save(Student student) throws IllegalArgumentException, RollbackException {

        if (student == null) {
            throw new IllegalArgumentException("The student is not valid (is null)!"); // IllegalArgumentException
        }
        try {
            em.getTransaction().begin();

            em.persist(student); // After "EntityManager::persist" the "student" contains the "ID" with which is persisted
            em.flush();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error(rollbackMessage("{}"), e.getMessage());
            throw new RollbackException(rollbackMessage(e.getMessage()), e); // RollbackException
        }
        return student; // see method's documentation
    }

    /**
     * The method saves a list of Students in the DB
     * If "EntityTransaction::commit" happens, the saved "students" (containing the auto-generated "ids", after "EntityManager::persist/EntityManager::flush") are returned
     * If "EntityTransaction::rollback" happens, the initial "students" (without "ids") are returned
     *
     * @param students The "students" to save
     * @return The saved "students" which contain the auto-generated "ids" or the initial "students" (without "ids")
     * @throws IllegalArgumentException If the "students" are not valid
     * @throws RollbackException        If any other error occurs
     */
    public List<Student> saveAll(List<Student> students) throws IllegalArgumentException, RollbackException {

        if (students == null || students.isEmpty() || students.contains(null)) {
            throw new IllegalArgumentException("The students are not valid!"); // IllegalArgumentException
        }
        try {
            em.getTransaction().begin();

            students.forEach(em::persist); // "student -> em.persist(student)"
            em.flush();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error(rollbackMessage("{}"), e.getMessage());
            throw new RollbackException(rollbackMessage(e.getMessage()), e); // RollbackException
        }
        return students; // see method's documentation
    }

    /**
     * The method updates a Student with ID "id", with the "student"
     *
     * @param student The "student" used to update, the Student with ID "id"
     * @param id      The ID of the Student to update
     * @return The updated Student
     * @throws IllegalArgumentException           If the "id" or "student" are not valid
     * @throws RequestedResourceNotFoundException If the Student with ID "id", is not found
     * @throws RollbackException                  If any other error occurs
     */
    public Student update(Student student, Long id) throws IllegalArgumentException, RequestedResourceNotFoundException, RollbackException {

        Student updatedStudent = null;
        if (id == null || id < 0 || student == null) {
            throw new IllegalArgumentException("The arguments are not valid!"); // IllegalArgumentException
        }
        try {
            em.getTransaction().begin();

            Student studentToUpdate = findById(id); // IllegalArgumentException, RequestedResourceNotFoundException
            updatedStudent = updateStudentWithGivenObject(studentToUpdate, student);

            em.getTransaction().commit();
        } catch (RequestedResourceNotFoundException e) {
            em.getTransaction().rollback();
            log.error(rollbackMessage("{}"), e.getMessage());
            log.error("The Student to update, is not found!");
            throw new RequestedResourceNotFoundException(Student.class.getSimpleName(), id); // RequestedResourceNotFoundException
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error(rollbackMessage("{}"), e.getMessage());
            throw new RollbackException(rollbackMessage(e.getMessage()), e); // RollbackException
        }
        return updatedStudent; // It can never be null
    }

    /**
     * The method deletes a Student with ID "id"
     *
     * @param id The ID of the Student to delete
     * @throws IllegalArgumentException           If the "id" is not valid
     * @throws RequestedResourceNotFoundException If the Student with ID "id", is not found
     * @throws RollbackException                  If any other error occurs
     */
    public void deleteById(Long id) throws IllegalArgumentException, RequestedResourceNotFoundException, RollbackException {

        if (id == null || id < 0) {
            throw new IllegalArgumentException("The id is not valid!"); // IllegalArgumentException
        }
        try {
            em.getTransaction().begin();

            Student studentToDelete = findById(id); // IllegalArgumentException, RequestedResourceNotFoundException
            em.remove(studentToDelete);

            em.getTransaction().commit();
        } catch (RequestedResourceNotFoundException e) {
            em.getTransaction().rollback();
            log.error(rollbackMessage("{}"), e.getMessage());
            log.error("The Student to delete, is not found!");
            throw new RequestedResourceNotFoundException(Student.class.getSimpleName(), id); // RequestedResourceNotFoundException
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error(rollbackMessage("{}"), e.getMessage());
            throw new RollbackException(rollbackMessage(e.getMessage()), e); // RollbackException
        }
    }

    private EntityManager createEntityManager() {

        try {
            return entityManagerFactory.createEntityManager(); // IllegalStateException
        } catch (Exception e) {
            throw new RuntimeException("Error creating EntityManager!", e);
        }
    }

    private String rollbackMessage(String exceptionMessage) {
        return String.format("Because of an 'Exception': '%s', the transaction is rolled back!", exceptionMessage);
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
