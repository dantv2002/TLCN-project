package com.medical.springboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.springboot.models.entity.TokenEntity;

public interface TokenRepository extends MongoRepository<TokenEntity, String> {

    void deleteByToken(String token);
    Boolean existsByToken(String token);
    
}
