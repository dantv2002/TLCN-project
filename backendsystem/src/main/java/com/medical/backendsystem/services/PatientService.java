package com.medical.backendsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.backendsystem.models.PatientModel;
import com.medical.backendsystem.repositories.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public PatientModel save(PatientModel patientModel) {
        return patientRepository.save(patientModel);
    }

    public List<PatientModel> findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
}
