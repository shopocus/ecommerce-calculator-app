package com.ecommerce.calculator.models;

public class Title {
        private String title, date;

    public Title(String title, String date) {
        this.title = title;
        this.date = date;
    }

        public String getTitle(){
            return title;
        }

        public String getDate() { return date; }
}