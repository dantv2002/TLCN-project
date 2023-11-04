package com.medical.springboot.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.medical.springboot.models.entity.PatientEntity;

public interface PatientRepository extends MongoRepository<PatientEntity, String> {

    public PatientEntity findFirstByEmail(String email);

    public PatientEntity findFirstById(String id);

    public Boolean existsByEmail(String email);

    public Boolean existsByIdAndIsDeleted(String id, Boolean isDeleted);

    @Query("{ 'fullName' : { $regex: ?0 , $options: 'i' }, 'identificationCard' : { $regex: ?0 , $options: 'i' } }")
    public List<PatientEntity> findIdByRegexpFullNameOrRegexpIdentificationCard(String keyword);
}
