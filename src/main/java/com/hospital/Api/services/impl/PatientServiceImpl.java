package com.hospital.Api.services.impl;

import com.hospital.Api.entity.Patient;
import com.hospital.Api.exception.GenericException;
import com.hospital.Api.repo.PatientRepository;
import com.hospital.Api.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public ResponseEntity<?> addPatient(Patient patient) {
        patient.setId(0);
        var patients=patientRepository.save(patient);
        if(patients==null)
            throw new GenericException(HttpStatus.EXPECTATION_FAILED.value(), "Due to technical problem patient could not be save");
        return ResponseEntity.ok(patients);
    }

    @Override
    public ResponseEntity<?> getPatient() {
      var patients=patientRepository.findAll();
        if(patients==null)
            throw new GenericException(HttpStatus.NOT_FOUND.value(), "something went wrong");
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }
    @Override
    public ResponseEntity<?> getPatientById(long id) {
        var patients=patientRepository.findById(id).orElseThrow(()->new GenericException(HttpStatus.NOT_FOUND.value(), "Patient not found with id "+id));
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }

    @Override
    public ResponseEntity<?> updatePatient(Patient patient, long id) {
        var patients=patientRepository.findById(id).orElseThrow(()->new GenericException(HttpStatus.NOT_FOUND.value(), "Patient not found with id "+id));
        patient.setId(id);
       var response= patientRepository.save(patients);
       if(response==null)
           throw new GenericException(HttpStatus.EXPECTATION_FAILED.value(), "Something went wrong");
       return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<?> deletePatient(long id) {
        var patients=patientRepository.findById(id).orElseThrow(()->new GenericException(HttpStatus.NOT_FOUND.value(), "Patient not found with id "+id));
        patientRepository.deleteById(id);
         return ResponseEntity.status(HttpStatus.OK).body(patients);
    }
}
