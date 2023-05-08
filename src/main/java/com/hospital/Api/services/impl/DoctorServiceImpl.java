package com.hospital.Api.services.impl;

import com.hospital.Api.entity.Doctor;
import com.hospital.Api.exception.GenericException;
import com.hospital.Api.repo.DoctorRepository;
import com.hospital.Api.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.html.HTMLHtmlElement;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public ResponseEntity<?> addDoctor(Doctor doctor) {
    doctor.setId(0);
    var doctors=doctorRepository.save(doctor);
    if(doctors==null)
        throw new GenericException(HttpStatus.EXPECTATION_FAILED.value(), "Something went wrong");
    return  ResponseEntity.status(HttpStatus.OK).body(doctors);
    }
    @Override
    public ResponseEntity<?> removeDoctor(long id) {
        var doctors=doctorRepository.findById(id).orElseThrow(()-> new GenericException(HttpStatus.EXPECTATION_FAILED.value(), "Doctor not found with id "+id));
        doctorRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }
    @Override
    public ResponseEntity<?> fetchDoctor() {
        var doctors=doctorRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }
    @Override
    public ResponseEntity<?> fetchByIdDoctor(long id) {
        var doctors=doctorRepository.findById(id).orElseThrow(()-> new GenericException(HttpStatus.EXPECTATION_FAILED.value(), "Doctor could not be found with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }

    @Override
    public ResponseEntity<?> updateDoctor(Doctor doctor, long id) {
        var doctorFromDb=doctorRepository.findById(id).orElseThrow(()-> new GenericException(HttpStatus.EXPECTATION_FAILED.value(), "Doctor could not be found with id "+id));
        doctor.setId(id);
        doctorFromDb=doctorRepository.save(doctor);
        if(doctorFromDb==null)
            throw new GenericException(HttpStatus.EXPECTATION_FAILED.value(), "Something went wrong");
        return ResponseEntity.status(HttpStatus.OK).body(doctorFromDb);
    }
}
