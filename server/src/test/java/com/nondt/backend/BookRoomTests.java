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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class BookRoomTests{

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateOfBook = LocalDate.parse("2020-12-01", formatter);
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
    LocalTime timeOfStart = LocalTime.parse("00:00", formatter2);
    LocalTime timeOfEnd = LocalTime.parse("00:00", formatter2);
    Date bookDate = new Date();


    private Validator validator;
        
    @Autowired
    private BookRoomRepository bookRoomRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PatientManagementRepository patientManagementRepository;

    @Autowired
    private RoomRepository roomRepository;

    
    @BeforeEach
    public void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6012755_testBookRoomOKFullData(){
        BookRoom bookRoom = new BookRoom();
        Profile profile = profileRepository.findById(1);
        PatientManagement patientManagement = patientManagementRepository.findById(1);
        Room room = roomRepository.findById(1);
        bookRoom.setNote("Test1Test1");
        bookRoom.setDateOfBook(dateOfBook);
        bookRoom.setTimeOfStart(timeOfStart);
        bookRoom.setTimeOfEnd(timeOfEnd);
        bookRoom.setBookDate(bookDate);
        bookRoom.setProfile(profile);
        bookRoom.setPatientManagement(patientManagement);
        bookRoom.setRoom(room);
        
        bookRoom = bookRoomRepository.saveAndFlush(bookRoom);
        Optional<BookRoom> found = bookRoomRepository.findById(bookRoom.getId());
        assertEquals("Test1Test1", found.get().getNote());
        assertEquals(dateOfBook, found.get().getDateOfBook());
        assertEquals(timeOfStart, found.get().getTimeOfStart());
        assertEquals(timeOfEnd, found.get().getTimeOfEnd());
        assertEquals(bookDate, found.get().getBookDate());
        assertEquals(bookRoom, found.get());
    }

    @Test
    void B6012755_testNoteMustNotBeNull() {
        BookRoom bookRoom = new BookRoom();
        Profile profile = profileRepository.findById(1);
        PatientManagement patientManagement = patientManagementRepository.findById(1);
        Room room = roomRepository.findById(1);
        bookRoom.setNote(null);
        bookRoom.setDateOfBook(dateOfBook);
        bookRoom.setTimeOfStart(timeOfStart);
        bookRoom.setTimeOfEnd(timeOfEnd);
        bookRoom.setBookDate(bookDate);
        bookRoom.setProfile(profile);
        bookRoom.setPatientManagement(patientManagement);
        bookRoom.setRoom(room);
        

        Set<ConstraintViolation<BookRoom>> result = validator.validate(bookRoom);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BookRoom> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void B6012755_testDateOfBookMustNotBeNull() {
        BookRoom bookRoom = new BookRoom();
        Profile profile = profileRepository.findById(1);
        PatientManagement patientManagement = patientManagementRepository.findById(1);
        Room room = roomRepository.findById(1);
        bookRoom.setNote("Test1Test1");
        bookRoom.setDateOfBook(null);
        bookRoom.setTimeOfStart(timeOfStart);
        bookRoom.setTimeOfEnd(timeOfEnd);
        bookRoom.setBookDate(bookDate);
        bookRoom.setProfile(profile);
        bookRoom.setPatientManagement(patientManagement);
        bookRoom.setRoom(room);
        

        Set<ConstraintViolation<BookRoom>> result = validator.validate(bookRoom);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BookRoom> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("DateOfBook", v.getPropertyPath().toString());
    }

    @Test
    void B6012755_timeOfStartOfBookMustNotBeNull() {
        BookRoom bookRoom = new BookRoom();
        Profile profile = profileRepository.findById(1);
        PatientManagement patientManagement = patientManagementRepository.findById(1);
        Room room = roomRepository.findById(1);
        bookRoom.setNote("Test1Test1");
        bookRoom.setDateOfBook(dateOfBook);
        bookRoom.setTimeOfStart(null);
        bookRoom.setTimeOfEnd(timeOfEnd);
        bookRoom.setBookDate(bookDate);
        bookRoom.setProfile(profile);
        bookRoom.setPatientManagement(patientManagement);
        bookRoom.setRoom(room);
        

        Set<ConstraintViolation<BookRoom>> result = validator.validate(bookRoom);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BookRoom> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("TimeOfStart", v.getPropertyPath().toString());
    }

    @Test
    void B6012755_timeOfEndOfBookMustNotBeNull() {
        BookRoom bookRoom = new BookRoom();
        Profile profile = profileRepository.findById(1);
        PatientManagement patientManagement = patientManagementRepository.findById(1);
        Room room = roomRepository.findById(1);
        bookRoom.setNote("Test1Test1");
        bookRoom.setDateOfBook(dateOfBook);
        bookRoom.setTimeOfStart(timeOfStart);
        bookRoom.setTimeOfEnd(timeOfEnd);
        bookRoom.setBookDate(null);
        bookRoom.setProfile(profile);
        bookRoom.setPatientManagement(patientManagement);
        bookRoom.setRoom(room);
        

        Set<ConstraintViolation<BookRoom>> result = validator.validate(bookRoom);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BookRoom> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("BookDate", v.getPropertyPath().toString());
    }

    @Test
    void B6012755_bookDateMustNotBeNull() {
        BookRoom bookRoom = new BookRoom();
        Profile profile = profileRepository.findById(1);
        PatientManagement patientManagement = patientManagementRepository.findById(1);
        Room room = roomRepository.findById(1);
        bookRoom.setNote("Test1Test1");
        bookRoom.setDateOfBook(dateOfBook);
        bookRoom.setTimeOfStart(timeOfStart);
        bookRoom.setTimeOfEnd(null);
        bookRoom.setBookDate(bookDate);
        bookRoom.setProfile(profile);
        bookRoom.setPatientManagement(patientManagement);
        bookRoom.setRoom(room);
        

        Set<ConstraintViolation<BookRoom>> result = validator.validate(bookRoom);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BookRoom> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("TimeOfEnd", v.getPropertyPath().toString());
    }
    
    @Test
    void B6012755_testBookRoomWrongPast() {
        BookRoom bookRoom = new BookRoom();
        Profile profile = profileRepository.findById(1);
        PatientManagement patientManagement = patientManagementRepository.findById(1);
        Room room = roomRepository.findById(1);
    
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate DateofBook = LocalDate.parse("2019-01-20",formatter);
        bookRoom.setNote("Test1Test1");
        bookRoom.setDateOfBook(DateofBook);
        bookRoom.setTimeOfStart(timeOfStart);
        bookRoom.setTimeOfEnd(timeOfEnd);
        bookRoom.setBookDate(bookDate);
        bookRoom.setProfile(profile);
        bookRoom.setPatientManagement(patientManagement);
        bookRoom.setRoom(room);
        
        Set<ConstraintViolation<BookRoom>> result = validator.validate(bookRoom);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BookRoom> v = result.iterator().next();
        assertEquals("must be a date in the present or in the future", v.getMessage());
        assertEquals("DateOfBook", v.getPropertyPath().toString());
    }


    @Test
    void B6012755_testNoteNotBeMaxSize() { // ใส่ข้อมูลปกติ

        BookRoom bookRoom = new BookRoom();
        Profile profile = profileRepository.findById(1);
        PatientManagement patientManagement = patientManagementRepository.findById(1);
        Room room = roomRepository.findById(1);
        

        bookRoom.setNote("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        bookRoom.setDateOfBook(dateOfBook);
        bookRoom.setTimeOfStart(timeOfStart);
        bookRoom.setTimeOfEnd(timeOfEnd);
        bookRoom.setBookDate(bookDate);
        bookRoom.setProfile(profile);
        bookRoom.setPatientManagement(patientManagement);
        bookRoom.setRoom(room);

        Set<ConstraintViolation<BookRoom>> result = validator.validate(bookRoom);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BookRoom> error = result.iterator().next();
        assertEquals("size must be between 10 and 500", error.getMessage());
        assertEquals("note", error.getPropertyPath().toString());
    }

    @Test
    void B6012755_testNoteNotBeMinSize() { // ใส่ข้อมูลปกติ

        BookRoom bookRoom = new BookRoom();
        Profile profile = profileRepository.findById(1);
        PatientManagement patientManagement = patientManagementRepository.findById(1);
        Room room = roomRepository.findById(1);

        bookRoom.setNote("test");
        bookRoom.setDateOfBook(dateOfBook);
        bookRoom.setTimeOfStart(timeOfStart);
        bookRoom.setTimeOfEnd(timeOfEnd);
        bookRoom.setBookDate(bookDate);
        bookRoom.setProfile(profile);
        bookRoom.setPatientManagement(patientManagement);
        bookRoom.setRoom(room);

        

        Set<ConstraintViolation<BookRoom>> result = validator.validate(bookRoom);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BookRoom> error = result.iterator().next();
        assertEquals("size must be between 10 and 500", error.getMessage());
        assertEquals("note", error.getPropertyPath().toString());
    }



}
