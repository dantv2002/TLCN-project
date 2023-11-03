package com.medical.springboot.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
