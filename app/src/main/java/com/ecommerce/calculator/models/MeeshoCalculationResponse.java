package com.ecommerce.calculator.models;

public class MeeshoCalculationResponse {
    private String commissionFees;
    private double shippingFees;
    private double prepaidFees;
    private double CS;
    private double gstOnCS;
    private double CSP;
    private double gstOnCSP;
    private double totalCharges;
    private double bankSettlement;
    private double gstClaim;
    private double gstPayable;
    private double totalGstPayable;
    private double tcs;
    private double profit;
    private double profitPercentage;

    public MeeshoCalculationResponse(String commissionFees, double shippingFees, double prepaidFees, double CS, double gstOnCS, double CSP, double gstOnCSP, double totalCharges,
                                     double bankSettlement, double gstClaim, double gstPayable, double totalGstPayable, double tcs, double profit, double profitPercentage) {
        this.commissionFees = commissionFees;
        this.shippingFees = shippingFees;
        this.prepaidFees = prepaidFees;
        this.CS = CS;
        this.gstOnCS = gstOnCS;
        this.CSP = CSP;
        this.gstOnCSP = gstOnCSP;
        this.totalCharges = totalCharges;
        this.bankSettlement = bankSettlement;
        this.gstClaim = gstClaim;
        this.gstPayable = gstPayable;
        this.totalGstPayable = totalGstPayable;
        this.tcs = tcs;
        this.profit = profit;
        this.profitPercentage = profitPercentage;
    }

    public String getCommissionFees() {
        return commissionFees;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public double getPrepaidFees() {
        return prepaidFees;
    }

    public double getCS() {
        return CS;
    }

    public double getGstOnCS() {
        return gstOnCS;
    }

    public double getCSP() {
        return CSP;
    }

    public double getGstOnCSP() {
        return gstOnCSP;
    }

    public double getTotalCharges() {
        return totalCharges;
    }

    public double getBankSettlement() {
        return bankSettlement;
    }

    public double getProfit() {
        return profit;
    }

    public double getTotalGstPayable() {
        return totalGstPayable;
    }

    public double getTcs() {
        return tcs;
    }

    public double getGstPayable() {
        return gstPayable;
    }

    public double getGstClaim() {
        return gstClaim;
    }

    public double getProfitPercentage() {
        return profitPercentage;
    }
}