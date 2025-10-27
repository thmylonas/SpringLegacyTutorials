package com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.services.api_services.student_alt_api_services._base;

import com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.entities.StudentAlt;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.helpers.HelperClass;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.services._base.AbstractService;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.service_layer.services.api_services.student_alt_api_services.StudentAltService;

public abstract class AbstractStudentAltService extends AbstractService implements StudentAltService {

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
