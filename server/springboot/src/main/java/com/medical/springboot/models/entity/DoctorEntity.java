package com.medical.springboot.models.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Doctors")
public class DoctorEntity extends PersonAbstractEntity {

    @Field
    private String department; // Khoa
    @Field
    private String title; // Chức danh

    public DoctorEntity() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DoctorEntity [id=" + this.getId() + ", fullname=" + this.getFullName() + ", birthday="
                + this.getBirthday() + ", gender=" + this.getGender() + ", address=" + this.getAddress()
                + ", phoneNumber=" + this.getPhoneNumber() + ", email=" + this.getEmail() + ", identificationCard="
                + this.getIdentificationCard() + ", department=" + department + ", title=" + title
                + "]";
    }

}
