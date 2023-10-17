package com.medical.backendsystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.backendsystem.models.PatientModel;

public interface PatientRepository extends MongoRepository<PatientModel, String> {
    
}
