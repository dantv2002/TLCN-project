package com.medical.springboot.models.response;

import com.medical.springboot.models.entity.DiagnosticImageEntity;

public class MedicalDetailResponse extends MedicalResponse {
    private String clinics;
    private String clinicalDiagnosis;
    private String diagnosis;
    private DiagnosticImageEntity diagnosticImages;

    public String getClinics() {
        return clinics;
    }

    public void setClinics(String clinics) {
        this.clinics = clinics;
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

    public DiagnosticImageEntity getDiagnosticImages() {
        return diagnosticImages;
    }

    public void setDiagnosticImages(DiagnosticImageEntity diagnosticImages) {
        this.diagnosticImages = diagnosticImages;
    }

}
