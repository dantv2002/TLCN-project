package com.medical.springboot.models.response;

public class PatientSearchResponse {
    private String name; // patient name
    private String yearBirthday; // year of birthday
    private String idPatient; // id of patient
    private Object medicals; // medicals of patient
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getYearBirthday() {
        return yearBirthday;
    }
    public void setYearBirthday(String yearBirthday) {
        this.yearBirthday = yearBirthday;
    }
    public String getIdPatient() {
        return idPatient;
    }
    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }
    public Object getMedicals() {
        return medicals;
    }
    public void setMedicals(Object medicals) {
        this.medicals = medicals;
    }

    

}
