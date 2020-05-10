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
public class Room {


	@Id
	@SequenceGenerator(name = "Roomseq", sequenceName = "Roomseq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Roomseq")
	private   Long room_id;
	private   String room;

	public Room(){}
}
