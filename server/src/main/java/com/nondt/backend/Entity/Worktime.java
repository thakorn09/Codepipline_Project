package com.nondt.backend.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Worktime")
public class Worktime {
    @Id    
    @SequenceGenerator(name="worktime_SEQ",sequenceName="worktime_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="worktime_SEQ")
    @Column(name="WORKTIME_Id",unique = true, nullable = true)
    private @NonNull Long worktimeId;
    private @NonNull String work;
}
   