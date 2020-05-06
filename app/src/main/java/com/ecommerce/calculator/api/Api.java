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
    @POST("clubFactory/app/calculate")
    Call<CalculateResponse> clubFactoyrCalculation(
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
    Call<category> getCategoriesClubFactory( );

    @FormUrlEncoded
    @POST("clubFactory/app/toSave")
    Call<SaveResponse> savedclubFactory(
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
            @Field("courier") String courier,
            @Field("bankSettlementLocal") String bankSettlementLocal,
            @Field("totalMeeshoCommisionLocal") String totalMeeshoCommisionLocal,
            @Field("totalGstPayableLocal") String totalGstPayableLocal,
            @Field("tcsLocal") String tcsLocal,
            @Field("gstPayableLocal") String gstPayableLocal,
            @Field("gstClaimLocal") String gstClaimLocal,
            @Field("profitLocal") String profitLocal,
            @Field("profitPercentageLocal") String profitPercentageLocal,
            @Field("bankSettlementRegional") String bankSettlementRegional,
            @Field("totalCfCommisionRegional") String totalCfCommisionRegional,
            @Field("totalGstPayableRegional") String totalGstPayableRegional,
            @Field("tcsRegional") String tcsRegional,
            @Field("gstPayableRegional") String gstPayableRegional,
            @Field("gstClaimRegional") String gstClaimRegional,
            @Field("profitRegional") String profitRegional,
            @Field("profitPercentageRegional") String profitPercentageRegional,
            @Field("bankSettlementMetro") String bankSettlementMetro,
            @Field("totalCfCommisionMetro") String totalMeeshoCommisionMetro,
            @Field("totalGstPayableMetro") String totalGstPayableMetro,
            @Field("tcsMetro") String tcsMetro,
            @Field("gstPayableMetro") String gstPayableMetro,
            @Field("gstClaimMetro") String gstClaimMetro,
            @Field("profitMetro") String profitMetro,
            @Field("profitPercentageMetro") String profitPercentageMetro,
            @Field("bankSettlementRestOfIndia") String bankSettlementRestOfIndia,
            @Field("totalCfCommisionRestOfIndia") String totalMeeshoCommisionRestOfIndia,
            @Field("totalGstPayableRestOfIndia") String totalGstPayableRestOfIndia,
            @Field("tcsRestOfIndia") String tcsRestOfIndia,
            @Field("gstPayableRestOfIndia") String gstPayableRestOfIndia,
            @Field("gstClaimRestOfIndia") String gstClaimRestOfIndia,
            @Field("profitRestOfIndia") String profitRestOfIndia,
            @Field("profitPercentageRestOfIndia") String profitPercentageRestOfIndia,
            @Field("bankSettlementKerela") String bankSettlementKerela,
            @Field("totalMeeshoCommisionKerela") String totalMeeshoCommisionKerela,
            @Field("totalGstPayableKerela") String totalGstPayableKerela,
            @Field("tcsKerela") String tcsKerela,
            @Field("gstPayableKerela") String gstPayableKerela,
            @Field("gstClaimKerela") String gstClaimKerela,
            @Field("profitKerela") String profitKerela,
            @Field("profitPercentageKerela") String profitPercentageKerala
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