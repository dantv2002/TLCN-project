package com.medical.springboot.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.medical.springboot.models.entity.MedicalEntity;

public interface MedicalRepository extends MongoRepository<MedicalEntity, String> {
    @Query(value = "{ 'patientId' : ?0 }", fields = "{ 'patientId' : 1, 'date': 1 }")
    public List<MedicalEntity> findByPatientId(String patientId);
}
