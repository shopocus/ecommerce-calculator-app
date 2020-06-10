package com.ecommerce.calculator.utils;

public class selfShipModel {
    private double selfShipLocal;
    private double selfShipRegional;
    private double selfShipNational;

    public selfShipModel(double selfShipLocal,double selfShipRegional, double selfShipNational) {
        this.selfShipLocal = selfShipLocal;
        this.selfShipRegional = selfShipRegional;
        this.selfShipNational = selfShipNational;
    }

    public double getSelfShipLocal(){
        return selfShipLocal;
    }

    public double getSelfShipRegional(){
        return selfShipRegional;
    }

    public double getSelfShipNational(){
        return selfShipNational;
    }
}
