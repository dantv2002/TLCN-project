package com.medical.backendsystem.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.medical.backendsystem.models.VerifyModel;

public interface VerifyRepository extends MongoRepository<VerifyModel, String> {
    
    List<VerifyModel> findByEmail(String email);
    
}
