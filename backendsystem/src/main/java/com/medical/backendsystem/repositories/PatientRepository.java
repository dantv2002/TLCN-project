package com.medical.backendsystem.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.backendsystem.models.entity.PatientEntity;

public interface PatientRepository extends MongoRepository<PatientEntity, String> {
    List<PatientEntity> findByEmail(String email);
    List<PatientEntity> findByEmailAndIsDeleted(String email, Boolean isDeleted);
}
