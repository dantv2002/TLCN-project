package com.medical.backendsystem.models.response;

public class BaseResponse {
    private String message;
    private Object data;

    public BaseResponse() {
    }

    public BaseResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
