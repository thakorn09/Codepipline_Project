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
public class Department {

	@Id
	@SequenceGenerator(name = "Departmentseq", sequenceName = "Departmentseq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Departmentseq")
	private  Long department_id;
	private  String department;


	public Department(){}
}
