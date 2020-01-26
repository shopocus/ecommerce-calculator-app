package com.ecommerce.calculator.models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    private String message;
    private User user;

    public DefaultResponse(String msg, User user) {
        this.message = msg;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
