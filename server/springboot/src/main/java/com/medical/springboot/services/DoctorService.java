package com.medical.springboot.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.medical.springboot.models.entity.DoctorEntity;
import com.medical.springboot.repositories.DoctorRepository;

@Service
public class DoctorService {
    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
    @Autowired
    private DoctorRepository doctorRepository;

    // Create
    // Read all
    // Read one
    // Update
    // Delete
    // Others methods
    // Find by email
    public Optional<DoctorEntity> findByEmail(String email) {
        logger.debug("find doctor by email: {}", email);
        return Optional.ofNullable(doctorRepository.findFirstByEmail(email));
    }

    // Diagnosis image
    public String diagnosisImage(String urlImage) {
        String url = "http://localhost:8081/image/predict";
        //
        RestTemplate restTemplate = new RestTemplate();
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        //
        Map<String, String> body = new HashMap<>() {
            {
                put("image", urlImage);
            }
        };
        //
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        //
        return restTemplate.postForObject(url, request, String.class);
    }
}
