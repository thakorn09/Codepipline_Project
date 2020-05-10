package com.nondt.backend.Controller;

import com.nondt.backend.Entity.*;
import com.nondt.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmenttimeRepository appointmenttimeRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PatientManagementRepository patientManagementRepository;


    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/appointment")
    public Collection<Appointment> appointment() {
        return appointmentRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/appointment/{profileSelect}/{patSelect}/{dateSelect}/{timeappointmentSelect}/{remark}")
    public void newAppointment(
        
                        
                        @PathVariable long profileSelect,
                        @PathVariable long patSelect,
                        @PathVariable String dateSelect,
                        @PathVariable long timeappointmentSelect,
                        @PathVariable String remark)
                  {

                Profile profile = profileRepository.findById(profileSelect);
                PatientManagement patientManagement = patientManagementRepository.findById(patSelect);
                Appointmenttime appointmenttime = appointmenttimeRepository.findById(timeappointmentSelect);
                Appointment appointment = new Appointment();
                appointment.setCause(remark);
                appointment.setPatientManagement(patientManagement);
                appointment.setProfile(profile);
                appointment.setAppointmenttime(appointmenttime);

                String[] b = dateSelect.split(" ");
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
            LocalDate today = LocalDate.now();
            appointment.setDateap(localDate);
            appointment.setDatetoday(today);
            appointmentRepository.save(appointment);

    }
    

}