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

public class Typepacking {
    @Id
    @SequenceGenerator(name = "Typepacking_SEQ", sequenceName = "Typepacking_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Typepacking_SEQ")
    private  Long typepacking_id;
    private  String typepacking_name;

    public Typepacking(){}

}