package com.medical.backendsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.backendsystem.models.entity.AccountEntity;
import com.medical.backendsystem.repositories.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    // Create
    public AccountEntity create(String email, String password, String role, Boolean status) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(email);
        accountEntity.setPassword(password);
        accountEntity.setRole(role);
        accountEntity.setStatus(status);
        return accountRepository.save(accountEntity);
    }
    // Read
    public AccountEntity findByEmail(String email) {
        return accountRepository.findByEmail(email).get(0);
    }
    // Update
    public AccountEntity update(String email, String password){
        AccountEntity accountEntity = accountRepository.findByEmail(email).get(0);
        accountEntity.setPassword(password);
        return accountRepository.save(accountEntity);
    }
    // Delete
    // Other
    public Boolean isexistsByEmail(String email) {
        return accountRepository.findByEmail(email).size() > 0 ? true : false;
    }
}
