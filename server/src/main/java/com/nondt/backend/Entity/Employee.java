package com.nondt.backend.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Employee")
public class Employee {
	@Id
	@SequenceGenerator(name = "Employee_seq", sequenceName = "Employee_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employee_seq")
	@Column(name = "Employee_ID", unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String employeeusername;
	private @NonNull String employeepassword;

}