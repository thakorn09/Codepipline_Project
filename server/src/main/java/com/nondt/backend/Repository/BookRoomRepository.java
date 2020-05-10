package com.nondt.backend.Repository;

import com.nondt.backend.Entity.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface BookRoomRepository extends JpaRepository<BookRoom, Long> {
    BookRoom findById(long id);
}