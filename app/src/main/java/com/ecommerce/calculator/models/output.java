package com.ecommerce.calculator.models;

public class output {
    private String title;
    private String answer;

    public output(String title, String answer){
        this.title = title;
        this.answer = answer;
    }

    public String getTitle(){
        return title;
    }
    public String getAnswer(){
        return answer;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }
}
