package com.ecommerce.calculator.api;

import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.models.LoginResponse;
import com.ecommerce.calculator.models.CalculateResponse;
import com.ecommerce.calculator.models.SaveResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("signup")
    Call<DefaultResponse> signup(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile_no") String mobile_no,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("calculate")
    Call<CalculateResponse> calculate(
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

    @FormUrlEncoded
    @POST("meesho/tosave")
    Call<SaveResponse> saved(
      @Field("email") String email,
      @Field("title") String title,
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
      @Field("bankSettlement") Double bankSettlement,
      @Field("totalMeeshoCommision") Double totalMeeshoCommision,
      @Field("profit") Double profit,
      @Field("totalGstPayable") Double totalGstPayable,
      @Field("tcs") Double tcs,
      @Field("gstPayable") Double gstPayable,
      @Field("gstClaim") Double gstClaim,
      @Field("profitPercentage") Double profitPercentage
    );

    /*@FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Field("email") String email,
            @Field("name") String name,
            @Field("mobile") String mobile_no
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );*/

}
