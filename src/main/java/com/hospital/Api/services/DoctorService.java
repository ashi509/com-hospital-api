package com.hospital.Api.services;

import com.hospital.Api.entity.Doctor;
import org.springframework.http.ResponseEntity;

public interface DoctorService {
    ResponseEntity<?>addDoctor(Doctor doctor);
    ResponseEntity<?>removeDoctor(long id);
    ResponseEntity<?>fetchDoctor();
    ResponseEntity<?>fetchByIdDoctor(long id);
    ResponseEntity<?>updateDoctor(Doctor doctor,long id);

}
