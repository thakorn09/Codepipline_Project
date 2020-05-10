package com.nondt.backend.Controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.nondt.backend.Entity.*;
import com.nondt.backend.Repository.*;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController("CNS")
public class ProfileController {

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Autowired
    private final ProfileRepository profileRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private MaritalstatusRepository maritalstatusRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private BloodRepository bloodRepository;

    @GetMapping("/profile")
    public Collection<Profile> Profile() {
        return profileRepository.findAll().stream().collect(Collectors.toList());
    }
    
   /* @GetMapping("/profile/{name}")
    public Collection<Profile> getProfileByName(@PathVariable("name") String name) {
        return  profileRepository.findProfileByName(name);
    }*/
    

    @PostMapping("/profile/{departid}/{name}/{genderid}/{age}/{bloodid}/{tel}/{address}/{matid}")
    public void newProfile(
        
                        
                        @PathVariable long departid,
                        @PathVariable long genderid,
                        @PathVariable String name,
                        @PathVariable long bloodid,
                        @PathVariable Integer age,
                        @PathVariable String tel,
                        @PathVariable String address,
                        @PathVariable long matid) 
                  {

                Department department = departmentRepository.findById(departid);
                Gender gender = genderRepository.findById(genderid);
                Blood blood = bloodRepository.findById(bloodid);
                Maritalstatus maritalstatus = maritalstatusRepository.findById(matid);
                Profile profile = new Profile();
                profile.setAge(age);
                profile.setAddress(address);
                profile.setBlood(blood);
                profile.setGender(gender);
                profile.setDepartment(department);
                profile.setMaritalstatus(maritalstatus);
                profile.setName(name);
                profile.setPhone(tel);
                profileRepository.save(profile);
    }
    

}