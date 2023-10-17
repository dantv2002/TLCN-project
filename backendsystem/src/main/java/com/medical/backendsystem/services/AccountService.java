package com.medical.backendsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.backendsystem.models.AccountModel;
import com.medical.backendsystem.repositories.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    
    public List<AccountModel> findByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public AccountModel save(AccountModel accountModel) {
        return accountRepository.save(accountModel);
    }
}
