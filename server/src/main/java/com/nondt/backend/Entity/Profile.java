package com.nondt.backend.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profile {

    @Id
    @SequenceGenerator(name = "Profile_seq", sequenceName = "Profile_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Profile_seq")
    private Long profile_id;

    @NotNull
    @Size(min=3,max=50)
    @Pattern(regexp = "[a-z0-9A-Zก-๙ ]*")
    private String name;

    @NotNull
    @Positive
    @Max(100)
    private Integer age;

    @NotNull
    @Pattern(regexp = "[a-z0-9A-Zก-๙- ]*")
    @Size(min=5,max=80)
    private String address;

    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String phone;

    @ManyToOne
    @NotNull
    private Gender gender;
    

    @ManyToOne
    @NotNull
    private Maritalstatus maritalstatus;

    

    @ManyToOne
    @NotNull
    private Department department;

    @ManyToOne
    @NotNull
    private Blood blood;

    public Profile(){}


}
