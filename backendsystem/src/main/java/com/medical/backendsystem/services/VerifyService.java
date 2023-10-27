package com.medical.backendsystem.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.backendsystem.models.entity.VerifyEntity;
import com.medical.backendsystem.repositories.VerifyRepository;

@Service
public class VerifyService {
    
    @Autowired
    private VerifyRepository verifyRepository;

    // Create
    public String create(String email){
        String verifyCode = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            verifyCode += random.nextInt(10);
        }
        verifyRepository.save(new VerifyEntity(email, verifyCode));
        return verifyCode;
    }
    // Read
    // Update
    // Delete
    public void delete(String email){
        VerifyEntity verifyEntities = verifyRepository.findFirstByEmail(email);
        if (verifyEntities != null) {
            verifyRepository.delete(verifyEntities);
        }
    }
    // Other
    public boolean verifyCode(String email, String verifyCode){
        return verifyRepository.existsByEmailAndVerifycode(email, verifyCode);
    }
}
