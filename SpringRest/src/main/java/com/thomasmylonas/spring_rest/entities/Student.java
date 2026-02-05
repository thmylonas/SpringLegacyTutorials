package com.thomasmylonas.spring_rest.entities;

import com.thomasmylonas.spring_rest.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity(name = "Student")
@Table(name = "Students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Students_Sequence_Generator")
    @SequenceGenerator(name = "Students_Sequence_Generator", sequenceName = "Students_Sequence", allocationSize = 1)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Last_Name", length = 30)
    private String lastName;

    @Column(name = "First_Name", length = 20)
    private String firstName;

    @Column(name = "Date_Of_Birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "Absences")
    private Integer absences;

    @Column(name = "Department_Id", length = 10)
    private String departmentId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "Status", length = 20)
    private Status status;
}
