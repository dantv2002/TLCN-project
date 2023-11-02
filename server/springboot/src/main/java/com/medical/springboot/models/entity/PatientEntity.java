package com.medical.springboot.models.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "PatientRecords")
public class PatientEntity extends PersonAbstractEntity {

    @Field
    private String allergy; // Dị ứng
    @Field
    private String healthInsurance; // Bảo hiểm y tế

    //
    @Field
    private Boolean isDeleted; // Đã xóa
    //
    @DBRef(lazy = true)
    private List<MedicalEntity> medicalRecords; // Hồ sơ bệnh án

    public PatientEntity() {
    }

    public PatientEntity(String fullName, Date birthday, Boolean gender, String address, String phoneNumber,
            String email, String identificationCard, String allergy, String healthInsurance, Boolean isDeleted,
            List<MedicalEntity> medicalRecords) {
        super(fullName, birthday, gender, address, phoneNumber, email, identificationCard);
        this.allergy = allergy;
        this.healthInsurance = healthInsurance;
        this.isDeleted = isDeleted;
        this.medicalRecords = medicalRecords;
    }

    public PatientEntity(String allergy, String healthInsurance, Boolean isDeleted,
            List<MedicalEntity> medicalRecords) {
        this.allergy = allergy;
        this.healthInsurance = healthInsurance;
        this.isDeleted = isDeleted;
        this.medicalRecords = medicalRecords;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public List<MedicalEntity> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalEntity> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    @Override
    public String toString() {
        return "PatientEntity [id=" + this.getId() + ", fullname=" + this.getFullName() + ", birthday="
                + this.getBirthday() + ", gender=" + this.getGender() + ", address=" + this.getAddress()
                + ", phoneNumber=" + this.getPhoneNumber() + ", email=" + this.getEmail() + ", identificationCard="
                + this.getIdentificationCard() + ", allergy=" + allergy + ", healthInsurance=" + healthInsurance
                + ", isDeleted=" + isDeleted + ", medicalRecords=" + medicalRecords + "]";
    }

}
