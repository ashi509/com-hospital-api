package com.hospital.Api.services;

import com.hospital.Api.entity.Doctor;
import com.hospital.Api.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.html.HTMLHtmlElement;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public ResponseEntity<?> addDoctor(Doctor doctor) {
    doctor.setDoctorId(0);
    var doctors=doctorRepository.save(doctor);
    if(doctors==null)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not added something went wrong");
        return  ResponseEntity.status(HttpStatus.OK).body(doctors);
    }
    @Override
    public ResponseEntity<?> removeDoctor(long id) {
        var doctors=doctorRepository.findById(id);
        if(doctors==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Given id is not found"+id);
        doctorRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Override
    public ResponseEntity<?> fetchDoctor() {
        var doctors=doctorRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }
    @Override
    public ResponseEntity<?> fetchByIdDoctor(long id) {
        var doctors=doctorRepository.findById(id);
        if(doctors==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Given id is not found"+id);
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }

    @Override
    public ResponseEntity<?> updateDoctor(Doctor doctor, long id) {
        var doctorFromDb=doctorRepository.findById(id).get();
        if(doctorFromDb==null)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("given id is not found"+id);
        doctor.setDoctorId(id);
        doctor.setDoctorName(doctorFromDb.getDoctorName());
        doctor.setDoctorCity(doctorFromDb.getDoctorCity());
        doctor.setDoctorPhoneNumber(doctorFromDb.getDoctorPhoneNumber());
        doctor.setDoctorSpeciality(doctorFromDb.getDoctorSpeciality());
        doctorFromDb=doctorRepository.save(doctor);
        if(doctorFromDb==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id not found"+id);
        return ResponseEntity.status(HttpStatus.OK).body(doctorFromDb);
    }


}
