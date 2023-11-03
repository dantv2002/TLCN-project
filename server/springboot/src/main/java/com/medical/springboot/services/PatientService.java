package com.medical.springboot.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.springboot.models.entity.PatientEntity;
import com.medical.springboot.repositories.PatientRepository;

@Service
public class PatientService implements IDao<PatientEntity> {
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
    @Autowired
    private PatientRepository patientRepository;

    // Create
    @Override
    public PatientEntity create(PatientEntity t) {
        logger.debug("create patient: {}", t);
        return patientRepository.save(t);
    }

    public PatientEntity activate(String email) {
        PatientEntity patientEntity = patientRepository.findFirstByEmail(email);
        patientEntity.setIsDeleted(false);
        return patientRepository.save(patientEntity);
    }

    // Read all
    @Override
    public List<PatientEntity> read(String key) {
        throw new UnsupportedOperationException("Not supported read all patients");
        // logger.debug("read all patients");
        // return patientRepository.findAll();
    }

    // Read one
    public Optional<PatientEntity> findByEmail(String email) {
        return Optional.ofNullable(patientRepository.findFirstByEmail(email));
    }

    public PatientEntity findByEmailAndIsDeleted(String email, Boolean isDeleted) {
        return patientRepository.findFirstByEmailAndIsDeleted(email, isDeleted);
    }

    // Update
    @Override
    public PatientEntity update(PatientEntity t) {
        logger.debug("update patient: {}", t);
        return patientRepository.save(t);
    }

    // Delete
    @Override
    public boolean delete(String key) { // set isDeleted = true
        try {
            PatientEntity patientEntity = patientRepository.findFirstByEmail(key);
            patientEntity.setIsDeleted(true);
            logger.debug("delete patient by id: {}", key);
            return true;
        } catch (Exception e) {
            logger.error("delete patient by id: {}", key);
            logger.error(e.getMessage());
            return false;
        }
    }

    // Other methods
    public Boolean isexistsByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }

    public Boolean isexistsByEmailAndIsDeleted(String email, Boolean isDeleted) {
        return patientRepository.existsByEmailAndIsDeleted(email, isDeleted);
    }
}
