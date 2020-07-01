package com.ecommerce.calculator.models;

public class CommonOutputModel {
    private String CS;
    private String gstOnCS;
    private String bankSettlement;
    private String profit;
    private String totalGstPayable;
    private String tcs;
    private String gstPayable;
    private String gstClaim;
    private String profitPercentage;
    private String commissionFees;
    private String shippingFees;
    private String paymentGatewayFees;
    private String prepaidFees;
    private String fulfillmentFees;
    private String RCSF;
    private String gstOnRCSF;
    private String fixedFees;
    private String collectionFees;
    private String CFCS;
    private String gstOnCFCS;
    private String CSP;
    private String referralFees;
    private String closingFees;
    private String RCS;
    private String gstOnRCS;
    private String gstOnCSP;
    private String totalCharges;
    private CommonOutputModel Local;
    private CommonOutputModel Regional;
    private CommonOutputModel Metro;
    private CommonOutputModel RestOfIndia;
    private CommonOutputModel Kerela;
    private CommonOutputModel flipkartLocal;
    private CommonOutputModel flipkartZonal;
    private CommonOutputModel flipkartNational;
    private CommonOutputModel amazonLocal;
    private CommonOutputModel amazonRegional;
    private CommonOutputModel amazonNational;
    private CommonOutputModel amazonFbaLocal;
    private CommonOutputModel amazonFbaRegional;
    private CommonOutputModel amazonFbaNational;

    public CommonOutputModel(String CS, String gstOnCS, String commissionFees, String shippingFees, String paymentGatewayFees, String prepaidFees, String CSP,
                             String gstOnCSP, String fulfillmentFees, String RCSF, String gstOnRCSF, String fixedFees, String collectionFees, String CFCS, String gstOnCFCS,
                             String totalCharges, String bankSettlement, String profit, String totalGstPayable, String tcs, String referralFees, String closingFees,
                             String RCS, String gstOnRCS, String gstPayable, String gstClaim, String profitPercentage, CommonOutputModel Local, CommonOutputModel Regional,
                             CommonOutputModel Metro, CommonOutputModel RestOfIndia, CommonOutputModel Kerela, CommonOutputModel flipkartLocal, CommonOutputModel flipkartZonal,
                             CommonOutputModel flipkartNational, CommonOutputModel amazonLocal, CommonOutputModel amazonRegional, CommonOutputModel amazonNational,
                             CommonOutputModel amazonFbaLocal, CommonOutputModel amazonFbaRegional, CommonOutputModel amazonFbaNational) {
        this.CS = CS;
        this.gstOnCS = gstOnCS;
        this.bankSettlement = bankSettlement;
        this.profit = profit;
        this.totalGstPayable = totalGstPayable;
        this.tcs = tcs;
        this.gstPayable = gstPayable;
        this.gstClaim = gstClaim;
        this.fixedFees = fixedFees;
        this.collectionFees = collectionFees;
        this.CFCS = CFCS;
        this.gstOnCFCS = gstOnCFCS;
        this.profitPercentage = profitPercentage;
        this.commissionFees = commissionFees;
        this.shippingFees = shippingFees;
        this.fulfillmentFees = fulfillmentFees;
        this.RCSF = RCSF;
        this.gstOnRCSF = gstOnRCSF;
        this.paymentGatewayFees = paymentGatewayFees;
        this.prepaidFees = prepaidFees;
        this.CSP = CSP;
        this.closingFees = closingFees;
        this.referralFees = referralFees;
        this.RCS = RCS;
        this.gstOnRCS = gstOnRCS;
        this.gstOnCSP = gstOnCSP;
        this.totalCharges = totalCharges;
        this.Local = Local;
        this.Regional = Regional;
        this.Metro = Metro;
        this.RestOfIndia = RestOfIndia;
        this.Kerela = Kerela;
        this.flipkartLocal = flipkartLocal;
        this.flipkartZonal = flipkartZonal;
        this.flipkartNational = flipkartNational;
        this.amazonLocal = amazonLocal;
        this.amazonRegional = amazonRegional;
        this.amazonNational = amazonNational;
        this.amazonFbaLocal = amazonFbaLocal;
        this.amazonFbaRegional = amazonFbaRegional;
        this.amazonFbaNational = amazonFbaNational;
    }

    public String getCS() {
        return CS;
    }

    public String getGstOnCS() {
        return gstOnCS;
    }

    public String getBankSettlement() {
        return bankSettlement;
    }

    public String getProfit() {
        return profit;
    }

    public String getTotalGstPayable() {
        return totalGstPayable;
    }

    public String getTcs() {
        return tcs;
    }

    public String getFulfillmentFees() {
        return fulfillmentFees;
    }

    public String getRCSF() {
        return RCSF;
    }

    public String getGstOnRCSF() {
        return gstOnRCSF;
    }

    public String getClosingFees() {
        return closingFees;
    }

    public String getReferralFees() {
        return referralFees;
    }

    public String getGstOnRCS() {
        return gstOnRCS;
    }

    public String getRCS() {
        return RCS;
    }

    public String getGstPayable() {
        return gstPayable;
    }

    public String getGstClaim() {
        return gstClaim;
    }

    public String getProfitPercentage() {
        return profitPercentage;
    }

    public String getCommissionFees() {
        return commissionFees;
    }

    public String getShippingFees() {
        return shippingFees;
    }

    public String getPaymentGatewayFees() {
        return paymentGatewayFees;
    }

    public String getPrepaidFees() {
        return prepaidFees;
    }

    public String getFixedFees() {
        return fixedFees;
    }

    public String getCollectionFees() {
        return collectionFees;
    }

    public String getCFCS() {
        return CFCS;
    }

    public String getGstOnCFCS() {
        return gstOnCFCS;
    }

    public String getCSP() {
        return CSP;
    }

    public String getGstOnCSP() {
        return gstOnCSP;
    }

    public String getTotalCharges() {
        return totalCharges;
    }

    public CommonOutputModel getLocal() {
        return Local;
    }

    public CommonOutputModel getRegional() {
        return Regional;
    }

    public CommonOutputModel getMetro() {
        return Metro;
    }

    public CommonOutputModel getRestOfIndia() {
        return RestOfIndia;
    }

    public CommonOutputModel getKerela() {
        return Kerela;
    }

    public CommonOutputModel getFlipkartLocal() {
        return flipkartLocal;
    }

    public CommonOutputModel getFlipkartZonal() {
        return flipkartZonal;
    }

    public CommonOutputModel getFlipkartNational() {
        return flipkartNational;
    }

    public CommonOutputModel getAmazonLocal() {
        return amazonLocal;
    }

    public CommonOutputModel getAmazonRegional() {
        return amazonRegional;
    }

    public CommonOutputModel getAmazonNational() {
        return amazonNational;
    }

    public CommonOutputModel getAmazonFbaLocal() {
        return amazonFbaLocal;
    }

    public CommonOutputModel getAmazonFbaRegional() {
        return amazonFbaRegional;
    }

    public CommonOutputModel getAmazonFbaNational() {
        return amazonFbaNational;
    }

}
