package com.hospital.Api.services;

import com.hospital.Api.entity.Patient;
import com.hospital.Api.repo.PatientRepository;
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
        var patients=patientRepository.save(patient);
        if(patients==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data not added successfully");
        return ResponseEntity.ok(patients);
    }

    @Override
    public ResponseEntity<?> getPatient() {
      var patients=patientRepository.findAll();
        if(patients==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("something went wrong");
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }
    @Override
    public ResponseEntity<?> getPatientById(long id) {
        var patients=patientRepository.findById(id);
        if(patients==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Given id is not found "+id);
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }
    @Override
    public ResponseEntity<?> updatePatient(Patient patient, long id) {
        var patients=patientRepository.findById(id).get();
        if(patients==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Given id is not found "+id);
       patients.setPatientId(patient.getPatientId());
       patients.setPatientName(patient.getPatientName());
       patients.setPatientCity(patient.getPatientCity());
       patients.setPatientEmail(patient.getPatientEmail());
       patients.setPatientPhoneNumber(patient.getPatientPhoneNumber());
       patients.setSymptoms(patient.getSymptoms());
       patientRepository.save(patients);
       return ResponseEntity.status(HttpStatus.OK).body(patient);
    }
    @Override
    public ResponseEntity<?> deletePatient(long id) {
        var patients=patientRepository.findById(id).get();
        if(patients==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Given id is not found "+id);
        patientRepository.deleteById(id);
         return ResponseEntity.status(HttpStatus.OK).body(patients);
    }
}
