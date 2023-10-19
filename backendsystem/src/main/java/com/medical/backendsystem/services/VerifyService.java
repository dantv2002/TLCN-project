package com.medical.backendsystem.services;

import java.util.List;
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
        List<VerifyEntity> verifyEntities = verifyRepository.findByEmail(email);
        if (verifyEntities.size() > 0) {
            verifyRepository.delete(verifyEntities.get(0));
        }
    }
    // Other
    public boolean verifyCode(String email, String verifyCode){
        List<VerifyEntity> verifyEntities = verifyRepository.findByEmail(email);
        if (verifyEntities.size() > 0) {
            if (verifyEntities.get(0).getVerifycode().equals(verifyCode)) {
                return true;
            }
        }
        return false;
    }
}
