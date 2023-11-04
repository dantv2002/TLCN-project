package com.medical.springboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.medical.springboot.models.entity.MedicalEntity;

public interface MedicalRepository extends MongoRepository<MedicalEntity, String> {
    @Query(value = "{ 'patientId' : ?0 }", fields = "{'id':1 , 'patientId' : 1, 'date': 1 }")
    public Page<MedicalEntity> findByPatientId(String patientId, Pageable pageable);
}
