package com.hospital.Api.services;

import com.hospital.Api.entity.Patient;
import org.springframework.http.ResponseEntity;

public interface PatientService {
    ResponseEntity<?> addPatient(Patient patient);
    ResponseEntity<?>getPatient();
    ResponseEntity<?>updatePatient(Patient patient , long id );
    ResponseEntity<?>getPatientById(long id);
    ResponseEntity<?>deletePatient(long id);
}
