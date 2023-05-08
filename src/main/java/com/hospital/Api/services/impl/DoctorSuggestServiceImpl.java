package com.hospital.Api.services.impl;

import com.hospital.Api.constants.ApiConstant;
import com.hospital.Api.exception.GenericException;
import com.hospital.Api.repo.DoctorRepository;
import com.hospital.Api.repo.PatientRepository;
import com.hospital.Api.services.DoctorSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DoctorSuggestServiceImpl implements DoctorSuggestService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Override
    public ResponseEntity<?> suggestDoctorApi(long id) {
        var patient=patientRepository.findById(id).orElseThrow(()->new GenericException(HttpStatus.NOT_FOUND.value(), "Patient not found with id "+id));
        var doctors=doctorRepository.findAll();
        if(doctors.stream().filter(doctor -> doctor.getCity().equalsIgnoreCase(patient.getCity()))
                .collect(Collectors.toList()).size()==0) // if don't have any doctor at this location
            throw new GenericException(HttpStatus.EXPECTATION_FAILED.value(), "We are still waiting to expand to your location");
        var doctorsForOrthoSpeciality= doctorRepository.findAll().stream().filter(doctor -> (
                patient.getSymptom().equalsIgnoreCase(ApiConstant.ARTBRITIS)
                || patient.getSymptom().equalsIgnoreCase(ApiConstant.TISSUE_INJURIES)
                || patient.getSymptom().equalsIgnoreCase(ApiConstant.BACKPAIN))
                && doctor.getSpeciality().equalsIgnoreCase(ApiConstant.ORTHOPEDIC)).collect(Collectors.toList());
        var doctorsForDysmemoSpeciality= doctorRepository.findAll().stream().filter(doctor ->
                patient.getSymptom().equalsIgnoreCase(ApiConstant.DYSMENORRBEA)
                && doctor.getSpeciality().equalsIgnoreCase(ApiConstant.GYNECOLOGY)).collect(Collectors.toList());
        var doctorsForSkinInfectionSpeciality= doctorRepository.findAll().stream().filter(doctor ->(
                patient.getSymptom().equalsIgnoreCase(ApiConstant.SKIN_INFECTION)
                        || patient.getSymptom().equalsIgnoreCase(ApiConstant.SKIN_BURN))
                        && doctor.getSpeciality().equalsIgnoreCase(ApiConstant.DERMATOLOGY)).collect(Collectors.toList());
        var doctorsForEarPainSpeciality= doctorRepository.findAll().stream().filter(doctor ->
                patient.getSymptom().equalsIgnoreCase(ApiConstant.EAR_PAIN)
                && doctor.getSpeciality().equalsIgnoreCase(ApiConstant.ENT)).collect(Collectors.toList());
        if(doctorsForOrthoSpeciality.size()==0 &&
                doctorsForDysmemoSpeciality.size()==0 &&
                doctorsForSkinInfectionSpeciality.size()==0
        && doctorsForEarPainSpeciality.size()==0)
            throw new GenericException(HttpStatus.NOT_FOUND.value(), "There is not any doctor present at your location for your symptom");
        if(patient.getSymptom().equalsIgnoreCase(ApiConstant.EAR_PAIN))
            return ResponseEntity.status(HttpStatus.OK).body(doctorsForEarPainSpeciality);
        else if(patient.getSymptom().equalsIgnoreCase(ApiConstant.SKIN_INFECTION) || patient.getSymptom().equalsIgnoreCase(ApiConstant.SKIN_BURN))
            return ResponseEntity.status(HttpStatus.OK).body(doctorsForSkinInfectionSpeciality);
        else if(patient.getSymptom().equalsIgnoreCase(ApiConstant.DYSMENORRBEA))
            return ResponseEntity.status(HttpStatus.OK).body(doctorsForDysmemoSpeciality);
        else
            return ResponseEntity.status(HttpStatus.OK).body(doctorsForOrthoSpeciality);
    }
}
