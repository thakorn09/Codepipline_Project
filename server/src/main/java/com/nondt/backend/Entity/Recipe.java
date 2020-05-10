package com.nondt.backend.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @NotNull
    private  Long number;
    
    @Max(100)
    @Min(1)
    @NotNull
    private   Long amount;


    @FutureOrPresent
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    
    @ManyToOne
    private Profile profile;
    @ManyToOne
    private PatientManagement patientManagement;
    @ManyToOne
    private Medicine medicine;
    @ManyToOne
    private Typemedicine typemedicine;
    @ManyToOne
    private Typepacking typepacking;

    public Recipe(){}
        


    /*public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    */

}
