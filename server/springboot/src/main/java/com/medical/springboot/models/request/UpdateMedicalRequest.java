package com.medical.springboot.models.request;

public class UpdateMedicalRequest extends CreateMedicalRequest {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
