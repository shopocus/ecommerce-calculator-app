package com.ecommerce.calculator.utils;

public class easyShipModel {

    private double weight;
    private double length;
    private double breadth;
    private double height;
    private String easyShipType;

    public easyShipModel(double weight,double length, double breadth, double height, String easyShipType) {
        this.weight = weight;
        this.length = length;
        this.breadth = breadth;
        this.height = height;
        this.easyShipType = easyShipType;
    }

    public double getWeight(){
        return weight;
    }

    public double getLength(){
        return length;
    }

    public double getBreadth(){
        return breadth;
    }

    public double getHeight(){
        return height;
    }

    public String getEasyShipType(){
        return easyShipType;
    }
}
