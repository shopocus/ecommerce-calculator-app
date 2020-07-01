package com.ecommerce.calculator.models;

public class TitleDataResponse {

    private CommonInputModel input;
    private CommonOutputModel output;

    public TitleDataResponse(CommonInputModel input, CommonOutputModel output) {
        this.input = input;
        this.output = output;
    }

    public CommonInputModel getInput() {
        return input;
    }

    public CommonOutputModel getOutput() {
        return output;
    }
}