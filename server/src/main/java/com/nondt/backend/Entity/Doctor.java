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
@Table(name = "Doctor")
public class Doctor {
	@Id
	@SequenceGenerator(name = "Doctor_seq", sequenceName = "Doctor_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Doctor_seq")
	@Column(name = "Doctor_ID", unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String doctorusername;
	private @NonNull String doctorpassword;

}