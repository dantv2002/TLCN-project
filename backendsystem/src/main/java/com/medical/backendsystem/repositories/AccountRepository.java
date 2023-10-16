package com.medical.backendsystem.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.backendsystem.models.AccountModel;

/**
 * AccountRepository
 */
public interface AccountRepository extends MongoRepository<AccountModel, String> {
    List<AccountModel> findByEmail(String email);
}