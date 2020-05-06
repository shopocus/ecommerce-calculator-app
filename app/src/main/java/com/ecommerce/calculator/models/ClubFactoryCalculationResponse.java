package com.ecommerce.calculator.models;

import java.util.List;

public class ClubFactoryCalculationResponse {

    private MeeshoCalculationResponse Local;
    private MeeshoCalculationResponse Regional;
    private MeeshoCalculationResponse Metro;
    private MeeshoCalculationResponse RestOfIndia;
    private MeeshoCalculationResponse Kerala;

    public ClubFactoryCalculationResponse(MeeshoCalculationResponse Local, MeeshoCalculationResponse Regional,
                                          MeeshoCalculationResponse Metro, MeeshoCalculationResponse RestOfIndia,
                                          MeeshoCalculationResponse Kerala) {
        this.Local = Local;
        this.Regional = Regional;
        this.Metro = Metro;
        this.RestOfIndia = RestOfIndia;
        this.Kerala = Kerala;
    }

    public MeeshoCalculationResponse getLocal() {
            return Local;
        }

    public MeeshoCalculationResponse getRegional() {
        return Regional;
    }

    public MeeshoCalculationResponse getMetro() {
        return Metro;
    }

    public MeeshoCalculationResponse getRestOfIndia() {
        return RestOfIndia;
    }

    public MeeshoCalculationResponse getKerala() {
        return Kerala;
    }

}
