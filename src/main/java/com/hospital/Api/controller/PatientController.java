package com.hospital.Api.controller;

import com.hospital.Api.entity.Patient;
import com.hospital.Api.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @PostMapping
    ResponseEntity<?> addPatient(@Valid @RequestBody Patient patient){
        return patientService.addPatient(patient);
    }
    @GetMapping
    ResponseEntity<?>getPatient(){
        return patientService.getPatient();
    }
    @PutMapping("{id}")
    ResponseEntity<?>updatePatient(@Valid @RequestBody Patient patient , @PathVariable long id ){
      return patientService.updatePatient(patient,id);
    }
    @GetMapping("{id}")
    ResponseEntity<?>getPatientById(@PathVariable long id){
        return patientService.getPatientById(id);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?>deletePatient(@PathVariable long id){
        return patientService.deletePatient(id);
    }

}
