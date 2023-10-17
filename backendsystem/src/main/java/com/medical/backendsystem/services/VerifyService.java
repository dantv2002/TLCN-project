package com.medical.backendsystem.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.backendsystem.models.VerifyModel;
import com.medical.backendsystem.repositories.VerifyRepository;

@Service
public class VerifyService {
    
    @Autowired
    private VerifyRepository verifyRepository;

    public String createCode(String email){
        String verifyCode = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            verifyCode += random.nextInt(10);
        }
        verifyRepository.save(new VerifyModel(email, verifyCode));
        return verifyCode;
    }

    public boolean verifyCode(String email, String verifyCode){
        List<VerifyModel> verifyModels = verifyRepository.findByEmail(email);
        if (verifyModels.size() > 0) {
            if (verifyModels.get(0).getVerifycode().equals(verifyCode)) {
                verifyRepository.delete(verifyModels.get(0));
                return true;
            }
        }
        return false;
    }
}
