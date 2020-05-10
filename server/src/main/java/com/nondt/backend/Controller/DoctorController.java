package com.nondt.backend.Controller;

import com.nondt.backend.Entity.*;
import com.nondt.backend.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class DoctorController {

    @Autowired
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/doctor/{doctorusername}/{doctorpassword}")
    public Doctor employee(@PathVariable String doctorusername, @PathVariable String doctorpassword) {
        return doctorRepository.findAll().stream()
                .filter(s -> s.getDoctorusername().equals(doctorusername) && s.getDoctorusername().equals(doctorpassword))
                .collect(Collectors.toList()).get(0);
    }
}