package com.hospital.Api.services;

import org.springframework.http.ResponseEntity;

public interface DoctorSuggestService {
    ResponseEntity<?> suggestDoctorApi(long id);
}
