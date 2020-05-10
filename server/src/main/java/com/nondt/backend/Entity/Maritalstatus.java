package com.nondt.backend.Entity;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;

@Entity

@Getter
@Setter
public class Maritalstatus {


	@Id
	@SequenceGenerator(name = "Maritalstatusseq", sequenceName = "Maritalstatusseq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Maritalstatustseq")
	private  Long status_id;
	private  String status;

    public Maritalstatus(){}
}
