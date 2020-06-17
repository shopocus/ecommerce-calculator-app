package com.ecommerce.calculator.models;

import com.ecommerce.calculator.utils.FlipkartOutputModel;

public class FlipkartCalculationResponse {

    private FlipkartOutputModel flipkartLocal;
    private FlipkartOutputModel flipkartZonal;
    private FlipkartOutputModel flipkartNational;

    public FlipkartCalculationResponse(FlipkartOutputModel flipkartLocal, FlipkartOutputModel flipkartZonal,
                                       FlipkartOutputModel flipkartNational) {
        this.flipkartLocal = flipkartLocal;
        this.flipkartZonal = flipkartZonal;
        this.flipkartNational = flipkartNational;
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

}
