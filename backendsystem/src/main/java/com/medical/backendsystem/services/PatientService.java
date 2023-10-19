package com.medical.backendsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.backendsystem.models.entity.PatientEntity;
import com.medical.backendsystem.repositories.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public PatientEntity save(PatientEntity patientEntity) {
        return patientRepository.save(patientEntity);
    }

    public List<PatientEntity> findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public List<PatientEntity> findByEmailAndIsDeleted(String email, Boolean isDeleted) {
        return patientRepository.findByEmailAndIsDeleted(email, isDeleted);
    }
}
