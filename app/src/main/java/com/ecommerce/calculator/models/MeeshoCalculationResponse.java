package com.ecommerce.calculator.models;

public class MeeshoCalculationResponse {
    private  double bankSettlement;
    private  double totalMeeshoCommision;
    private  double profit;
    private  double totalGstPayable;
    private  double tcs;
    private  double gstPayable;
    private  double gstClaim;
    private  double profitPercentage;

    public MeeshoCalculationResponse(double bankSettlement, double totalMeeshoCommision, double profit, double totalGstPayable, double tcs,
                                     double gstPayable, double gstClaim, double profitPercentage) {
        this.bankSettlement = bankSettlement;
        this.totalMeeshoCommision = totalMeeshoCommision;
        this.profit = profit;
        this.totalGstPayable = totalGstPayable;
        this.tcs = tcs;
        this.gstPayable = gstPayable;
        this.gstClaim = gstClaim;
        this.profitPercentage = profitPercentage;
    }

    public double getBankSettlement() {
        return bankSettlement;
    }

    public double getTotalMeeshoCommision() {
        return totalMeeshoCommision;
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