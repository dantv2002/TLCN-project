package com.medical.springboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.springboot.models.entity.PatientEntity;

public interface PatientRepository extends MongoRepository<PatientEntity, String> {

    public PatientEntity findFirstByEmail(String email);

    public PatientEntity findFirstById(String id);

    public Boolean existsByEmail(String email);

    public Boolean existsByIdAndIsDeleted(String id, Boolean isDeleted);

}
