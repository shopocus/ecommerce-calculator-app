package com.ecommerce.calculator.models;

import com.ecommerce.calculator.utils.AmazonOutputModel;
import com.ecommerce.calculator.utils.FlipkartOutputModel;
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
    private String shipping;
    private String paymentGatewayCharge;
    private String other;
    private String discountAmount;
    private String discountPercent;
    private String shipmentType;
    private easyShipModel easyShip;
    private selfShipModel selfShip;
    private String title;

    public CommonInputModel(String title, String category, String subcategory, String sellingPrice, String gstOnProduct, String productPriceWithoutGst,
                            String weight, String length, String breadth, String height, String customerType, String courier, String payMode,
                            String inwardShipping, String packagingExpense, String labour, String storageFee, String commission, String shipping,
                            String paymentGatewayCharge, String other, String discountAmount, String discountPercent, String shipmentType, easyShipModel easyShip,
                            selfShipModel selfShip) {
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
        this.commission = commission;
        this.shipping = shipping;
        this.paymentGatewayCharge = paymentGatewayCharge;
        this.other = other;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.shipmentType = shipmentType;
        this.easyShip = easyShip;
        this.selfShip = selfShip;
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
}
