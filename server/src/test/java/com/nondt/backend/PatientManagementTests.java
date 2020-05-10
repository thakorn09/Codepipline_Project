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
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class PatientManagementTests {

    private Validator validator;

    @Autowired
    private PatientManagementRepository patientManagementRepository;
    @Autowired
    private  ProfileRepository profileRepository;
    @Autowired
    private  DepartmentRepository departmentRepository;
    @Autowired
    private GenderRepository genderRepository;

    
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    void b6008376_testInsertOk(){

        Department d = departmentRepository.findById(1);
        Gender g = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);

        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("กมลฉัตร กมลลานนท์");
        p.setAge(20);
        p.setPatient_result("สบายดี");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(g);
        p.setProfile(profile);
        p.setDepartment(d);
        p = patientManagementRepository.saveAndFlush(p);

        Optional<PatientManagement> found = patientManagementRepository.findById(p.getPatient_id());
        assertEquals("นางสาว", found.get().getTitle_name());
        assertEquals("กมลฉัตร กมลลานนท์", found.get().getName());
        assertEquals(20, found.get().getAge());
        assertEquals(date, found.get().getPatientDate());
        assertEquals(g, found.get().getGender());
        assertEquals(profile, found.get().getProfile());
        assertEquals(d, found.get().getDepartment());
    }

    //title_name ต้องไม่ใช่ค่าว่าง
    @Test
    void b6008376_testTitleNameMustNotNull() {

        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name(null);
        p.setName("กมลฉัตร กมลลานนท์");
        p.setAge(20);
        p.setPatient_result("สบายดี");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(gender);
        p.setProfile(profile);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testTitleNameMustNotNull Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("title_name", v.getPropertyPath().toString());
    }



    //name ต้องไม่ใช่ค่าว่าง
    @Test
    void b6008376_testNameMustNotNull() {

        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName(null);
        p.setAge(20);
        p.setPatient_result("สบายดี");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(gender);
        p.setProfile(profile);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testNameMustNotNull Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    //name มีความยาวเกิน 5 ตัวอักษร
    @Test
    void b6008376_testNameMoreThanMin() {

        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("1234");
        p.setAge(20);
        p.setPatient_result("สบายดี");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(gender);
        p.setProfile(profile);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testNameMoreThanMin Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("size must be between 5 and 50", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }



    //name มีความยาวไม่เกิน 50 ตัวอักษร
    @Test
    void b6008376_testNameLessThanMax() {

        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("123456789012345678901234567890123456789012345678901");
        p.setAge(20);
        p.setPatient_result("สบายดี");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(gender);
        p.setProfile(profile);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testNameLessThanMax Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("size must be between 5 and 50", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
    //age ต้องไม่ต่ำกว่า 0
    @Test
    void b6008376_testAgeMustBeGreaterThanOrEqualsTo1() {

        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("กมลฉัตร กมลลานนท์");
        p.setAge(0);
        p.setPatient_result("สบายดี");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(gender);
        p.setProfile(profile);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testAgeMoreThanMin Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("age", v.getPropertyPath().toString());
    }

    //patient_result ต้องไม่ใช่ค่าว่าง
    @Test
    void b6008376_testPatientResultMustNotNull() {

        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("กมลฉัตร กมลลานนท์");
        p.setAge(20);
        p.setPatient_result(null);
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(gender);
        p.setProfile(profile);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testPatientResultMustNotNull Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("patient_result", v.getPropertyPath().toString());
    }

    //patient_result มีความยาวไม่เกิน 100 ตัวอักษร
    @Test
    void b6008376_testPatientResultLessThanMax() {
        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("กมลฉัตร กมลลานนท์");
        p.setAge(20);
        p.setPatient_result("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(gender);
        p.setProfile(profile);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testPatientResultLessThanMax Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("size must be between 3 and 100", v.getMessage());
        assertEquals("patient_result", v.getPropertyPath().toString());
    }
    //patient_result มีความยาวเกิน 3 ตัวอักษร
    @Test
    void b6008376_testPatientResultMoreThanMin() {
        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("กมลฉัตร กมลลานนท์");
        p.setAge(20);
        p.setPatient_result("12");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(gender);
        p.setProfile(profile);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testPatientResultMoreThanMin Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("size must be between 3 and 100", v.getMessage());
        assertEquals("patient_result", v.getPropertyPath().toString());
    }

    //patientDate ต้องไม่ใช่ค่าว่าง
    @Test
    void b6008376_testPatientDateMustNotNull() {

        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("กมลฉัตร กมลลานนท์");
        p.setAge(20);
        p.setPatient_result("สบายดี");
        LocalDate date = LocalDate.now();
        p.setPatientDate(null);
        p.setGender(gender);
        p.setProfile(profile);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testPatientDateMustNotNull Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("patientDate", v.getPropertyPath().toString());
    }

    //combobox gender ต้องไม่ว่าง
    @Test
    void b6008376_testGenderMustNotNull() {

        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("กมลฉัตร กมลลานนท์");
        p.setAge(20);
        p.setPatient_result("สบายดี");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(null);
        p.setProfile(profile);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testGenderMustNotNull Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gender", v.getPropertyPath().toString());
    }

    //combobox profile ต้องไม่ว่าง
    @Test
    void b6008376_testProfileMustNotNull() {

        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("กมลฉัตร กมลลานนท์");
        p.setAge(20);
        p.setPatient_result("สบายดี");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(gender);
        p.setProfile(null);
        p.setDepartment(department);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testProfileMustNotNull Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("profile", v.getPropertyPath().toString());
    }    

    //combobox department ต้องไม่ว่าง
    @Test
    void b6008376_testDepartmentMustNotNull() {

        Department department = departmentRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Profile profile = profileRepository.findById(1);
        PatientManagement p = new PatientManagement();
        p.setTitle_name("นางสาว");
        p.setName("กมลฉัตร กมลลานนท์");
        p.setAge(20);
        p.setPatient_result("สบายดี");
        LocalDate date = LocalDate.now();
        p.setPatientDate(date);
        p.setGender(gender);
        p.setProfile(profile);
        p.setDepartment(null);

        Set<ConstraintViolation<PatientManagement>> result = validator.validate(p);
        System.out.println("\n\n\n\n\n testDepartmentMustNotNull Success"+"\n\n\n\n\n");
        assertEquals(1, result.size());
        ConstraintViolation<PatientManagement> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("department", v.getPropertyPath().toString());
    }   

}

