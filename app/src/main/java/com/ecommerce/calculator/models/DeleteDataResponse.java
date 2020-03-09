package com.ecommerce.calculator.models;

public class DeleteDataResponse {

    private String message;

    public DeleteDataResponse(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

}
