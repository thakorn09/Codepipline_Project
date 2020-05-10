package com.nondt.backend.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Schedule")
public class Schedule {
    @Id    
    @SequenceGenerator(name="schedule_SEQ",sequenceName="schedule_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="schedule_SEQ")
    @Column(name="SCHEDULE_Id",unique = true, nullable = true)
    private @NonNull Long scheduleId;
   

   @FutureOrPresent
   @NotNull
   @DateTimeFormat(pattern = "yyyy-MM-dd")
    private   LocalDate ScheduleDate;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Profile.class)
    @JoinColumn(name = "ProfileId", insertable = true)
    private  Profile profile_id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Department.class)
    @JoinColumn(name = "DepartmentID", insertable = true)
    private  Department department_id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Worktime.class)
    @JoinColumn(name="WORKTIME", insertable = true)
    private Worktime worktime;
   
    



}