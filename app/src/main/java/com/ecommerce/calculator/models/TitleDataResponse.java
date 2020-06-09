package com.ecommerce.calculator.models;

import com.ecommerce.calculator.utils.AmazonOutputModel;
import com.ecommerce.calculator.utils.FlipkartOutputModel;

public class TitleDataResponse {

    private String category;
    private String subcategory;
    private String sellingPrice;
    private String gstOnProduct;
    private String productPriceWithoutGst;
    private String weight;
    private String length;
    private String breadth;
    private String height;
    private String customerType;
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
    private String totalCommision;
    private String profit;
    private String totalGstPayable;
    private String tcs;
    private String gstPayable;
    private String gstClaim;
    private String profitPercentage;
    private String shipmentType;
    private String easyShipType;
    private String selfShipLocal;
    private String selfShipRegional;
    private String selfShipNational;
    private MeeshoCalculationResponse local;
    private MeeshoCalculationResponse regional;
    private MeeshoCalculationResponse metro;
    private MeeshoCalculationResponse restOfIndia;
    private MeeshoCalculationResponse kerela;
    private FlipkartOutputModel flipkartLocal;
    private FlipkartOutputModel flipkartZonal;
    private FlipkartOutputModel flipkartNational;
    private AmazonOutputModel amazonLocal;
    private AmazonOutputModel amazonRegional;
    private AmazonOutputModel amazonNational;
    private String title;

    public TitleDataResponse(String title, String category, String subcategory, String sellingPrice, String gstOnProduct, String productPriceWithoutGst, String weight,
                             String length, String breadth, String height, String customerType, String courier, String payMode, String inwardShipping,
                             String packagingExpense, String labour, String storageFee, String other, String discountAmount, String discountPercent,
                             String bankSettlement, String totalCommision, String profit, String totalGstPayable, String tcs, String gstPayable,
                             String gstClaim, String profitPercentage, String shipmentType, String easyShipType, String selfShipLocal,
                             String selfShipRegional, String selfShipNational, MeeshoCalculationResponse local, MeeshoCalculationResponse regional,
                             MeeshoCalculationResponse metro, MeeshoCalculationResponse restOfIndia, MeeshoCalculationResponse kerela,
                             FlipkartOutputModel flipkartLocal, FlipkartOutputModel flipkartZonal, FlipkartOutputModel flipkartNational,
                             AmazonOutputModel amazonLocal, AmazonOutputModel amazonRegional, AmazonOutputModel amazonNational) {
        this.title = title;
        this.category = category;
        this.subcategory = subcategory;
        this.sellingPrice = sellingPrice;
        this.gstOnProduct = gstOnProduct;
        this.productPriceWithoutGst = productPriceWithoutGst;
        this.weight = weight;
        this.length = length;
        this.breadth = breadth;
        this.height = height;
        this.customerType = customerType;
        this.courier = courier;
        this.payMode = payMode;
        this.inwardShipping = inwardShipping;
        this.packagingExpense = packagingExpense;
        this.labour = labour;
        this.storageFee = storageFee;
        this.other = other;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.shipmentType = shipmentType;
        this.easyShipType = easyShipType;
        this.selfShipLocal = selfShipLocal;
        this.selfShipRegional = selfShipRegional;
        this.selfShipNational = selfShipNational;
        this.bankSettlement = bankSettlement;
        this.totalCommision = totalCommision;
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
        this.flipkartLocal = flipkartLocal;
        this.flipkartZonal = flipkartZonal;
        this.flipkartNational = flipkartNational;
        this.amazonLocal = amazonLocal;
        this.amazonRegional = amazonRegional;
        this.amazonNational = amazonNational;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
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

    public String getLength() { return length; }

    public String getBreadth() { return breadth; }

    public String getHeight() { return height; }

    public String getCustomerType() { return customerType; }

    public String getCourier() { return courier; }

    public String getPaymentMode() { return payMode; }

    public String getOther(){
        return other;
    }

    public String getDiscountAmount(){ return discountAmount; }

    public String getDiscountPercent(){ return discountPercent; }

    public String getShipmentType() {
        return shipmentType;
    }

    public String getEasyShipType(){
        return  easyShipType;
    }
    public String getSelfShipLocal(){
        return selfShipLocal;
    }

    public String getSelfShipRegional(){
        return  selfShipRegional;
    }

    public String getSelfShipNational(){
        return selfShipNational;
    }

    public String getBankSettlement() { return bankSettlement; }

    public String getTotalCommision() { return totalCommision; }

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

    public FlipkartOutputModel getFlipkartLocal() { return flipkartLocal; }

    public FlipkartOutputModel getFlipkartZonal() { return flipkartZonal; }

    public FlipkartOutputModel getFlipkartNational() { return flipkartNational; }

    public AmazonOutputModel getAmazonLocal() {
        return amazonLocal;
    }

    public AmazonOutputModel getAmazonRegional() {
        return amazonRegional;
    }

    public AmazonOutputModel getAmazonNational() {
        return amazonNational;
    }

}