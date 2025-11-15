package com.thomasmylonas.spring_rest.repositories;

import com.thomasmylonas.spring_rest.entities.StudentAlt;
import com.thomasmylonas.spring_rest.exceptions.ResourceNotFoundException;
import com.thomasmylonas.spring_rest.helpers.HelperClass;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Component(value = "studentDao")
@Getter
@Setter
@Slf4j
public class StudentDao {

    private static final String PERSISTENCE_UNIT_NAME = "StudentPU_H2";

    private EntityManager em;

    @PostConstruct
    private void init() {
        em = createEntityManager(PERSISTENCE_UNIT_NAME);
    }

    public StudentAlt findById(Long id) {

        if (id == null || id < 0) {
            throw new IllegalArgumentException("The id is not valid!");
        }
        StudentAlt entityResult = em.find(StudentAlt.class, id);
        if (entityResult == null) {
            throw new ResourceNotFoundException(StudentAlt.class.getSimpleName(), id);
        }
        return entityResult;
    }

    public List<StudentAlt> findAll() {
        TypedQuery<StudentAlt> query = em.createQuery("select s from StudentAlt s", StudentAlt.class);
        return query.getResultList();
    }

    public StudentAlt save(StudentAlt student) {

        if (student == null) {
            throw new IllegalArgumentException("The student is not valid (is null)!");
        }
        try {
            em.getTransaction().begin();

            em.persist(student); // After "EntityManager::persist" the "entity" contains the "ID" with which is persisted

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error("Because of an 'Exception': '{}', the transaction is rolled back!", e.getMessage());
        }
        return student;
    }

    public void saveAll(List<StudentAlt> entities) {

        try {
            em.getTransaction().begin();

            entities.forEach(em::persist); // "student -> em.persist(student)"

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error("Because of an 'Exception': '{}', the transaction is rolled back!", e.getMessage());
        }
    }

    public void update(StudentAlt student, Long id) {

        if (id == null || id < 0 || student == null) {
            throw new IllegalArgumentException("The arguments are not valid!");
        }
        try {
            em.getTransaction().begin();

            StudentAlt studentToUpdate = findById(id); // IllegalArgumentException, ResourceNotFoundException
            updateStudentWithGivenObject(studentToUpdate, student);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error("Because of an 'Exception': '{}', the transaction is rolled back!", e.getMessage());
        }
    }

    public void deleteById(Long id) {

        if (id == null || id < 0) {
            throw new IllegalArgumentException("The id is not valid!");
        }
        try {
            em.getTransaction().begin();

            StudentAlt entityToDelete = findById(id); // IllegalArgumentException, ResourceNotFoundException
            em.remove(entityToDelete);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error("Because of an 'Exception': '{}', the transaction is rolled back!", e.getMessage());
        }
    }

    private EntityManager createEntityManager(String persistenceUnitName) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        try {
            return emf.createEntityManager(); // IllegalStateException
        } catch (Exception e) {
            throw new RuntimeException("Error creating EntityManager", e);
        }
    }

    protected void updateStudentWithGivenObject(StudentAlt studentToUpdate, StudentAlt student) {

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
    }
}
