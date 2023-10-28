package com.medical.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.springboot.models.entity.AccountEntity;
import com.medical.springboot.repositories.AccountRepository;

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
    // Read one
    public AccountEntity findByEmail(String email) {
        return accountRepository.findFirstByEmail(email);
    }
    // Update
    public AccountEntity update(String email, String password){
        AccountEntity accountEntity = accountRepository.findFirstByEmail(email);
        accountEntity.setPassword(password);
        return accountRepository.save(accountEntity);
    }
    // Delete
    // Other
    public Boolean isExistsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
}
