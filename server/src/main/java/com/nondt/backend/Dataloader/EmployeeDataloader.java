package com.nondt.backend.Dataloader;

import java.util.stream.Stream;

import com.nondt.backend.Entity.*;
import com.nondt.backend.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDataloader implements ApplicationRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Employee employee = new Employee();
		employee.setEmployeeusername("Employee");
		employee.setEmployeepassword("Employee");
		employeeRepository.save(employee);

		employeeRepository.findAll().forEach(System.out::println);



	};
}
