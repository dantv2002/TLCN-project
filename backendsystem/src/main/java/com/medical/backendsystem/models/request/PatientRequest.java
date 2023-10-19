package com.medical.backendsystem.models.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PatientRequest {
    private String fullname;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthday;
    private Boolean gender; // Nam = 1, Nữ = 0
    private String address;
    private String phonenumber;
    private String email;
    private String identificationCard;
    private String allergy;
    
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
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
    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    
}
