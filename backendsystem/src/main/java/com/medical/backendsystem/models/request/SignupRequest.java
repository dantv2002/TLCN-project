package com.medical.backendsystem.models.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SignupRequest {
    // Account
    private String email;
    private String password;
    private String role;
    // Patient
    private String fullname;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthday;
    private String address;
    private String phonenumber;
    // Verify
    private String verifycode;
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
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
    public String getAddress() {
        return address;
    }
    public void setDddress(String address) {
        this.address = address;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getVerifycode() {
        return verifycode;
    }
    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }
    
    
}
