package com.medical.backendsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.backendsystem.models.AccountModel;
import com.medical.backendsystem.repositories.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public AccountService(@Autowired AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    public List<AccountModel> findByEmail(String email){
        return accountRepository.findByEmail(email);
    }
}
