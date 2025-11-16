package com.thomasmylonas.spring_rest.student_alt_api_services._base;

import com.thomasmylonas.spring_rest.entities.Student;
import com.thomasmylonas.spring_rest.helpers.HelperClass;
import com.thomasmylonas.spring_rest.student_alt_api_services.StudentAltService;

public abstract class AbstractStudentService implements StudentAltService {

    // All "interface StudentService"'s methods are implemented in the subclasses

    protected void updateStudentWithGivenObject(Student studentToBeUpdated, Student student) {

        if (studentToBeUpdated == null || student == null) {
            return;
        }
        if (!HelperClass.isStringNullOrEmpty(student.getLastName())) {
            studentToBeUpdated.setLastName(student.getLastName());
        }
        if (!HelperClass.isStringNullOrEmpty(student.getFirstName())) {
            studentToBeUpdated.setFirstName(student.getFirstName());
        }
        if (student.getDateOfBirth() != null) {
            studentToBeUpdated.setDateOfBirth(student.getDateOfBirth());
        }
        if (student.getAbsences() != null) {
            studentToBeUpdated.setAbsences(student.getAbsences());
        }
        if (!HelperClass.isStringNullOrEmpty(student.getDepartmentId())) {
            studentToBeUpdated.setDepartmentId(student.getDepartmentId());
        }
        if (student.getStatus() != null) {
            studentToBeUpdated.setStatus(student.getStatus());
        }
    }
}
