package com.ecommerce.calculator.models;

import com.ecommerce.calculator.utils.AmazonOutputModel;
import com.ecommerce.calculator.utils.FlipkartOutputModel;
import com.ecommerce.calculator.utils.easyShipModel;
import com.ecommerce.calculator.utils.selfShipModel;

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
    private String commission;
    private String shipping;
    private String paymentGatewayCharge;
    private String other;
    private String discountAmount;
    private String discountPercent;
    private String CS;
    private String gstOnCS;
    private String bankSettlement;
    private String totalCommision;
    private String profit;
    private String totalGstPayable;
    private String tcs;
    private String gstPayable;
    private String gstClaim;
    private String profitPercentage;
    private String shipmentType;
    private String commissionFees;
    private String shippingFees;
    private String paymentGatewayFees;
    private String CSP;
    private String gstOnCSP;
    private String totalCharges;
    private MeeshoInputModel input;
    private MeeshoOutputModel output;
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
    private AmazonOutputModel amazonFbaLocal;
    private AmazonOutputModel amazonFbaRegional;
    private AmazonOutputModel amazonFbaNational;
    private easyShipModel easyShip;
    private selfShipModel selfShip;
    private String title;

    public TitleDataResponse(String title, String category, String subcategory, String sellingPrice, String gstOnProduct, String productPriceWithoutGst,
                             String weight, String length, String breadth, String height, String customerType, String courier, String payMode,
                             String inwardShipping, String packagingExpense, String labour, String storageFee, String commission, String shipping,
                             String paymentGatewayCharge, String other, String discountAmount, String discountPercent, String CS, String gstOnCS,
                             String commissionFees, String shippingFees, String paymentGatewayFees, String CSP, String gstOnCSP, String totalCharges,
                             String bankSettlement, String totalCommision, String profit, String totalGstPayable, String tcs, String gstPayable, String gstClaim,
                             String profitPercentage, String shipmentType, easyShipModel easyShip, selfShipModel selfShip, MeeshoInputModel input, MeeshoOutputModel output,
                             MeeshoCalculationResponse local, MeeshoCalculationResponse regional, MeeshoCalculationResponse metro,
                             MeeshoCalculationResponse restOfIndia, MeeshoCalculationResponse kerela, FlipkartOutputModel flipkartLocal,
                             FlipkartOutputModel flipkartZonal, FlipkartOutputModel flipkartNational, AmazonOutputModel amazonLocal,
                             AmazonOutputModel amazonRegional, AmazonOutputModel amazonNational, AmazonOutputModel amazonFbaLocal, AmazonOutputModel
                                     amazonFbaRegional, AmazonOutputModel amazonFbaNational) {
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
        this.CS = CS;
        this.gstOnCS = gstOnCS;
        this.shipmentType = shipmentType;
        this.easyShip = easyShip;
        this.selfShip = selfShip;
        this.bankSettlement = bankSettlement;
        this.totalCommision = totalCommision;
        this.profit = profit;
        this.totalGstPayable = totalGstPayable;
        this.tcs = tcs;
        this.gstPayable = gstPayable;
        this.gstClaim = gstClaim;
        this.profitPercentage = profitPercentage;
        this.commissionFees = commissionFees;
        this.shippingFees = shippingFees;
        this.paymentGatewayFees = paymentGatewayFees;
        this.CSP = CSP;
        this.gstOnCSP = gstOnCSP;
        this.totalCharges = totalCharges;
        this.local = local;
        this.regional = regional;
        this.metro = metro;
        this.restOfIndia = restOfIndia;
        this.kerela = kerela;
        this.input = input;
        this.output = output;
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

    public String getCS() {
        return CS;
    }

    public String getGstOnCS() {
        return gstOnCS;
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

    public String getBankSettlement() {
        return bankSettlement;
    }

    public String getTotalCommision() {
        return totalCommision;
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

    public String getCSP() {
        return CSP;
    }

    public String getGstOnCSP() {
        return gstOnCSP;
    }

    public String getTotalCharges() {
        return totalCharges;
    }

    public MeeshoInputModel getInput() {
        return input;
    }

    public MeeshoOutputModel getOutput() { return output; }

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

    public FlipkartOutputModel getFlipkartLocal() {
        return flipkartLocal;
    }

    public FlipkartOutputModel getFlipkartZonal() {
        return flipkartZonal;
    }

    public FlipkartOutputModel getFlipkartNational() {
        return flipkartNational;
    }

    public AmazonOutputModel getAmazonLocal() {
        return amazonLocal;
    }

    public AmazonOutputModel getAmazonRegional() {
        return amazonRegional;
    }

    public AmazonOutputModel getAmazonNational() {
        return amazonNational;
    }

    public AmazonOutputModel getAmazonFbaLocal() {
        return amazonFbaLocal;
    }

    public AmazonOutputModel getAmazonFbaRegional() {
        return amazonFbaRegional;
    }

    public AmazonOutputModel getAmazonFbaNational() {
        return amazonFbaNational;
    }

}