package com.ecommerce.calculator.models;

import com.ecommerce.calculator.utils.easyShipModel;
import com.ecommerce.calculator.utils.selfShipModel;

public class CommonInputModel {
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
    private String commission;
    private String marketingFees;
    private String shipping;
    private String paymentGatewayCharge;
    private String other;
    private String discountAmount;
    private String discountPercent;
    private String shipmentType;
    private easyShipModel easyShip;
    private selfShipModel selfShip;
    private String title;

    public CommonInputModel(String category, String subcategory, String sellingPrice, String gstOnProduct, String productPriceWithoutGst, String weight, String length, String breadth, String height, String customerType, String courier, String payMode, String inwardShipping, String packagingExpense, String labour, String storageFee, String commission, String marketingFees, String shipping, String paymentGatewayCharge, String other, String discountAmount, String discountPercent, String shipmentType, easyShipModel easyShip, selfShipModel selfShip, String title) {
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
        this.commission = commission;
        this.marketingFees = marketingFees;
        this.shipping = shipping;
        this.paymentGatewayCharge = paymentGatewayCharge;
        this.other = other;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.shipmentType = shipmentType;
        this.easyShip = easyShip;
        this.selfShip = selfShip;
        this.title = title;
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

    public String getInwardShipping() {
        return inwardShipping;
    }

    public String getPackagingExpense() {
        return packagingExpense;
    }

    public String getLabour() {
        return labour;
    }

    public String getStorageFee() {
        return storageFee;
    }

    public String getCommission() {
        return commission;
    }

    public String getShipping() {
        return shipping;
    }

    public String getPaymentGatewayCharge() {
        return paymentGatewayCharge;
    }

    public String getWeight() {
        return weight;
    }

    public String getLength() {
        return length;
    }

    public String getBreadth() {
        return breadth;
    }

    public String getHeight() {
        return height;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getCourier() {
        return courier;
    }

    public String getPaymentMode() {
        return payMode;
    }

    public String getOther() {
        return other;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public easyShipModel getEasyShip() {
        return easyShip;
    }

    public selfShipModel getSelfShip() {
        return selfShip;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setGstOnProduct(String gstOnProduct) {
        this.gstOnProduct = gstOnProduct;
    }

    public void setProductPriceWithoutGst(String productPriceWithoutGst) {
        this.productPriceWithoutGst = productPriceWithoutGst;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setBreadth(String breadth) {
        this.breadth = breadth;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public void setInwardShipping(String inwardShipping) {
        this.inwardShipping = inwardShipping;
    }

    public void setPackagingExpense(String packagingExpense) {
        this.packagingExpense = packagingExpense;
    }

    public void setLabour(String labour) {
        this.labour = labour;
    }

    public void setStorageFee(String storageFee) {
        this.storageFee = storageFee;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getMarketingFees() {
        return marketingFees;
    }

    public void setMarketingFees(String marketingFees) {
        this.marketingFees = marketingFees;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public void setPaymentGatewayCharge(String paymentGatewayCharge) {
        this.paymentGatewayCharge = paymentGatewayCharge;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public void setEasyShip(easyShipModel easyShip) {
        this.easyShip = easyShip;
    }

    public void setSelfShip(selfShipModel selfShip) {
        this.selfShip = selfShip;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
