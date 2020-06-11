package com.ecommerce.calculator.utils;

public class AmazonOutputModel {
    private  double referralFees;
    private  double closingFees;
    private  double shippingFees;
    private  double RCS;
    private double RCF;
    private double gstOnRCF;
    private  double gstOnRCS;
    private  double totalCharges;
    private  double gstClaim;
    private  double bankSettlement;
    private  double totalGstPayable;
    private  double tcs;
    private  double gstPayable;
    private  double profit;
    private  double profitPercentage;

    public AmazonOutputModel(double referralFees, double closingFees, double shippingFees, double RCS, double RCF, double gstOnRCF, double gstOnRCS,
                             double totalCharges, double gstClaim, double bankSettlement, double totalGstPayable, double tcs, double gstPayable,
                             double profit, double profitPercentage)
    {
        this.referralFees = referralFees;
        this.closingFees = closingFees;
        this.shippingFees = shippingFees;
        this.RCS = RCS;
        this.RCF = RCF;
        this.gstOnRCF = gstOnRCF;
        this.gstOnRCS = gstOnRCS;
        this.totalCharges = totalCharges;
        this.gstClaim = gstClaim;
        this.bankSettlement = bankSettlement;
        this.profit = profit;
        this.totalGstPayable = totalGstPayable;
        this.tcs = tcs;
        this.gstPayable = gstPayable;
        this.profitPercentage = profitPercentage;
    }

    public double getReferralFees() { return referralFees; }

    public double getClosingFees() { return closingFees; }

    public double getShippingFees() { return shippingFees; }

    public double getRCS() { return RCS; }

    public double getRCF() {
        return RCF;
    }

    public double getGstOnRCF() {
        return gstOnRCF;
    }

    public double getGstOnRCS() { return gstOnRCS; }

    public double getTotalCharges() { return totalCharges; }

    public double getGstClaim() {
        return gstClaim;
    }

    public double getBankSettlement() {
        return bankSettlement;
    }

    public double getProfit(){
        return profit;
    }

    public double getTotalGstPayable(){
        return totalGstPayable;
    }

    public double getTcs(){
        return tcs;
    }

    public double getGstPayable(){
        return gstPayable;
    }

    public double getProfitPercentage(){
        return profitPercentage;
    }
}
