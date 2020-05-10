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

public class Typemedicine {
    @Id
    @SequenceGenerator(name = "Typemedicine_SEQ", sequenceName = "Typemedicine_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Typemedicine_SEQ")
    private  Long typemedicine_id;
    private  String typemedicine_name;

    public Typemedicine(){}

    /*public Long getTypemedicine_id() {
        return typemedicine_id;
    }
    public void setTypemedicine_id(Long typemedicine_id) {
        this.typemedicine_id = typemedicine_id;
    }

    public String getTypemedicine_name() {
        return typemedicine_name;
    }
    public void setTypemedicine_name(String typemedicine_name) {
        this.typemedicine_name = typemedicine_name;
    }
    */
}