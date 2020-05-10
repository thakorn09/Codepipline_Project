package com.nondt.backend.Controller;

import com.nondt.backend.Entity.*;
import com.nondt.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class PatientManagementController {

    @Autowired
    private PatientManagementRepository patientManagementRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    public PatientManagementController(PatientManagementRepository patientManagementRepository) {
        this.patientManagementRepository = patientManagementRepository;
    }

    @GetMapping("/patientmanagement")
    public Collection<PatientManagement> patientmanages() {
        return patientManagementRepository.findAll().stream().collect(Collectors.toList());
    }
/*
    @GetMapping("/patientmanage/")
    public Collection<PatientManagement> findByName(@PathVariable("id") String id ){
         return patientManagementRepository.findByPatientManagement(id);
     }
*/
     @GetMapping("/patientmanage/{name}")
    public Collection<PatientManagement> findByName(@PathVariable String name ){
        return patientManagementRepository.findByNamePatient(name);
     } 
 

    @PostMapping("/patientmanagement/{profileSelect}/{departSelect}/{title_name}/{name}/{genSelect}/{age}/{patient_result}/{date}")
    public void newPatientManagement(

            @PathVariable long profileSelect,
            @PathVariable long departSelect,
            @PathVariable String title_name,
            @PathVariable String name,
            @PathVariable long genSelect, 
            @PathVariable String age,
            @PathVariable String patient_result,
            @PathVariable String date) 
            {

                

                Department department = departmentRepository.findById(departSelect);
                Profile profile = profileRepository.findById(profileSelect);
                Gender gender = genderRepository.findById(genSelect);

                PatientManagement p1 = new PatientManagement();
                p1.setTitle_name(title_name);
                p1.setName(name);
                p1.setGender(gender);
                p1.setAge(Integer.valueOf(age));
                p1.setPatient_result(patient_result);
                p1.setDepartment(department);
                p1.setProfile(profile);
                String[] b = date.split(" ");
            int year = Integer.valueOf(b[3]);
            int day = Integer.valueOf(b[2]);
            int month = 1 ;

            if(b[1].equalsIgnoreCase("Jan")){ month = 1;}else if(b[1].equalsIgnoreCase("Feb")){ month = 2;}
            else if(b[1].equalsIgnoreCase("Mar")){ month = 3;}else if(b[1].equalsIgnoreCase("Mar")){ month = 4;}
            else if(b[1].equalsIgnoreCase("May")){ month = 5;}else if(b[1].equalsIgnoreCase("Jun")){ month = 6;}
            else if(b[1].equalsIgnoreCase("Jul")){ month = 7;}else if(b[1].equalsIgnoreCase("Aug")){ month = 8;}
            else if(b[1].equalsIgnoreCase("Sep")){ month = 9;}else if(b[1].equalsIgnoreCase("Oct")){ month = 10;}
            else if(b[1].equalsIgnoreCase("Nov")){ month = 11;}else if(b[1].equalsIgnoreCase("Dec")){ month = 12;}


            LocalDate localDate = LocalDate.of(year,month,day);
                p1.setPatientDate(localDate);
                patientManagementRepository.save(p1);
    }

}