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

public class Medicine {
    @Id
    @SequenceGenerator(name = "Medicine_SEQ", sequenceName = "Medicine_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Medicine_SEQ")
    private  Long medicine_id;
    private  String medicine_name;

    public Medicine(){}
    
    /*public Long getMedicine_id() {
        return medicin_id;
    }
    public void setMedicine_id(Long medicin_id) {
        this.medicin_id = medicin_id;
    }

    public String getMedicine_name() {
        return medicin_name;
    }
    public void setMedicine_name(String medicin_name) {
        this.medicin_name = medicin_name;
    }
    */
}