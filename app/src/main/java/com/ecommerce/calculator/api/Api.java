package com.ecommerce.calculator.api;

import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.models.LoginResponse;
import com.ecommerce.calculator.models.CalculateResponse;
import com.ecommerce.calculator.models.SaveResponse;
import com.ecommerce.calculator.models.category;
import com.ecommerce.calculator.models.savedTitleResponse;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.models.MessageResponse;
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
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("meesho/app/calculate")
    Call<CalculateResponse> calculate(
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
            @Field("discountAmount") double discountAmount
    );

    @GET("meesho/app/showCategory")
    Call<category> getCategories( );

    @FormUrlEncoded
    @POST("meesho/app/toSave")
    Call<SaveResponse> saved(
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