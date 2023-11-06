package com.medical.springboot.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.medical.springboot.models.entity.MedicalEntity;

public interface MedicalRepository extends MongoRepository<MedicalEntity, String> {
    public List<MedicalEntity> findByPatientId(String patientId);

    @Query(value = "{ 'patientId' : ?0 }", fields = "{'patientId' : 1, 'date': 1 }")
    public Page<MedicalEntity> findByPatientId(String patientId, Pageable pageable);

    @Query(value = "{ $and: [ { $text: { $search: ?0 } }, { 'patientId': ?1 } ] }", fields = "{'patientId' : 1, 'date': 1}")
    public Page<MedicalEntity> find(String keyword, String patientId, Pageable pageable);

    @Query("{'diagnosis': {$regex: ?0, $options: 'i'}}")
    public List<MedicalEntity> findByRegexpDiagnosis(String keyword);
}
