package com.ecommerce.calculator.models;

public class SaveResponse {

    private String message;
    private String email;

    public SaveResponse(String message, String email) {
        this.message = message;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage(){
        return message;
    }

}