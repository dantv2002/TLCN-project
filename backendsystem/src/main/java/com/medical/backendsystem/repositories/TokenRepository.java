package com.medical.backendsystem.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.backendsystem.models.entity.TokenEntity;

public interface TokenRepository extends MongoRepository<TokenEntity, String> {

    TokenEntity findFirstByAccountId(String accountId);

    void deleteByToken(String token);
}
