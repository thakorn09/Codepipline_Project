package com.nondt.backend.Controller;

import com.nondt.backend.Entity.*;
import com.nondt.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class TypemedicineController {

    @Autowired
    private TypemedicineRepository typemedicineRepository;

    public TypemedicineController(TypemedicineRepository typemedicineRepository) {
        this.typemedicineRepository = typemedicineRepository;
    }

    @GetMapping("/typemedicine")
    public Collection<Typemedicine> Typemedicine() {
        return typemedicineRepository.findAll().stream().collect(Collectors.toList());
    }

}