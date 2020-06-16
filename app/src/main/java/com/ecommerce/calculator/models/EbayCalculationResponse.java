package com.ecommerce.calculator.models;

public class EbayCalculationResponse {
    private double commissionFees;
    private double shippingFees;
    private double paymentGatewayFees;
    private double CSP;
    private double gstOnCSP;
    private double totalCharges;
    private  double bankSettlement;
    private  double totalGstPayable;
    private  double gstClaim;
    private  double tcs;
    private  double gstPayable;
    private double profit;
    private  double profitPercentage;

    public EbayCalculationResponse(double commissionFees, double shippingFees, double paymentGatewayFees, double CSP, double gstOnCSP,
                                   double totalCharges, double bankSettlement, double totalGstPayable, double gstClaim,  double tcs,
                                     double gstPayable, double profit, double profitPercentage) {
        this.commissionFees = commissionFees;
        this.shippingFees = shippingFees;
        this.paymentGatewayFees = paymentGatewayFees;
        this.CSP = CSP;
        this.gstOnCSP = gstOnCSP;
        this.totalCharges = totalCharges;
        this.bankSettlement = bankSettlement;
        this.totalGstPayable = totalGstPayable;
        this.gstClaim = gstClaim;
        this.tcs = tcs;
        this.gstPayable = gstPayable;
        this.profit = profit;
        this.profitPercentage = profitPercentage;
    }

    public double getCommissionFees() {
        return commissionFees;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public double getPaymentGatewayCharge() {
        return paymentGatewayFees;
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

    public double getGstClaim(){
        return gstClaim;
    }

    public double getProfitPercentage(){
        return profitPercentage;
    }
}
