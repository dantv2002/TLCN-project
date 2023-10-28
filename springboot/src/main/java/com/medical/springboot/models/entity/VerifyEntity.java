package com.medical.springboot.models.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "verifications")
public class VerifyEntity {

    @Id
    private String id;
    @Field
    private String email;
    @Field
    private String verifycode;

    public VerifyEntity() {
    }

    public VerifyEntity(String email, String verifycode) {
        this.email = email;
        this.verifycode = verifycode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    @Override
    public String toString() {
        return "VerifyModel [email=" + email + ", id=" + id + ", verifycode=" + verifycode + "]";
    }
    
}
