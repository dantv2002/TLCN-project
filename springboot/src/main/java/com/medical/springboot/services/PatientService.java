package com.medical.springboot.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.springboot.models.entity.PatientEntity;
import com.medical.springboot.repositories.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    //Create
    public PatientEntity create(String fullname, Date birthday, String address, String phonenumber, String email) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFullName(fullname);
        patientEntity.setBirthday(birthday);
        patientEntity.setGender(null);
        patientEntity.setAddress(address);
        patientEntity.setPhoneNumber(phonenumber);
        patientEntity.setEmail(email);
        patientEntity.setIdentificationCard(null);
        patientEntity.setAllergy(null);
        patientEntity.setIsDeleted(true);
        return patientRepository.save(patientEntity);
    }

    public PatientEntity create(String email){
        PatientEntity patientEntity = patientRepository.findFirstByEmail(email);
        patientEntity.setIsDeleted(false);
        return patientRepository.save(patientEntity);
    }

    //Read
    public PatientEntity findByEmail(String email) {
        return patientRepository.findFirstByEmail(email);
    }

    public PatientEntity findByEmailAndIsDeleted(String email, Boolean isDeleted) {
        return patientRepository.findFirstByEmailAndIsDeleted(email, isDeleted);
    }
    //Update
    public PatientEntity update(String fullName, Date birthday, Boolean gender, String address, String phonenumber,
            String email, String identificationcard, String allergy) {

        PatientEntity patientEntity = patientRepository.findFirstByEmail(email);
        //
        patientEntity.setFullName(fullName);
        patientEntity.setBirthday(birthday);
        patientEntity.setGender(gender);
        patientEntity.setAddress(address);
        patientEntity.setPhoneNumber(phonenumber);
        patientEntity.setIdentificationCard(identificationcard);
        patientEntity.setAllergy(allergy);
        //
        return patientRepository.save(patientEntity);
    }
    //Delete
    public PatientEntity delete(String email) {
        PatientEntity patientEntity = patientRepository.findFirstByEmail(email);
        patientEntity.setIsDeleted(true);
        return patientRepository.save(patientEntity);
    }
    //Other
    public Boolean isexistsByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }

    public Boolean isexistsByEmailAndIsDeleted(String email, Boolean isDeleted) {
        return patientRepository.existsByEmailAndIsDeleted(email, isDeleted);
    }
}
