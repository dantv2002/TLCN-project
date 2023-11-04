package com.medical.springboot.models.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "MedicalRecords")
public class MedicalEntity {

    @Id
    private String id;
    @Field
    private String department; // Khoa
    @Field
    private String clinics; // Phòng khám
    @Field
    private Date date; // Ngày khám
    @Field
    private String doctorId; // Id bác sĩ khám
    @Field
    private String clinicalDiagnosis; // Chẩn đoán lâm sàng
    @Field
    private DiagnosticImageEntity diagnosticImages; // Chẩn đoán hình ảnh
    @Indexed
    private String diagnosis; // Kết luận chẩn đoán
    @Field
    private String patientId; // Id hồ sơ bệnh nhân

    public MedicalEntity() {
    }

    public MedicalEntity(String department, String clinics, Date date, String doctorId, String clinicalDiagnosis,
            DiagnosticImageEntity diagnosticImages, String diagnosis, String patientId) {
        this.department = department;
        this.clinics = clinics;
        this.date = date;
        this.doctorId = doctorId;
        this.clinicalDiagnosis = clinicalDiagnosis;
        this.diagnosticImages = diagnosticImages;
        this.diagnosis = diagnosis;
        this.patientId = patientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClinics() {
        return clinics;
    }

    public void setClinics(String clinics) {
        this.clinics = clinics;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public DiagnosticImageEntity getDiagnosticImages() {
        return diagnosticImages;
    }

    public void setDiagnosticImages(DiagnosticImageEntity diagnosticImages) {
        this.diagnosticImages = diagnosticImages;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Medical [id=" + id + ", department=" + department + ", clinics=" + clinics + ", date=" + date
                + ", doctorId=" + doctorId + ", clinicalDiagnosis=" + clinicalDiagnosis + ", diagnosticImages="
                + diagnosticImages + ", diagnosis=" + diagnosis + ", patientId=" + patientId + "]";
    }
}
