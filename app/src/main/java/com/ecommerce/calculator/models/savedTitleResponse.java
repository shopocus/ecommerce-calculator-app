package com.ecommerce.calculator.models;

import java.util.List;

public class savedTitleResponse {

    private List<String> title;
    private String message;

    public savedTitleResponse(List<String> title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getMessage() {return message; }

    public List<String> getTitle() {
        return title;
    }
}
