package com.medical.springboot.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.springboot.models.entity.AccountEntity;
import com.medical.springboot.repositories.AccountRepository;

@Service
public class AccountService implements IDao<AccountEntity> {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    private AccountRepository accountRepository;

    // Create
    @Override
    public AccountEntity create(AccountEntity t) {
        logger.debug("create account: {}", t);
        return accountRepository.save(t);
    }

    // Read all by key = email
    @Override
    public List<AccountEntity> read(String key) {
        throw new UnsupportedOperationException("Not supported read all accounts by email");
        // logger.debug("read all accounts by email: {}", key);
        // return accountRepository.findByEmail(key);
    }

    // Read one
    public Optional<AccountEntity> findFirstByEmail(String email) {
        logger.debug("read account by email: {}", email);
        return Optional.ofNullable(accountRepository.findFirstByEmail(email));
    }

    // Update
    @Override
    public AccountEntity update(AccountEntity t) {
        logger.debug("update account: {}", t);
        return accountRepository.save(t);
    }

    // Delete
    @Override
    public boolean delete(String key) {
        throw new UnsupportedOperationException("Not supported delete account by id");
        // try {
        //     accountRepository.deleteById(key);
        //     logger.debug("delete account by id: {}", key);
        //     return true;
        // } catch (Exception e) {
        //     logger.error("delete account by id: {}", key);
        //     logger.error(e.getMessage());
        //     return false;
        // }
    }

    // Other methods
    public Boolean isExistsByEmail(String email) {
        logger.debug("check exists account by email: {}", email);
        return accountRepository.existsByEmail(email);
    }
}
