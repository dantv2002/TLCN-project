package com.medical.springboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.springboot.models.entity.DoctorEntity;

public interface DoctorRepository extends MongoRepository<DoctorEntity, String> {

    public DoctorEntity findFirstByEmail(String email);
}
