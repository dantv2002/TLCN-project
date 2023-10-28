package com.medical.springboot.models.entity;

import java.util.Date;
import java.awt.image.BufferedImage;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "MedicalRecords")
public class MedicalEntity {
    
    @Id
    private String id;
    @Field
    private String department;
    @Field
    private String clinics;
    @Field
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date date;
    @Field
    private String doctor;
    @Field
    private String result;
    @Field
    private String urlImage;
    @Field
    private String patientId;
    

}
