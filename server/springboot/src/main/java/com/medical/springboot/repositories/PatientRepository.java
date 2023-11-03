package com.medical.springboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.springboot.models.entity.PatientEntity;

public interface PatientRepository extends MongoRepository<PatientEntity, String> {

    public PatientEntity findFirstByEmail(String email);

    public PatientEntity findFirstByEmailAndIsDeleted(String email, Boolean isDeleted);

    public Boolean existsByEmail(String email);

    public Boolean existsByEmailAndIsDeleted(String email, Boolean isDeleted);

}
