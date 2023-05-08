package com.hospital.Api.controller;

import com.hospital.Api.services.DoctorSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suggest")
public class SuggestDoctorController {
    @Autowired
    private DoctorSuggestService doctorSuggestService;

    @GetMapping("{id}")
    public ResponseEntity<?> suggestDoctor(@PathVariable long id){
        return doctorSuggestService.suggestDoctorApi(id);
    }
}
