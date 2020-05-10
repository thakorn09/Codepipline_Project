package com.nondt.backend.Repository;

import java.util.Collection;

import com.nondt.backend.Entity.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RepositoryRestResource

public

interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	Schedule findById(long id);
	@Query( value = "SELECT * FROM Profile r JOIN Schedule s WHERE r.Profile_ID = s.Profile_ID and r.Name = :id",
            nativeQuery = true)
	Collection<Schedule> findBySchedule (@Param("id") String id);
	
	
}