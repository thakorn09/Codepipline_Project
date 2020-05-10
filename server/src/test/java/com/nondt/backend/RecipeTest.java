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
public class RecipeTest{

    private Validator validator;
        
    @Autowired
    private RecipeRepository recipeRepository;
    
    @BeforeEach
    public void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


     @Test
    void B5700820_testRecipeFullData(){
        Recipe recipe = new Recipe();
        
        recipe.setNumber(1L);
        recipe.setAmount(10L);
        LocalDate date = LocalDate.now();
        recipe.setDate(date);

        recipe = recipeRepository.saveAndFlush(recipe);
        Optional<Recipe> found = recipeRepository.findById(recipe.getNumber());
        assertEquals(Long.valueOf(1),found.get().getNumber());
        assertEquals(Long.valueOf(10),found.get().getAmount());
        assertEquals(date,found.get().getDate());
    }

    @Test
    void B5700820_testRecipeNotNull() { //Notnull
        Recipe recipe = new Recipe();

        recipe.setNumber(null);
        recipe.setAmount(10L);
        LocalDate date = LocalDate.now();
        recipe.setDate(date);

       
        Set<ConstraintViolation<Recipe>> result = validator.validate(recipe);
        
        assertEquals(1, result.size());

        ConstraintViolation<Recipe> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("number", v.getPropertyPath().toString());
    }
    
    @Test
    void B5700820_testRecipeWrongPast() {
        Recipe recipe = new Recipe();

        recipe.setNumber(1L);
        recipe.setAmount(10L);
    
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate DateofRecipe = LocalDate.parse("2019-01-23",formatter);
        recipe.setDate(DateofRecipe);
        
        Set<ConstraintViolation<Recipe>> result = validator.validate(recipe);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Recipe> v = result.iterator().next();
        assertEquals("must be a date in the present or in the future", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }


    @Test
    void B5700820_testAmountNotBeMaxSize() { // ใส่ข้อมูลปกติ

        Recipe recipe = new Recipe();

        recipe.setNumber(1L);
        recipe.setAmount(101L);
        LocalDate date = LocalDate.now();
        recipe.setDate(date);

        Set<ConstraintViolation<Recipe>> result = validator.validate(recipe);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Recipe> error = result.iterator().next();
        assertEquals("must be less than or equal to 100", error.getMessage());
        assertEquals("amount", error.getPropertyPath().toString());
    }

    @Test
    void B5700820_testAmountNotBeMinSize() { // ใส่ข้อมูลปกติ

        Recipe recipe = new Recipe();

        recipe.setNumber(1L);
        recipe.setAmount(0L);
        LocalDate date = LocalDate.now();
        recipe.setDate(date);

        Set<ConstraintViolation<Recipe>> result = validator.validate(recipe);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Recipe> error = result.iterator().next();
        assertEquals("must be greater than or equal to 1", error.getMessage());
        assertEquals("amount", error.getPropertyPath().toString());
    }





}
