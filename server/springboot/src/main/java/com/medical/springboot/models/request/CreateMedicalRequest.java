package com.medical.springboot.models.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CreateMedicalRequest {
    private String department;
    private String clinics;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date date; // Ngày khám
    private String clinicalDiagnosis;
    private String diagnosis;
    private String patientId;

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

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
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

}
