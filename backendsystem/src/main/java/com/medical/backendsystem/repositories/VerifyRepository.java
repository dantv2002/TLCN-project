package com.medical.backendsystem.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.backendsystem.models.entity.VerifyEntity;

public interface VerifyRepository extends MongoRepository<VerifyEntity, String> {
    
    VerifyEntity findFirstByEmail(String email);
    Boolean existsByEmailAndVerifycode(String email, String verifycode);
    
}
