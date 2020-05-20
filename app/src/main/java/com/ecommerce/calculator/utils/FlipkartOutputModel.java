package com.ecommerce.calculator.utils;

public class FlipkartOutputModel {
    private  double commissionFees;
    private  double fixedFees;
    private  double collectionFees;
    private  double shippingFees;
    private  double CFCS;
    private  double gstOnCFCS;
    private  double totalCharges;
    private  double bankSettlement;
    private  double totalGstPayable;
    private  double tcs;
    private  double gstPayable;
    private  double profit;
    private  double profitPercentage;

    public FlipkartOutputModel(double commissionFees, double fixedFees, double collectionFees, double shippingFees, double CFCS, double gstOnCFCS,
                               double totalCharges, double bankSettlement, double totalGstPayable, double tcs, double gstPayable, double profit,
                               double profitPercentage)
    {
        this.commissionFees = commissionFees;
        this.fixedFees = fixedFees;
        this.collectionFees = collectionFees;
        this.shippingFees = shippingFees;
        this.CFCS = CFCS;
        this.gstOnCFCS = gstOnCFCS;
        this.totalCharges = totalCharges;
        this.bankSettlement = bankSettlement;
        this.profit = profit;
        this.totalGstPayable = totalGstPayable;
        this.tcs = tcs;
        this.gstPayable = gstPayable;
        this.profitPercentage = profitPercentage;
    }

    public double getCommissionFees() { return commissionFees; }

    public double getFixedFees() { return fixedFees; }

    public double getCollectionFees() { return collectionFees; }

    public double getShippingFees() { return shippingFees; }

    public double getCFCS() { return CFCS; }

    public double getGstOnCFCS() { return gstOnCFCS; }

    public double getTotalCharges() { return totalCharges; }

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
