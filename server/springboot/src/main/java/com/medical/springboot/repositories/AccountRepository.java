package com.medical.springboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.springboot.models.entity.AccountEntity;

/**
 * AccountRepository
 */
public interface AccountRepository extends MongoRepository<AccountEntity, String> {

    AccountEntity findFirstByEmail(String email);

    Boolean existsByEmail(String email);

}