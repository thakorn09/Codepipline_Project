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
public class Blood {

	@Id
	@SequenceGenerator(name = "Bloodseq", sequenceName = "Bloodseq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Bloodtseq")
    private long blood_id;

	private String blood;

	public Blood(){}


}
