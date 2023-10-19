package com.medical.backendsystem.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.backendsystem.models.entity.PatientEntity;
import com.medical.backendsystem.repositories.PatientRepository;

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
        PatientEntity patientEntity = patientRepository.findByEmail(email).get(0);
        patientEntity.setIsDeleted(false);
        return patientRepository.save(patientEntity);
    }

    //Read
    public PatientEntity findByEmail(String email) {
        return patientRepository.findByEmail(email).get(0);
    }

    public PatientEntity findByEmailAndIsDeleted(String email, Boolean isDeleted) {
        return patientRepository.findByEmailAndIsDeleted(email, isDeleted).get(0);
    }
    //Update
    public PatientEntity update(String fullName, Date birthday, Boolean gender, String address, String phonenumber,
            String email, String identificationcard, String allergy) {

        PatientEntity patientEntity = patientRepository.findByEmail(email).get(0);
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
        PatientEntity patientEntity = patientRepository.findByEmail(email).get(0);
        patientEntity.setIsDeleted(true);
        return patientRepository.save(patientEntity);
    }
    //Other
    public Boolean isexistsByEmail(String email) {
        return patientRepository.findByEmail(email).size() > 0 ? true : false;
    }

    public Boolean isexistsByEmailAndIsDeleted(String email, Boolean isDeleted) {
        return patientRepository.findByEmailAndIsDeleted(email, isDeleted).size() > 0 ? true : false;
    }
}
