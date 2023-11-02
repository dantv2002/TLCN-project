package com.medical.springboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.springboot.models.entity.VerifyEntity;

public interface VerifyRepository extends MongoRepository<VerifyEntity, String> {

    VerifyEntity findFirstByEmail(String email);

    Boolean existsByEmailAndVerifycode(String email, String verifycode);

}
