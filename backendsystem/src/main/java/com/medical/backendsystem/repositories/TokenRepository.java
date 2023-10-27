package com.medical.backendsystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.backendsystem.models.entity.TokenEntity;

public interface TokenRepository extends MongoRepository<TokenEntity, String> {

    void deleteByToken(String token);
}
