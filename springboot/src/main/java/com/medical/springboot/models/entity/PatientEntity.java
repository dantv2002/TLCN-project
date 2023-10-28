package com.medical.springboot.models.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "patients")
public class PatientEntity {

    @Id
    private String id;
    @Field
    private String fullName;
    @Field
    private Date birthday;
    @Field
    private Boolean gender; // Nam = 1, Nữ = 0
    @Field
    private String address;
    @Field
    private String phoneNumber;
    @Field
    private String email;
    @Field
    private String identificationCard; // CCCD
    @Field
    private String allergy; // Dị ứng

    //
    @Field
    private Boolean isDeleted;

    public PatientEntity() {
    }

    public PatientEntity(String fullName, Date birthday, Boolean gender, String address, String phoneNumber,
            String email, String identificationCard, String allergy, Boolean isDeleted) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.identificationCard = identificationCard;
        this.allergy = allergy;
        this.isDeleted = isDeleted;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
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

}
