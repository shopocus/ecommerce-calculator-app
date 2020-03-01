package com.ecommerce.calculator.models;

import java.util.List;

public class savedTitleResponse {

    private List<String> title;

    public savedTitleResponse(List<String> title) {
        this.title = title;
    }

    public List<String> getTitle() {
        return title;
    }
}
