package com.thomasmylonas.jsf_primefaces_tutorials.data_access_layer.repositories;

import com.thomasmylonas.jsf_primefaces_tutorials.data_access_layer.entities.Student;
import com.thomasmylonas.jsf_primefaces_tutorials.helpers.HelperClass;
import com.thomasmylonas.jsf_primefaces_tutorials.service_layer.services.exceptions.ResourceNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@ManagedBean(name = "studentDao")
@SessionScoped
@Getter
@Setter
public class StudentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDao.class.getSimpleName());
    private static final String PERSISTENCE_UNIT_NAME = "StudentPU_H2";

    private EntityManager em;

    @PostConstruct
    private void init() {
        em = createEntityManager(PERSISTENCE_UNIT_NAME);
    }

    public Student findById(Long id) {

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

    public Student save(Student student) {

        if (student == null) {
            throw new IllegalArgumentException("The student is not valid (is null)!");
        }
        try {
            em.getTransaction().begin();

            em.persist(student); // After "EntityManager::persist" the "entity" contains the "ID" with which is persisted

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error("Because of an 'Exception': '{}', the transaction is rolled back!", e.getMessage());
        }
        return student;
    }

    public void saveAll(List<Student> entities) {

        try {
            em.getTransaction().begin();

            entities.forEach(em::persist); // "student -> em.persist(student)"

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error("Because of an 'Exception': '{}', the transaction is rolled back!", e.getMessage());
        }
    }

    public void update(Student student, Long id) {

        if (id == null || id < 0 || student == null) {
            throw new IllegalArgumentException("The arguments are not valid!");
        }
        try {
            em.getTransaction().begin();

            Student studentToUpdate = findById(id); // IllegalArgumentException, ResourceNotFoundException
            updateStudentWithGivenObject(studentToUpdate, student);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error("Because of an 'Exception': '{}', the transaction is rolled back!", e.getMessage());
        }
    }

    public void deleteById(Long id) {

        if (id == null || id < 0) {
            throw new IllegalArgumentException("The id is not valid!");
        }
        try {
            em.getTransaction().begin();

            Student entityToDelete = findById(id); // IllegalArgumentException, ResourceNotFoundException
            em.remove(entityToDelete);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error("Because of an 'Exception': '{}', the transaction is rolled back!", e.getMessage());
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

    protected void updateStudentWithGivenObject(Student studentToUpdate, Student student) {

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
