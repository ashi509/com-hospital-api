package com.hospital.Api.controller;

import com.hospital.Api.entity.Doctor;
import com.hospital.Api.services.DoctorService;
import com.hospital.Api.services.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping
    ResponseEntity<?>addDoctor(@RequestBody Doctor doctor){
    return doctorService.addDoctor(doctor);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?>removeDoctor(@PathVariable long id){
    return doctorService.removeDoctor(id);
    }
    @GetMapping
    ResponseEntity<?>fetchDoctor(){
    return doctorService.fetchDoctor();
    }
    @GetMapping("{id}")
    ResponseEntity<?>fetchByIdDoctor(@PathVariable long id){
    return doctorService.fetchByIdDoctor(id);
    }
    @PutMapping("/{id}")
    ResponseEntity<?>updateDoctor(@RequestBody Doctor doctor,@PathVariable long id){
    return doctorService.updateDoctor(doctor,id);
    }

}
