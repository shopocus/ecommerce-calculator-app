package com.ecommerce.calculator.models;

public class LoginResponse {

    private String message;
    private User user;

    public LoginResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}