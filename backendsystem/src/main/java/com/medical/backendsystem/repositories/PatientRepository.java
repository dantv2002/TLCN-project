package com.medical.backendsystem.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.backendsystem.models.PatientModel;

public interface PatientRepository extends MongoRepository<PatientModel, String> {
    List<PatientModel> findByEmail(String email);
    List<PatientModel> findByEmailAndIsDeleted(String email, Boolean isDeleted);
}
