package com.ecommerce.calculator.api;

import com.ecommerce.calculator.models.CommonOutputModel;
import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.models.LoginResponse;
import com.ecommerce.calculator.models.category;
import com.ecommerce.calculator.models.savedTitleResponse;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.models.subCategory;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

    @FormUrlEncoded
    @POST("signup")
    Call<DefaultResponse> signup(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile_no") String mobile_no,
            @Field("password") String password,
            @Field("role") String role
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("meesho/app/calculate")
    Call<CommonOutputModel> calculate(
            @Field("category") String category,
            @Field("subcategory") String subcategory,
            @Field("sellingPrice") double sellingPrice,
            @Field("gstOnProduct") double gstOnProduct,
            @Field("productPriceWithoutGst") double productPriceWithoutGst,
            @Field("inwardShipping") double inwardShipping,
            @Field("packagingExpense") double packagingExpense,
            @Field("labour") double labour,
            @Field("storageFee") double storageFee,
            @Field("other") double other,
            @Field("discountPercent") double discountPercent,
            @Field("discountAmount") double discountAmount
    );

    @GET("meesho/app/showCategory")
    Call<category> getCategories();

    @FormUrlEncoded
    @POST("meesho/app/showSubCategory")
    Call<subCategory> getSubCategoriesMeesho(
            @Field("category") String category
    );

    @FormUrlEncoded
    @POST("meesho/app/toSave")
    Call<MessageResponse> saved(
            @Field("title") String title,
            @Field("category") String category,
            @Field("subcategory") String subcategory,
            @Field("sellingPrice") String sellingPrice,
            @Field("gstOnProduct") String gstOnProduct,
            @Field("productPriceWithoutGst") String productPriceWithoutGst,
            @Field("inwardShipping") String inwardShipping,
            @Field("packagingExpense") String packagingExpense,
            @Field("labour") String labour,
            @Field("storageFee") String storageFee,
            @Field("other") String other,
            @Field("discountPercent") String discountPercent,
            @Field("discountAmount") String discountAmount
    );

    @FormUrlEncoded
    @POST("clubFactory/app/calculate")
    Call<CommonOutputModel> clubFactoyrCalculation(
            @Field("category") String category,
            @Field("sellingPrice") double sellingPrice,
            @Field("gstOnProduct") double gstOnProduct,
            @Field("productPriceWithoutGst") double productPriceWithoutGst,
            @Field("inwardShipping") double inwardShipping,
            @Field("packagingExpense") double packagingExpense,
            @Field("labour") double labour,
            @Field("storageFee") double storageFee,
            @Field("other") double other,
            @Field("discountPercent") double discountPercent,
            @Field("discountAmount") double discountAmount,
            @Field("weight") double weight,
            @Field("payMode") String payMode,
            @Field("courier") String courier
    );

    @GET("clubFactory/app/showCategory")
    Call<category> getCategoriesClubFactory();

    @FormUrlEncoded
    @POST("clubFactory/app/toSave")
    Call<MessageResponse> savedclubFactory(
            @Field("title") String title,
            @Field("category") String category,
            @Field("sellingPrice") String sellingPrice,
            @Field("gstOnProduct") String gstOnProduct,
            @Field("productPriceWithoutGst") String productPriceWithoutGst,
            @Field("inwardShipping") String inwardShipping,
            @Field("packagingExpense") String packagingExpense,
            @Field("labour") String labour,
            @Field("storageFee") String storageFee,
            @Field("other") String other,
            @Field("discountPercent") String discountPercent,
            @Field("discountAmount") String discountAmount,
            @Field("weight") String weight,
            @Field("payMode") String payMode,
            @Field("courier") String courier
    );

    @FormUrlEncoded
    @POST("flipkart/app/calculate")
    Call<CommonOutputModel> flipkartCalculation(
            @Field("category") String category,
            @Field("sellingPrice") double sellingPrice,
            @Field("gstOnProduct") double gstOnProduct,
            @Field("productPriceWithoutGst") double productPriceWithoutGst,
            @Field("inwardShipping") double inwardShipping,
            @Field("packagingExpense") double packagingExpense,
            @Field("labour") double labour,
            @Field("storageFee") double storageFee,
            @Field("other") double other,
            @Field("discountPercent") double discountPercent,
            @Field("discountAmount") double discountAmount,
            @Field("weight") double weight,
            @Field("length") double length,
            @Field("breadth") double breadth,
            @Field("height") double height,
            @Field("payMode") String payMode,
            @Field("customerType") String customerType
    );

    @GET("flipkart/app/showCategory")
    Call<category> getCategoriesFlipkart();

    @FormUrlEncoded
    @POST("flipkart/app/toSave")
    Call<MessageResponse> savedFlipkart(
            @Field("title") String title,
            @Field("category") String category,
            @Field("sellingPrice") String sellingPrice,
            @Field("gstOnProduct") String gstOnProduct,
            @Field("productPriceWithoutGst") String productPriceWithoutGst,
            @Field("inwardShipping") String inwardShipping,
            @Field("packagingExpense") String packagingExpense,
            @Field("labour") String labour,
            @Field("storageFee") String storageFee,
            @Field("other") String other,
            @Field("discountPercent") String discountPercent,
            @Field("discountAmount") String discountAmount,
            @Field("weight") String weight,
            @Field("length") String length,
            @Field("breadth") String breadth,
            @Field("height") String height,
            @Field("payMode") String payMode,
            @Field("customerType") String customerType
    );

    @FormUrlEncoded
    @POST("amazon/app/calculate")
    Call<CommonOutputModel> amazonCalculation(
            @Field("category") String category,
            @Field("subcategory") String subcategory,
            @Field("sellingPrice") double sellingPrice,
            @Field("gstOnProduct") double gstOnProduct,
            @Field("productPriceWithoutGst") double productPriceWithoutGst,
            @Field("inwardShipping") double inwardShipping,
            @Field("packagingExpense") double packagingExpense,
            @Field("labour") double labour,
            @Field("storageFee") double storageFee,
            @Field("other") double other,
            @Field("discountPercent") double discountPercent,
            @Field("discountAmount") double discountAmount,
            @Field("weight") double weight,
            @Field("length") double length,
            @Field("breadth") double breadth,
            @Field("height") double height,
            @Field("shipmentType") String shipmentType,
            @Field("easyShipType") String easyShipType,
            @Field("selfShipLocal") double selfShipLocal,
            @Field("selfShipRegional") double selfShipRegional,
            @Field("selfShipNational") double selfShipNational
    );

    @GET("amazon/app/showCategory")
    Call<category> getCategoriesAmazon();

    @FormUrlEncoded
    @POST("amazon/app/showSubCategory")
    Call<subCategory> getSubCategoriesAmazon(
            @Field("category") String category
    );

    @GET("amazonFba/app/showCategory")
    Call<category> getCategoriesAmazonFba();

    @FormUrlEncoded
    @POST("amazonFba/app/showSubCategory")
    Call<subCategory> getSubCategoriesAmazonFba(
            @Field("category") String category
    );

    @FormUrlEncoded
    @POST("amazon/app/toSave")
    Call<MessageResponse> savedAmazon(
            @Field("title") String title,
            @Field("category") String category,
            @Field("subcategory") String subcategory,
            @Field("sellingPrice") String sellingPrice,
            @Field("gstOnProduct") String gstOnProduct,
            @Field("productPriceWithoutGst") String productPriceWithoutGst,
            @Field("inwardShipping") String inwardShipping,
            @Field("packagingExpense") String packagingExpense,
            @Field("labour") String labour,
            @Field("storageFee") String storageFee,
            @Field("other") String other,
            @Field("discountPercent") String discountPercent,
            @Field("discountAmount") String discountAmount,
            @Field("weight") String weight,
            @Field("length") String length,
            @Field("breadth") String breadth,
            @Field("height") String height,
            @Field("shipmentType") String shipmentType,
            @Field("easyShipType") String easyShipType,
            @Field("selfShipLocal") String selfShipLocal,
            @Field("selfShipRegional") String selfShipRegional,
            @Field("selfShipNational") String selfShipNational
    );

    @FormUrlEncoded
    @POST("amazonFba/app/calculate")
    Call<CommonOutputModel> amazonFbaCalculation(
            @Field("category") String category,
            @Field("subcategory") String subcategory,
            @Field("sellingPrice") double sellingPrice,
            @Field("gstOnProduct") double gstOnProduct,
            @Field("productPriceWithoutGst") double productPriceWithoutGst,
            @Field("inwardShipping") double inwardShipping,
            @Field("other") double other,
            @Field("discountPercent") double discountPercent,
            @Field("discountAmount") double discountAmount,
            @Field("weight") double weight,
            @Field("length") double length,
            @Field("breadth") double breadth,
            @Field("height") double height
    );

    @FormUrlEncoded
    @POST("amazonFba/app/toSave")
    Call<MessageResponse> savedAmazonFba(
            @Field("title") String title,
            @Field("category") String category,
            @Field("subcategory") String subcategory,
            @Field("sellingPrice") String sellingPrice,
            @Field("gstOnProduct") String gstOnProduct,
            @Field("productPriceWithoutGst") String productPriceWithoutGst,
            @Field("inwardShipping") String inwardShipping,
            @Field("other") String other,
            @Field("discountPercent") String discountPercent,
            @Field("discountAmount") String discountAmount,
            @Field("weight") String weight,
            @Field("length") String length,
            @Field("breadth") String breadth,
            @Field("height") String height
    );

    @FormUrlEncoded
    @POST("ebay/app/calculate")
    Call<CommonOutputModel> ebayCalculation(
            @Field("sellingPrice") double sellingPrice,
            @Field("gstOnProduct") double gstOnProduct,
            @Field("productPriceWithoutGst") double productPriceWithoutGst,
            @Field("inwardShipping") double inwardShipping,
            @Field("packagingExpense") double packagingExpense,
            @Field("labour") double labour,
            @Field("storageFee") double storageFee,
            @Field("other") double other,
            @Field("discountPercent") double discountPercent,
            @Field("discountAmount") double discountAmount,
            @Field("commission") double commission,
            @Field("shipping") double shipping,
            @Field("paymentGatewayCharge") double paymentGatewayCharge
    );

    @FormUrlEncoded
    @POST("ebay/app/toSave")
    Call<MessageResponse> savedEbay(
            @Field("title") String title,
            @Field("sellingPrice") String sellingPrice,
            @Field("gstOnProduct") String gstOnProduct,
            @Field("productPriceWithoutGst") String productPriceWithoutGst,
            @Field("inwardShipping") String inwardShipping,
            @Field("packagingExpense") String packagingExpense,
            @Field("labour") String labour,
            @Field("storageFee") String storageFee,
            @Field("other") String other,
            @Field("discountAmount") String discountAmount,
            @Field("discountPercent") String discountPercent,
            @Field("commission") String commission,
            @Field("shipping") String shipping,
            @Field("paymentGatewayCharge") String paymentGatewayCharge
    );

    @FormUrlEncoded
    @POST("other/app/calculate")
    Call<CommonOutputModel> otherCalculation(
            @Field("sellingPrice") double sellingPrice,
            @Field("gstOnProduct") double gstOnProduct,
            @Field("productPriceWithoutGst") double productPriceWithoutGst,
            @Field("inwardShipping") double inwardShipping,
            @Field("packagingExpense") double packagingExpense,
            @Field("labour") double labour,
            @Field("storageFee") double storageFee,
            @Field("other") double other,
            @Field("discountAmount") double discountAmount,
            @Field("discountPercent") double discountPercent,
            @Field("marketingFees") double marketingFees,
            @Field("shipping") double shipping,
            @Field("paymentGatewayCharge") double paymentGatewayCharge
    );

    @FormUrlEncoded
    @POST("other/app/toSave")
    Call<MessageResponse> savedOther(
            @Field("title") String title,
            @Field("sellingPrice") String sellingPrice,
            @Field("gstOnProduct") String gstOnProduct,
            @Field("productPriceWithoutGst") String productPriceWithoutGst,
            @Field("inwardShipping") String inwardShipping,
            @Field("packagingExpense") String packagingExpense,
            @Field("labour") String labour,
            @Field("storageFee") String storageFee,
            @Field("other") String other,
            @Field("discountAmount") String discountAmount,
            @Field("discountPercent") String discountPercent,
            @Field("marketingFees") String marketingFees,
            @Field("shipping") String shipping,
            @Field("paymentGatewayCharge") String paymentGatewayCharge
    );

    @FormUrlEncoded
    @POST("common/show/saved/title")
    Call<savedTitleResponse> getTitles(
            @Field("company") String company
    );

    @FormUrlEncoded
    @POST("common/show/saved/title/data")
    Call<TitleDataResponse> getData(
            @Field("company") String company,
            @Field("title") String title
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "common/delete/title", hasBody = true)
    Call<MessageResponse> DeleteData(@Field("company") String company, @Field("title") String title);

    @FormUrlEncoded
    @PUT("common/update/title")
    Call<MessageResponse> update(
            @Field("company") String company,
            @Field("title") String title,
            @Field("newTitle") String newTitle
    );

    @FormUrlEncoded
    @POST("preRegister/sendOtp")
    Call<MessageResponse> otpSend(
            @Field("email") String email,
            @Field("mobile_no") String mobile_no
    );

    @FormUrlEncoded
    @POST("preRegister/verifyOtp")
    Call<MessageResponse> otpVerification(
            @Field("email") String email,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("forgetPassword/sendOtp")
    Call<MessageResponse> forgetPasswordOtpSend(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("forgetPassword/verifyOtp")
    Call<MessageResponse> forgetPasswordOtpVerification(
            @Field("email") String email,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @PUT("forgetPassword/changePassword")
    Call<MessageResponse> updatePassword(
            @Field("email") String email,
            @Field("newPassword") String newPassword
    );

    @HTTP(method = "DELETE", path = "logout", hasBody = true)
    Call<MessageResponse> logout();

}