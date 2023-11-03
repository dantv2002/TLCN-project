package com.medical.springboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.springboot.models.entity.VerifyEntity;

public interface VerifyRepository extends MongoRepository<VerifyEntity, String> {

    public VerifyEntity findFirstByEmail(String email);

    public Boolean existsByEmailAndVerifycode(String email, String verifycode);

}
