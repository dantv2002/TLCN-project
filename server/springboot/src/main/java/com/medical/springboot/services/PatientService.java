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

    // Read all
    @Override
    public List<PatientEntity> readAll() {
        throw new UnsupportedOperationException("Not supported read all patients");
    }

    public PatientEntity findFirstById(String id) {
        return patientRepository.findFirstById(id);
    }

    // Update
    @Override
    public PatientEntity update(PatientEntity t) {
        logger.debug("update patient: {}", t);
        return patientRepository.save(t);
    }

    // Delete
    @Override
    public boolean delete(String id) { // set isDeleted = true
        try {
            PatientEntity patientEntity = patientRepository.findFirstById(id);
            patientEntity.setIsDeleted(true);
            logger.debug("delete patient by id: {}", id);
            return true;
        } catch (Exception e) {
            logger.error("delete patient by id: {}", id);
            logger.error(e.getMessage());
            return false;
        }
    }

    // Other methods
    public Optional<PatientEntity> findByEmail(String email) {
        return Optional.ofNullable(patientRepository.findFirstByEmail(email));
    }

    public Boolean isexistsByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }

    public Boolean isexistsById(String id) {
        return patientRepository.existsById(id);
    }

    public Boolean isexistsByIdAndIsDeleted(String id, Boolean isDeleted) {
        return patientRepository.existsByIdAndIsDeleted(id, isDeleted);
    }

    public PatientEntity activate(String email) {
        PatientEntity patientEntity = patientRepository.findFirstByEmail(email);
        if (patientEntity == null) {
            throw new NullPointerException("Patient record does not exist");
        }
        patientEntity.setIsDeleted(false);
        patientEntity.setGender(null);
        patientEntity.setIdentificationCard(null);
        patientEntity.setAllergy(null);
        patientEntity.setHealthInsurance(null);
        return patientRepository.save(patientEntity);
    }

    // Search
    public List<PatientEntity> searchIdByRegexpFullNameOrRegexpIdentificationCard(String keyword) {
        logger.debug("search patients");
        keyword = ".*" + keyword + ".*";
        return patientRepository.findIdByRegexpFullNameOrRegexpIdentificationCard(keyword);
    }
}
