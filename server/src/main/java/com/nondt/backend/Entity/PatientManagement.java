package com.nondt.backend.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Entity;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name="PatientManagement")
public class PatientManagement {

    @Id
    @SequenceGenerator(name="patient_management_seq",sequenceName="patient_management_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_management_seq")  
    private  Long patient_id;

    @NotNull
    @Size(min = 3, max = 100)
    private  String patient_result;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate patientDate;
    
    @NotNull
    @Size(min = 5, max = 50)
    private  String name;

    @NotNull
    private  String title_name;

    @Min(1)
    private  int age;

    @NotNull
    @ManyToOne 
    private Profile profile; 

    @NotNull
    @ManyToOne
    private Department department;

    @NotNull
    @ManyToOne
    private Gender gender;
    
    public PatientManagement(){}

    
   
}