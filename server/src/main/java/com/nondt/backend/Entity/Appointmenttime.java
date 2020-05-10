package com.nondt.backend.Entity;

import java.sql.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Getter
@Setter
public class Appointmenttime{
    @Id
	@SequenceGenerator(name = "appotime_seq", sequenceName = "appotime_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appotime_seq")
    private long appointmenttime_id;

    private String timeap;

    public Appointmenttime(){}
}