package com.nondt.backend.Repository;

import java.util.Collection;
import java.util.Optional;

import com.nondt.backend.Entity.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource
public interface PatientManagementRepository extends JpaRepository<PatientManagement, Long> {
    PatientManagement findById(long patient_id);
    PatientManagement findByName(String name);
    @Query( value = "SELECT * FROM PATIENT_MANAGEMENT  pm WHERE pm.name = :name  ",
            nativeQuery = true)
	Collection<PatientManagement> findByNamePatient(@Param("name") String name);

}