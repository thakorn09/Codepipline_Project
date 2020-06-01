package com.nondt.backend;
import com.nondt.backend.Entity.*;
import com.nondt.backend.Repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class AppointmentTest {


    private Validator validator;

    @Autowired
    private  AppointmentRepository appointmentRepository;
    @Autowired
    private  ProfileRepository profileRepository;
    @Autowired
    private  PatientManagementRepository patientManagementRepository;
    @Autowired
    private  AppointmenttimeRepository appointmenttimeRepository;

    Profile profile = new Profile();
    PatientManagement patientManagement2 = new PatientManagement();
    Appointmenttime appointmenttime = new Appointmenttime();

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        long id =1 ;
        profile = profileRepository.findById(id);
        patientManagement2 = patientManagementRepository.findById(id);
        appointmenttime = appointmenttimeRepository.findById(id);
    }
    // Cause Complete
    @Test
    void testCauseOK() {
        Appointment appointment = new Appointment();


        appointment.setCause("1234");
        LocalDate date = LocalDate.now();
        appointment.setDatetoday(date);
        appointment.setDateap(date);
        appointment.setProfile(profile);
        appointment.setPatientManagement(patientManagement2);
        appointment.setAppointmenttime(appointmenttime);
        appointment = appointmentRepository.saveAndFlush(appointment);
        System.out.println("\n\n\n\n\ntestCauseOK Success"+"\n\n\n\n\n");
        System.out.println(appointment.getCause());
        Optional<Appointment> found = appointmentRepository.findById(appointment.getAppointment_id());
        assertEquals("1234", found.get().getCause());
    }

    // Cause Null
    @Test
    void b6018153_testCauseMustNotBeNull() {
        Appointment appointment = new Appointment();

        appointment.setCause(null);
        LocalDate date = LocalDate.now();
        appointment.setDatetoday(date);
        appointment.setDateap(date);
        appointment.setProfile(profile);
        appointment.setPatientManagement(patientManagement2);
        appointment.setAppointmenttime(appointmenttime);
        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);
        System.out.println("\n\n\n\n\ntestCauseMustNotBeNull Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("cause", v.getPropertyPath().toString());
    }

    //Test Cause สั้นเกิน
    @Test
    void b6018153_testCauseShort() {
        Appointment appointment = new Appointment();
        appointment.setCause("12");
        LocalDate date = LocalDate.now();
        appointment.setDatetoday(date);
        appointment.setDateap(date);
        appointment.setProfile(profile);
        appointment.setPatientManagement(patientManagement2);
        appointment.setAppointmenttime(appointmenttime);

        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);
        System.out.println("\n\n\n\n\ntestCauseShort Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must match \"[ก-๛A-Za-z0-9]{3,100}\"", v.getMessage());
        assertEquals("cause", v.getPropertyPath().toString());
    }

    //Test Cause ยาวเกิน
    @Test
    void b6018153_testCauseOver() {
        Appointment appointment = new Appointment();
        appointment.setCause("12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc" +
                "12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc" +
                "12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc" +
                "12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc" +
                "12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc" +
                "12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc" +
                "12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc12sasacasc4as6d546a5sc46a5s4c56as4c65a46s5cascasc");

        LocalDate date = LocalDate.now();
        appointment.setDatetoday(date);
        appointment.setDateap(date);
        appointment.setProfile(profile);
        appointment.setPatientManagement(patientManagement2);
        appointment.setAppointmenttime(appointmenttime);

        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);
        System.out.println("\n\n\n\n\ntestCauseOver Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must match \"[ก-๛A-Za-z0-9]{3,100}\"", v.getMessage());
        assertEquals("cause", v.getPropertyPath().toString());
    }

    @Test
    void B6018153_testDateSaveOK() {
        Appointment appointment = new Appointment();

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate dateap = LocalDate.parse("2021-05-30",formatter);

        appointment.setCause("ปวดหัว");
        LocalDate date = LocalDate.now();
        appointment.setDatetoday(date);
        appointment.setDateap(dateap);
        appointment.setProfile(profile);
        appointment.setPatientManagement(patientManagement2);
        appointment.setAppointmenttime(appointmenttime);

        appointment = appointmentRepository.saveAndFlush(appointment);

        final Optional<Appointment> found = appointmentRepository.findById(appointment.getAppointment_id());
        assertEquals(dateap, found.get().getDateap());
    }
     //insert Null
   @Test
   void B6018153_testDatenull() {
    Appointment appointment = new Appointment();

       appointment.setDateap(null);
       appointment.setCause("ป่วย");
       LocalDate date = LocalDate.now();
       appointment.setDatetoday(date);
       appointment.setProfile(profile);
       appointment.setPatientManagement(patientManagement2);
       appointment.setAppointmenttime(appointmenttime);
       Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Appointment> v = result.iterator().next();
       assertEquals("must not be null", v.getMessage());
       assertEquals("dateap", v.getPropertyPath().toString());
       
   }

   //insert Wrong date

   @Test
   void B6018153_testDateWrongPast() {
       Appointment appointment = new Appointment();
   
       final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       final LocalDate dateap = LocalDate.parse("2019-01-20",formatter);
       appointment.setDateap(dateap);
       appointment.setCause("ป่วย");
       LocalDate date = LocalDate.now();
       appointment.setDatetoday(date);
       appointment.setProfile(profile);
       appointment.setPatientManagement(patientManagement2);
       appointment.setAppointmenttime(appointmenttime);
       
       Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Appointment> v = result.iterator().next();
       assertEquals("must be a date in the present or in the future", v.getMessage());
       assertEquals("dateap", v.getPropertyPath().toString());
   }
   
   //inert date present ok
   @Test
   void B6018153_testDatePresentOK() {
     Appointment appointment = new Appointment();

       //final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       final LocalDate dateap = LocalDate.now();
       appointment.setCause("1234");
       LocalDate date = LocalDate.now();
       appointment.setDatetoday(date);
       appointment.setProfile(profile);
       appointment.setPatientManagement(patientManagement2);
       appointment.setAppointmenttime(appointmenttime);
       appointment.setDateap(dateap);
       appointment = appointmentRepository.saveAndFlush(appointment);


       final Optional<Appointment> found = appointmentRepository.findById(appointment.getAppointment_id());
       assertEquals(appointment, found.get());
   }



}