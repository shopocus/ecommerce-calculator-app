package com.ecommerce.calculator.models;

public class TitleDataResponse {

    private String category;
    private String sellingPrice;
    private String gstOnProduct;
    private String productPriceWithoutGst;
    private String weight;
    private String courier;
    private String payMode;
    private String inwardShipping;
    private String packagingExpense;
    private String labour;
    private String storageFee;
    private String other;
    private String discountAmount;
    private String discountPercent;
    private String bankSettlement;
    private String totalMeeshoCommision;
    private String profit;
    private String totalGstPayable;
    private String tcs;
    private String gstPayable;
    private String gstClaim;
    private String profitPercentage;
    private MeeshoCalculationResponse local;
    private MeeshoCalculationResponse regional;
    private MeeshoCalculationResponse metro;
    private MeeshoCalculationResponse restOfIndia;
    private MeeshoCalculationResponse kerela;
    private String title;

    public TitleDataResponse(String title, String category, String sellingPrice, String gstOnProduct, String productPriceWithoutGst, String weight,
                             String courier, String payMode, String inwardShipping, String packagingExpense, String labour, String storageFee,
                             String other, String discountAmount, String discountPercent, String bankSettlement, String totalMeeshoCommision,
                             String profit, String totalGstPayable, String tcs, String gstPayable, String gstClaim, String profitPercentage,
                             MeeshoCalculationResponse local, MeeshoCalculationResponse regional, MeeshoCalculationResponse metro,
                             MeeshoCalculationResponse restOfIndia, MeeshoCalculationResponse kerela) {
        this.title = title;
        this.category = category;
        this.sellingPrice = sellingPrice;
        this.gstOnProduct = gstOnProduct;
        this.productPriceWithoutGst = productPriceWithoutGst;
        this.weight = weight;
        this.courier = courier;
        this.payMode = payMode;
        this.inwardShipping = inwardShipping;
        this.packagingExpense = packagingExpense;
        this.labour = labour;
        this.storageFee = storageFee;
        this.other = other;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.bankSettlement = bankSettlement;
        this.totalMeeshoCommision = totalMeeshoCommision;
        this.profit = profit;
        this.totalGstPayable = totalGstPayable;
        this.tcs = tcs;
        this.gstPayable = gstPayable;
        this.gstClaim = gstClaim;
        this.profitPercentage = profitPercentage;
        this.local = local;
        this.regional = regional;
        this.metro = metro;
        this.restOfIndia = restOfIndia;
        this.kerela = kerela;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getSellingPrice() {
            return sellingPrice;
    }

    public String getGstOnProduct() {
        return gstOnProduct;
    }

    public String getProductPriceWithoutGst() {
        return productPriceWithoutGst;
    }

    public String getInwardShipping(){
        return inwardShipping;
    }

    public String getPackagingExpense(){
        return packagingExpense;
    }

    public String getLabour(){
        return labour;
    }

    public String getStorageFee(){
        return storageFee;
    }

    public String getWeight() { return weight; }

    public String getCourier() { return courier; }

    public String getPaymentMode() { return payMode; }

    public String getOther(){
        return other;
    }

    public String getDiscountAmount(){ return discountAmount; }

    public String getDiscountPercent(){ return discountPercent; }

    public String getBankSettlement() { return bankSettlement; }

    public String getTotalMeeshoCommision() { return totalMeeshoCommision; }

    public String getProfit(){
            return profit;
        }

        public String getTotalGstPayable(){
            return totalGstPayable;
        }

        public String getTcs(){
            return tcs;
        }

        public String getGstPayable(){
            return gstPayable;
        }

        public String getGstClaim(){
            return gstClaim;
        }

        public String getProfitPercentage(){
            return profitPercentage;
        }

    public MeeshoCalculationResponse getLocal() {
        return local;
    }

    public MeeshoCalculationResponse getRegional() {
        return regional;
    }

    public MeeshoCalculationResponse getMetro() {
        return metro;
    }

    public MeeshoCalculationResponse getRestOfIndia() {
        return restOfIndia;
    }

    public MeeshoCalculationResponse getKerela() {
        return kerela;
    }

}