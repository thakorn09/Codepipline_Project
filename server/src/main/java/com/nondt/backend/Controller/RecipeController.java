package com.nondt.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.Collection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import com.nondt.backend.Entity.*;
import com.nondt.backend.Repository.*;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RecipeController {

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Autowired
    private final RecipeRepository recipeRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private TypemedicineRepository typemedicineRepository;
    @Autowired
    private TypepackingRepository typepackingRepository;
    @Autowired
    private PatientManagementRepository patientManagementRepository;
    
    @GetMapping("/recipe")
    public Collection<Recipe> Recipe() {
        return recipeRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/recipe/{nummed}/{medicineSelect}/{typeSelect}/{amount}/{typepackingSelect}/{selectDate}/{profileSelect}/{patSelect}")
    public void newRecipe(
                        @PathVariable long nummed,
                        @PathVariable long medicineSelect,
                        @PathVariable long typeSelect,
                        @PathVariable long amount,
                        @PathVariable long typepackingSelect,
                        @PathVariable String selectDate,
                        @PathVariable long profileSelect,
                        @PathVariable long patSelect
                        ) 
                  {
                    Recipe recipe = new Recipe();
                    Medicine medicine = medicineRepository.findById(medicineSelect);
                    Typemedicine typemedicine = typemedicineRepository.findById(typeSelect);
                    Profile profile = profileRepository.findById(profileSelect);
                    PatientManagement patientManagement = patientManagementRepository.findById(patSelect);
                    Typepacking typepacking = typepackingRepository.findById(typepackingSelect);
                    recipe.setNumber(nummed);
                    recipe.setAmount(amount);
                
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate recipeDate = LocalDate.parse(selectDate, formatter);

            
            recipe.setDate(recipeDate);
            recipe.setMedicine(medicine);
            recipe.setPatientManagement(patientManagement);
            recipe.setProfile(profile);
            recipe.setTypemedicine(typemedicine);
            recipe.setTypepacking(typepacking);
            recipeRepository.save(recipe);
    }
    

}