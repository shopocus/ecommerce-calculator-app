package com.ecommerce.calculator.models;

public class User {

    private String email, name, mobile_no;

    public User(String name, String email, String mobile_no) {
        this.name = name;
        this.email = email;
        this.mobile_no = mobile_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}
