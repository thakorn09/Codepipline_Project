package com.nondt.backend.Repository;


import com.nondt.backend.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RepositoryRestResource

public

interface WorktimeRepository extends JpaRepository<Worktime, Long> {

	Worktime findById(long id);

}