package com.medical.backendsystem.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.backendsystem.models.entity.AccountEntity;

/**
 * AccountRepository
 */
public interface AccountRepository extends MongoRepository<AccountEntity, String> {
    
    AccountEntity findFirstByEmail(String email);
    Boolean existsByEmail(String email);
    
}