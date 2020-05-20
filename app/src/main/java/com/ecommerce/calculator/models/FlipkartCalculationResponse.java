package com.ecommerce.calculator.models;

import com.ecommerce.calculator.utils.FlipkartOutputModel;

public class FlipkartCalculationResponse {

        private FlipkartOutputModel Local;
        private FlipkartOutputModel Zonal;
        private FlipkartOutputModel National;

        public FlipkartCalculationResponse(FlipkartOutputModel Local, FlipkartOutputModel Zonal,
                                              FlipkartOutputModel National) {
            this.Local = Local;
            this.Zonal = Zonal;
            this.National = National;
        }

        public FlipkartOutputModel getLocal() {
            return Local;
        }

        public FlipkartOutputModel getRegional() {
            return Zonal;
        }

        public FlipkartOutputModel getMetro() {
            return National;
        }

}
