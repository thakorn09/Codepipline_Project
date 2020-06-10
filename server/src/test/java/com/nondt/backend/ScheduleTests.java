
package com.nondt.backend;

import com.nondt.backend.Entity.*;
import com.nondt.backend.Repository.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import java.util.Date;




import java.time.LocalDate;
import java.util.Collection;
import java.time.format.DateTimeFormatter;



@DataJpaTest
public class ScheduleTests {

    private Validator validator;

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private  ProfileRepository profileRepository;
    @Autowired
    private  DepartmentRepository departmentRepository;
    @Autowired
    private  WorktimeRepository worktimeRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // insert OK
    @Test
    void B6008901_testScheduleSaveOK() {

        Profile profile = profileRepository.findById(1);
        Department department = departmentRepository.findById(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ScheduleDate = LocalDate.parse("2030-02-28",formatter);

        Worktime worktime = worktimeRepository.findById(1);


        Schedule schedule = new Schedule();
        schedule.setProfile_id(profile);
        schedule.setDepartment_id(department);
        schedule.setScheduleDate(ScheduleDate);
        schedule.setWorktime(worktime);
        schedule = scheduleRepository.saveAndFlush(schedule);

        final Optional<Schedule> found = scheduleRepository.findById(schedule.getScheduleId());
        assertEquals(ScheduleDate, found.get().getScheduleDate());
    }

    //insert Null date
   @Test
    void B6008901_testScheduleMustNotNullDate() {
        Profile profile = profileRepository.findById(1);
        Department department = departmentRepository.findById(1);
        Worktime worktime = worktimeRepository.findById(1);

        Schedule schedule = new Schedule();
        schedule.setProfile_id(profile);
        schedule.setDepartment_id(department);
        schedule.setScheduleDate(null);
        schedule.setWorktime(worktime);
        
        

        Set<ConstraintViolation<Schedule>> result = validator.validate(schedule);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Schedule> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ScheduleDate", v.getPropertyPath().toString());
        
    }

    //insert Wrong date

    @Test
    void B6008901_testScheduleWrongPast() {
        
        Profile profile = profileRepository.findById(1);
        Department department = departmentRepository.findById(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ScheduleDate = LocalDate.parse("2019-01-20",formatter);

        Worktime worktime = worktimeRepository.findById(1);

        Schedule schedule = new Schedule();
        schedule.setProfile_id(profile);
        schedule.setDepartment_id(department);
        schedule.setScheduleDate(ScheduleDate);
        schedule.setWorktime(worktime);
        
        Set<ConstraintViolation<Schedule>> result = validator.validate(schedule);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Schedule> v = result.iterator().next();
        assertEquals("must be a date in the present or in the future", v.getMessage());
        assertEquals("ScheduleDate", v.getPropertyPath().toString());
    }
  
    //inert date present ok
    @Test
    void B6008901_testSchedulePresentOK() {

        Profile profile = profileRepository.findById(1);
        Department department = departmentRepository.findById(1);
        LocalDate ScheduleDate = LocalDate.now();
        Worktime worktime = worktimeRepository.findById(1);

        Schedule schedule = new Schedule();
        
        schedule.setProfile_id(profile);
        schedule.setDepartment_id(department);
        schedule.setScheduleDate(ScheduleDate);
        schedule.setWorktime(worktime);
        
        schedule = scheduleRepository.saveAndFlush(schedule);

        final Optional<Schedule> found = scheduleRepository.findById(schedule.getScheduleId());
        assertEquals(ScheduleDate, found.get().getScheduleDate());
    }
 
  //insert Null profile
  @Test
  void B6008901_testScheduleMustNotNullName() {
      
      Department department = departmentRepository.findById(1);
      LocalDate ScheduleDate = LocalDate.now();
      Worktime worktime = worktimeRepository.findById(1);

      Schedule schedule = new Schedule();
      schedule.setProfile_id(null);
      schedule.setDepartment_id(department);
      schedule.setScheduleDate(ScheduleDate);
      schedule.setWorktime(worktime);
      
      

      Set<ConstraintViolation<Schedule>> result = validator.validate(schedule);
      // result ต้องมี error 1 ค่าเท่านั้น
      assertEquals(1, result.size());

      // error message ตรงชนิด และถูก field
      ConstraintViolation<Schedule> v = result.iterator().next();
      assertEquals("must not be null", v.getMessage());
      assertEquals("profile_id", v.getPropertyPath().toString());
      
  }

  //insert Null department
  @Test
  void B6008901_testScheduleMustNotNullDepartment() {

      Profile profile = profileRepository.findById(1);
      LocalDate ScheduleDate = LocalDate.now();
      Worktime worktime = worktimeRepository.findById(1);

      Schedule schedule = new Schedule();
      schedule.setProfile_id(profile);
      schedule.setDepartment_id(null);
      schedule.setScheduleDate(ScheduleDate);
      schedule.setWorktime(worktime);
      
      

      Set<ConstraintViolation<Schedule>> result = validator.validate(schedule);
      // result ต้องมี error 1 ค่าเท่านั้น
      assertEquals(1, result.size());

      // error message ตรงชนิด และถูก field
      ConstraintViolation<Schedule> v = result.iterator().next();
      assertEquals("must not be null", v.getMessage());
      assertEquals("department_id", v.getPropertyPath().toString());
      
  }
    
     //insert Null worktime
  @Test
  void B6008901_testScheduleMustNotNullWorktime() {

      Profile profile = profileRepository.findById(1);
      Department department = departmentRepository.findById(1);
      LocalDate ScheduleDate = LocalDate.now();
     

      Schedule schedule = new Schedule();
      schedule.setProfile_id(profile);
      schedule.setDepartment_id(department);
      schedule.setScheduleDate(ScheduleDate);
      schedule.setWorktime(null);
      
      

      Set<ConstraintViolation<Schedule>> result = validator.validate(schedule);
      // result ต้องมี error 1 ค่าเท่านั้น
      assertEquals(1, result.size());

      // error message ตรงชนิด และถูก field
      ConstraintViolation<Schedule> v = result.iterator().next();
      assertEquals("must not be null", v.getMessage());
      assertEquals("worktime", v.getPropertyPath().toString());
      
  }

}
