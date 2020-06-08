package com.ecommerce.calculator.models;

import com.ecommerce.calculator.utils.AmazonOutputModel;

public class AmazonCalculationResponse {

    private AmazonOutputModel amazonLocal;
    private AmazonOutputModel amazonRegional;
    private AmazonOutputModel amazonNational;

    public AmazonCalculationResponse(AmazonOutputModel amazonLocal, AmazonOutputModel amazonRegional, AmazonOutputModel amazonNational) {
        this.amazonLocal = amazonLocal;
        this.amazonRegional = amazonRegional;
        this.amazonNational = amazonNational;
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
}
