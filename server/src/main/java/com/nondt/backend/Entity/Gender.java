package com.nondt.backend.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
public class Gender {


	@Id
	@SequenceGenerator(name = "Genderseq", sequenceName = "Genderseq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Genderseq")
	private   Long gender_id;
	private   String gender;

	public Gender(){}
}
