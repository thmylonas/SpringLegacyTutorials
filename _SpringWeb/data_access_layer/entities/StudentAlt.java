package com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.entities;

import com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.entities.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "StudentAlt")
@Table(name = "student_alt", schema = "schooldb")
public class StudentAlt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_alt_generator")
    @SequenceGenerator(name = "student_alt_generator", sequenceName = "student_alt_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "absences")
    private Integer absences;

    @Column(name = "department_id", length = 10)
    private String departmentId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", length = 20)
    private Status status;

    public StudentAlt() {
    }

    private StudentAlt(Builder builder) {
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.dateOfBirth = builder.dateOfBirth;
        this.absences = builder.absences;
        this.departmentId = builder.departmentId;
        this.status = builder.status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAbsences() {
        return absences;
    }

    public void setAbsences(Integer absences) {
        this.absences = absences;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentAlt{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", absences=" + absences +
                ", departmentId='" + departmentId + '\'' +
                ", status=" + status +
                '}';
    }

    public static class Builder {

        private Long id;
        private String lastName;
        private String firstName;
        private Date dateOfBirth;
        private Integer absences;
        private String departmentId;
        private Status status;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder dateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder absences(Integer absences) {
            this.absences = absences;
            return this;
        }

        public Builder departmentId(String departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public StudentAlt build() {
            return new StudentAlt(this);
        }
    }
}
