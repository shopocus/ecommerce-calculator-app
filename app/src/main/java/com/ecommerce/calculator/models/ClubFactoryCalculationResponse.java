package com.ecommerce.calculator.models;

public class ClubFactoryCalculationResponse {

    private MeeshoCalculationResponse Local;
    private MeeshoCalculationResponse Regional;
    private MeeshoCalculationResponse Metro;
    private MeeshoCalculationResponse RestOfIndia;
    private MeeshoCalculationResponse Kerela;

    public ClubFactoryCalculationResponse(MeeshoCalculationResponse Local, MeeshoCalculationResponse Regional,
                                          MeeshoCalculationResponse Metro, MeeshoCalculationResponse RestOfIndia,
                                          MeeshoCalculationResponse Kerela) {
        this.Local = Local;
        this.Regional = Regional;
        this.Metro = Metro;
        this.RestOfIndia = RestOfIndia;
        this.Kerela = Kerela;
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

    public MeeshoCalculationResponse getKerela() {
        return Kerela;
    }

}
