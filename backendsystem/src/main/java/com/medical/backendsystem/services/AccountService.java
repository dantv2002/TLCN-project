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

    public List<AccountEntity> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public AccountEntity save(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }
}
