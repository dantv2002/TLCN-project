package com.medical.springboot.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.springboot.models.entity.AccountEntity;

/**
 * AccountRepository
 */
public interface AccountRepository extends MongoRepository<AccountEntity, String> {

    public AccountEntity findFirstByEmail(String email);

    public Boolean existsByEmail(String email);

    public Boolean existsByEmailAndStatus(String email, Boolean status);

}