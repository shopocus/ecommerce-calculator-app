package com.ecommerce.calculator.models;

import java.util.List;

public class savedTitleResponse {

    private List<Title> title;
    private String message;

    public savedTitleResponse(List<Title> title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getMessage() {return message; }

    public List<Title> getTitle() {
        return title;
    }
}
