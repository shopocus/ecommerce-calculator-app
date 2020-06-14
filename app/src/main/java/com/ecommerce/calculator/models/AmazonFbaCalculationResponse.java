package com.ecommerce.calculator.models;

import com.ecommerce.calculator.utils.AmazonOutputModel;

public class AmazonFbaCalculationResponse {

        private AmazonOutputModel amazonFbaLocal;
        private AmazonOutputModel amazonFbaRegional;
        private AmazonOutputModel amazonFbaNational;

        public AmazonFbaCalculationResponse(AmazonOutputModel amazonFbaLocal, AmazonOutputModel amazonFbaRegional, AmazonOutputModel amazonFbaNational) {
            this.amazonFbaLocal = amazonFbaLocal;
            this.amazonFbaRegional = amazonFbaRegional;
            this.amazonFbaNational = amazonFbaNational;
        }

        public AmazonOutputModel getAmazonLocal() {
            return amazonFbaLocal;
        }

        public AmazonOutputModel getAmazonRegional() {
            return amazonFbaRegional;
        }

        public AmazonOutputModel getAmazonNational() {
            return amazonFbaNational;
        }
    }
