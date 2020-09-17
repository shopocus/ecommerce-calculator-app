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
    private String marketingFees;
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

    public CommonOutputModel(String CS, String gstOnCS, String bankSettlement, String profit, String totalGstPayable, String tcs, String gstPayable, String gstClaim, String profitPercentage, String commissionFees, String marketingFees, String shippingFees, String paymentGatewayFees, String prepaidFees, String fulfillmentFees, String RCSF, String gstOnRCSF, String fixedFees, String collectionFees, String CFCS, String gstOnCFCS, String CSP, String referralFees, String closingFees, String RCS, String gstOnRCS, String gstOnCSP, String totalCharges, CommonOutputModel local, CommonOutputModel regional, CommonOutputModel metro, CommonOutputModel restOfIndia, CommonOutputModel kerela, CommonOutputModel flipkartLocal, CommonOutputModel flipkartZonal, CommonOutputModel flipkartNational, CommonOutputModel amazonLocal, CommonOutputModel amazonRegional, CommonOutputModel amazonNational, CommonOutputModel amazonFbaLocal, CommonOutputModel amazonFbaRegional, CommonOutputModel amazonFbaNational) {
        this.CS = CS;
        this.gstOnCS = gstOnCS;
        this.bankSettlement = bankSettlement;
        this.profit = profit;
        this.totalGstPayable = totalGstPayable;
        this.tcs = tcs;
        this.gstPayable = gstPayable;
        this.gstClaim = gstClaim;
        this.profitPercentage = profitPercentage;
        this.commissionFees = commissionFees;
        this.marketingFees = marketingFees;
        this.shippingFees = shippingFees;
        this.paymentGatewayFees = paymentGatewayFees;
        this.prepaidFees = prepaidFees;
        this.fulfillmentFees = fulfillmentFees;
        this.RCSF = RCSF;
        this.gstOnRCSF = gstOnRCSF;
        this.fixedFees = fixedFees;
        this.collectionFees = collectionFees;
        this.CFCS = CFCS;
        this.gstOnCFCS = gstOnCFCS;
        this.CSP = CSP;
        this.referralFees = referralFees;
        this.closingFees = closingFees;
        this.RCS = RCS;
        this.gstOnRCS = gstOnRCS;
        this.gstOnCSP = gstOnCSP;
        this.totalCharges = totalCharges;
        Local = local;
        Regional = regional;
        Metro = metro;
        RestOfIndia = restOfIndia;
        Kerela = kerela;
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

    public void setCS(String CS) {
        this.CS = CS;
    }

    public void setGstOnCS(String gstOnCS) {
        this.gstOnCS = gstOnCS;
    }

    public void setBankSettlement(String bankSettlement) {
        this.bankSettlement = bankSettlement;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public void setTotalGstPayable(String totalGstPayable) {
        this.totalGstPayable = totalGstPayable;
    }

    public void setTcs(String tcs) {
        this.tcs = tcs;
    }

    public void setGstPayable(String gstPayable) {
        this.gstPayable = gstPayable;
    }

    public void setGstClaim(String gstClaim) {
        this.gstClaim = gstClaim;
    }

    public void setProfitPercentage(String profitPercentage) {
        this.profitPercentage = profitPercentage;
    }

    public void setCommissionFees(String commissionFees) {
        this.commissionFees = commissionFees;
    }

    public String getMarketingFees() {
        return marketingFees;
    }

    public void setMarketingFees(String marketingFees) {
        this.marketingFees = marketingFees;
    }

    public void setShippingFees(String shippingFees) {
        this.shippingFees = shippingFees;
    }

    public void setPaymentGatewayFees(String paymentGatewayFees) {
        this.paymentGatewayFees = paymentGatewayFees;
    }

    public void setPrepaidFees(String prepaidFees) {
        this.prepaidFees = prepaidFees;
    }

    public void setFulfillmentFees(String fulfillmentFees) {
        this.fulfillmentFees = fulfillmentFees;
    }

    public void setRCSF(String RCSF) {
        this.RCSF = RCSF;
    }

    public void setGstOnRCSF(String gstOnRCSF) {
        this.gstOnRCSF = gstOnRCSF;
    }

    public void setFixedFees(String fixedFees) {
        this.fixedFees = fixedFees;
    }

    public void setCollectionFees(String collectionFees) {
        this.collectionFees = collectionFees;
    }

    public void setCFCS(String CFCS) {
        this.CFCS = CFCS;
    }

    public void setGstOnCFCS(String gstOnCFCS) {
        this.gstOnCFCS = gstOnCFCS;
    }

    public void setCSP(String CSP) {
        this.CSP = CSP;
    }

    public void setReferralFees(String referralFees) {
        this.referralFees = referralFees;
    }

    public void setClosingFees(String closingFees) {
        this.closingFees = closingFees;
    }

    public void setRCS(String RCS) {
        this.RCS = RCS;
    }

    public void setGstOnRCS(String gstOnRCS) {
        this.gstOnRCS = gstOnRCS;
    }

    public void setGstOnCSP(String gstOnCSP) {
        this.gstOnCSP = gstOnCSP;
    }

    public void setTotalCharges(String totalCharges) {
        this.totalCharges = totalCharges;
    }

    public void setLocal(CommonOutputModel local) {
        Local = local;
    }

    public void setRegional(CommonOutputModel regional) {
        Regional = regional;
    }

    public void setMetro(CommonOutputModel metro) {
        Metro = metro;
    }

    public void setRestOfIndia(CommonOutputModel restOfIndia) {
        RestOfIndia = restOfIndia;
    }

    public void setKerela(CommonOutputModel kerela) {
        Kerela = kerela;
    }

    public void setFlipkartLocal(CommonOutputModel flipkartLocal) {
        this.flipkartLocal = flipkartLocal;
    }

    public void setFlipkartZonal(CommonOutputModel flipkartZonal) {
        this.flipkartZonal = flipkartZonal;
    }

    public void setFlipkartNational(CommonOutputModel flipkartNational) {
        this.flipkartNational = flipkartNational;
    }

    public void setAmazonLocal(CommonOutputModel amazonLocal) {
        this.amazonLocal = amazonLocal;
    }

    public void setAmazonRegional(CommonOutputModel amazonRegional) {
        this.amazonRegional = amazonRegional;
    }

    public void setAmazonNational(CommonOutputModel amazonNational) {
        this.amazonNational = amazonNational;
    }

    public void setAmazonFbaLocal(CommonOutputModel amazonFbaLocal) {
        this.amazonFbaLocal = amazonFbaLocal;
    }

    public void setAmazonFbaRegional(CommonOutputModel amazonFbaRegional) {
        this.amazonFbaRegional = amazonFbaRegional;
    }

    public void setAmazonFbaNational(CommonOutputModel amazonFbaNational) {
        this.amazonFbaNational = amazonFbaNational;
    }
}
