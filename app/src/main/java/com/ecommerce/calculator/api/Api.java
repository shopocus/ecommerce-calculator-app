package com.ecommerce.calculator.api;

import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.models.LoginResponse;
import com.ecommerce.calculator.models.CalculateResponse;
import com.ecommerce.calculator.models.SaveResponse;
import com.ecommerce.calculator.models.savedTitleResponse;
import com.ecommerce.calculator.models.TitleDataResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
      @Field("bankSettlement") String bankSettlement,
      @Field("totalMeeshoCommision") String totalMeeshoCommision,
      @Field("profit") String profit,
      @Field("totalGstPayable") String totalGstPayable,
      @Field("tcs") String tcs,
      @Field("gstPayable") String gstPayable,
      @Field("gstClaim") String gstClaim,
      @Field("profitPercentage") String profitPercentage
    );

    @FormUrlEncoded
    @POST("meesho/saved/title")
    Call<savedTitleResponse> getTitles(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("meesho/saved/title/data")
    Call<TitleDataResponse> getData(
            @Field("email") String email,
            @Field("title") String title
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
