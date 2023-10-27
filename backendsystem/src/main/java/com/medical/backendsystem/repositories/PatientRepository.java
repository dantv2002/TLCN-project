package com.medical.backendsystem.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.backendsystem.models.entity.PatientEntity;

public interface PatientRepository extends MongoRepository<PatientEntity, String> {

    PatientEntity findFirstByEmail(String email);
    PatientEntity findFirstByEmailAndIsDeleted(String email, Boolean isDeleted);
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndIsDeleted(String email, Boolean isDeleted);
    
}
