package com.medical.backendsystem.models.request;

public class EmailRequest {
    private String toemail;
    private String username;
    
    public String getToemail() {
        return toemail;
    }
    public void setToemail(String toemail) {
        this.toemail = toemail;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
   
}
