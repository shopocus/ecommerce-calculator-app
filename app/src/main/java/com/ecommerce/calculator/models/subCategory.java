package com.ecommerce.calculator.models;

import java.util.ArrayList;

public class subCategory {
        private ArrayList<String> subcategories;

        public subCategory(ArrayList<String> subcategories) {
            this.subcategories = subcategories;
        }

        public ArrayList<String> getSubCategories(){
            return subcategories;
        }

}
