package com.nondt.backend.Entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.FutureOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Appointment{

    @Id
	@SequenceGenerator(name = "appo_seq", sequenceName = "appo_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appo_seq")
    private long appointment_id;


    @NotNull
    @Pattern(regexp = "[ก-๛A-Za-z0-9]{3,100}")
    private String cause;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate datetoday;
    
    @FutureOrPresent
     @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateap;
    

    @ManyToOne
    private Profile profile;

    @ManyToOne
    private PatientManagement patientManagement;

    @ManyToOne
    private Appointmenttime appointmenttime;
    
    public Appointment(){}

    public long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public LocalDate getDatetoday() {
        return datetoday;
    }

    public void setDatetoday(LocalDate datetoday) {
        this.datetoday = datetoday;
    }

    public LocalDate getDateap() {
        return dateap;
    }

    public void setDateap(LocalDate dateap) {
        this.dateap = dateap;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public PatientManagement getPatientManagement(){
        return patientManagement;
    }
    public void setPatientManagement(PatientManagement patientManagement){
        this.patientManagement = patientManagement;
    }
    public Appointmenttime getAppointmenttime() {
        return appointmenttime;
    }

    public void setAppointmenttime(Appointmenttime appointmenttime) {
        this.appointmenttime = appointmenttime;
    }
}