package com.ecommerce.calculator.models;

public class SaveResponse {

    private String email;

    public SaveResponse(String email) {
        this.email = email;
        //this.user = user;
    }

    public String getEmail() {
        return email;
    }

}
