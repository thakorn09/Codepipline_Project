package com.nondt.backend.Dataloader;

import java.util.stream.Stream;

import com.nondt.backend.Entity.*;
import com.nondt.backend.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DoctorDataloader implements ApplicationRunner {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Doctor doctor = new Doctor();
		doctor.setDoctorusername("Doctor");
		doctor.setDoctorpassword("Doctor");
		doctorRepository.save(doctor);

		doctorRepository.findAll().forEach(System.out::println);



	};
}