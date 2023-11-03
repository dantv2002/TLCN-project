package com.medical.springboot.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.springboot.models.entity.MedicalEntity;

public interface MedicalRepository extends MongoRepository<MedicalEntity, String> {
    public List<MedicalEntity> findByPatientId(String patientId);
}
