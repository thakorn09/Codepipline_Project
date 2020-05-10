package com.nondt.backend.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


import org.springframework.format.annotation.DateTimeFormat;

@Data 
@Entity 
@NoArgsConstructor
@Table(name="BookRoom")
public class BookRoom {

    @Id
    @SequenceGenerator(name="BookRoom_seq",sequenceName="BookRoom_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BookRoom_seq")
    @Column(name = "ID_BookRoom", unique = true, nullable = true)

    
    private @NonNull Long id;

    @NotNull
    @Size(min = 10, max = 500)
    private String note;
    
    @FutureOrPresent
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate DateOfBook;
    
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime TimeOfStart;
   
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime TimeOfEnd;
    
    @NotNull
    @Column(name="BookDate ")
    private Date BookDate;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Profile.class)
    @JoinColumn(name = "profile_id", insertable = true)
    private Profile profile;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PatientManagement.class)
    @JoinColumn(name = "patientManage_id", insertable = true)
    private PatientManagement patientManagement;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "room_id", insertable = true)
    private Room room;

}