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
public class EmployeeController {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employee/{employeeusername}/{employeepassword}")
    public Employee employee(@PathVariable String employeeusername, @PathVariable String employeepassword) {
        return employeeRepository.findAll().stream()
                .filter(s -> s.getEmployeeusername().equals(employeeusername) && s.getEmployeepassword().equals(employeepassword))
                .collect(Collectors.toList()).get(0);
    }
}