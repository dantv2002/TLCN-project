package com.medical.springboot.models.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * DiagnosticImageEntity
 */
@Document(collection = "DiagnosticImages")
public class DiagnosticImageEntity {

    @Id
    private String id;
    @Field
    private String method; // Phương pháp: CT, MRI, X-Quang
    @Field
    private String content; // Nội dung yêu cầu
    @Field
    private String doctorIdPerform; // Id bác sĩ thực hiện
    @Field
    private List<String> urlImage; // Đường dẫn ảnh
    @Field
    private String conclude; // Kết luận
    @Field
    private String medicalId; // Mã bệnh án

    public DiagnosticImageEntity() {
    }

    public DiagnosticImageEntity(String method, String content, String doctorIdPerform, List<String> urlImage,
            String conclude, String medicalId) {
        this.method = method;
        this.content = content;
        this.doctorIdPerform = doctorIdPerform;
        this.urlImage = urlImage;
        this.conclude = conclude;
        this.medicalId = medicalId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDoctorIdPerform() {
        return doctorIdPerform;
    }

    public void setDoctorIdPerform(String doctorIdPerform) {
        this.doctorIdPerform = doctorIdPerform;
    }

    public List<String> getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(List<String> urlImage) {
        this.urlImage = urlImage;
    }

    public String getConclude() {
        return conclude;
    }

    public void setConclude(String conclude) {
        this.conclude = conclude;
    }

    public String getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(String medicalId) {
        this.medicalId = medicalId;
    }

    @Override
    public String toString() {
        return "DiagnosticImageEntity [id=" + id + ", method=" + method + ", content=" + content + ", doctorIdPerform="
                + doctorIdPerform + ", urlImage=" + urlImage + ", conclude=" + conclude + ", medicalId=" + medicalId
                + "]";
    }

}