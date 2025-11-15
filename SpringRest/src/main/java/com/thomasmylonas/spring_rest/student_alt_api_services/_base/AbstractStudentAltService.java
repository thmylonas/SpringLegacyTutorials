package com.thomasmylonas.spring_rest.student_alt_api_services._base;

import com.thomasmylonas.spring_rest.entities.StudentAlt;
import com.thomasmylonas.spring_rest.helpers.HelperClass;
import com.thomasmylonas.spring_rest.student_alt_api_services.StudentAltService;

public abstract class AbstractStudentAltService implements StudentAltService {

    // All "interface StudentAltService"'s methods are implemented in the subclasses

    protected void updateStudentAltWithGivenObject(StudentAlt studentAltToBeUpdated, StudentAlt studentAlt) {

        if (studentAltToBeUpdated == null || studentAlt == null) {
            return;
        }
        if (!HelperClass.isStringNullOrEmpty(studentAlt.getLastName())) {
            studentAltToBeUpdated.setLastName(studentAlt.getLastName());
        }
        if (!HelperClass.isStringNullOrEmpty(studentAlt.getFirstName())) {
            studentAltToBeUpdated.setFirstName(studentAlt.getFirstName());
        }
        if (studentAlt.getDateOfBirth() != null) {
            studentAltToBeUpdated.setDateOfBirth(studentAlt.getDateOfBirth());
        }
        if (studentAlt.getAbsences() != null) {
            studentAltToBeUpdated.setAbsences(studentAlt.getAbsences());
        }
        if (!HelperClass.isStringNullOrEmpty(studentAlt.getDepartmentId())) {
            studentAltToBeUpdated.setDepartmentId(studentAlt.getDepartmentId());
        }
        if (studentAlt.getStatus() != null) {
            studentAltToBeUpdated.setStatus(studentAlt.getStatus());
        }
    }
}
