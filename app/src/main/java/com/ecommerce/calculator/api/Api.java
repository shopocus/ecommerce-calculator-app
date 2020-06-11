package com.ecommerce.calculator.api;

import com.ecommerce.calculator.models.AmazonCalculationResponse;
import com.ecommerce.calculator.models.ClubFactoryCalculationResponse;
import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.models.FlipkartCalculationResponse;
import com.ecommerce.calculator.models.LoginResponse;
import com.ecommerce.calculator.models.MeeshoCalculationResponse;
import com.ecommerce.calculator.models.category;
import com.ecommerce.calculator.models.savedTitleResponse;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.models.subCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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
    Call<MeeshoCalculationResponse> calculate(
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
    Call<MessageResponse> saved(
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
            @Field("totalCommision") String totalCommision,
            @Field("profit") String profit,
            @Field("totalGstPayable") String totalGstPayable,
            @Field("tcs") String tcs,
            @Field("gstPayable") String gstPayable,
            @Field("gstClaim") String gstClaim,
            @Field("profitPercentage") String profitPercentage
    );

    @FormUrlEncoded
    @POST("clubFactory/app/calculate")
    Call<ClubFactoryCalculationResponse> clubFactoyrCalculation(
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
            @Field("courier") String courier,
            @Field("bankSettlementLocal") String bankSettlementLocal,
            @Field("totalCommisionLocal") String totalCommisionLocal,
            @Field("totalGstPayableLocal") String totalGstPayableLocal,
            @Field("tcsLocal") String tcsLocal,
            @Field("gstPayableLocal") String gstPayableLocal,
            @Field("gstClaimLocal") String gstClaimLocal,
            @Field("profitLocal") String profitLocal,
            @Field("profitPercentageLocal") String profitPercentageLocal,
            @Field("bankSettlementRegional") String bankSettlementRegional,
            @Field("totalCommisionRegional") String totalCommisionRegional,
            @Field("totalGstPayableRegional") String totalGstPayableRegional,
            @Field("tcsRegional") String tcsRegional,
            @Field("gstPayableRegional") String gstPayableRegional,
            @Field("gstClaimRegional") String gstClaimRegional,
            @Field("profitRegional") String profitRegional,
            @Field("profitPercentageRegional") String profitPercentageRegional,
            @Field("bankSettlementMetro") String bankSettlementMetro,
            @Field("totalCommisionMetro") String totalCommisionMetro,
            @Field("totalGstPayableMetro") String totalGstPayableMetro,
            @Field("tcsMetro") String tcsMetro,
            @Field("gstPayableMetro") String gstPayableMetro,
            @Field("gstClaimMetro") String gstClaimMetro,
            @Field("profitMetro") String profitMetro,
            @Field("profitPercentageMetro") String profitPercentageMetro,
            @Field("bankSettlementRestOfIndia") String bankSettlementRestOfIndia,
            @Field("totalCommisionRestOfIndia") String totalCommisionRestOfIndia,
            @Field("totalGstPayableRestOfIndia") String totalGstPayableRestOfIndia,
            @Field("tcsRestOfIndia") String tcsRestOfIndia,
            @Field("gstPayableRestOfIndia") String gstPayableRestOfIndia,
            @Field("gstClaimRestOfIndia") String gstClaimRestOfIndia,
            @Field("profitRestOfIndia") String profitRestOfIndia,
            @Field("profitPercentageRestOfIndia") String profitPercentageRestOfIndia,
            @Field("bankSettlementKerela") String bankSettlementKerela,
            @Field("totalCommisionKerela") String totalCommisionKerela,
            @Field("totalGstPayableKerela") String totalGstPayableKerela,
            @Field("tcsKerela") String tcsKerela,
            @Field("gstPayableKerela") String gstPayableKerela,
            @Field("gstClaimKerela") String gstClaimKerela,
            @Field("profitKerela") String profitKerela,
            @Field("profitPercentageKerela") String profitPercentageKerala
    );

    @FormUrlEncoded
    @POST("flipkart/app/calculate")
    Call<FlipkartCalculationResponse> flipkartCalculation(
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
    Call<category> getCategoriesFlipkart( );

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
            @Field("customerType") String customerType,
            @Field("commissionFeesLocal") String commissionFeesLocal,
            @Field("fixedFeesLocal") String fixedFeesLocal,
            @Field("collectionFeesLocal") String collectionFeesLocal,
            @Field("shippingFeesLocal") String shippingFeesLocal,
            @Field("CFCSLocal") String CFCSLocal,
            @Field("gstOnCFCSLocal") String gstOnCFCSLocal,
            @Field("totalChargesLocal") String totalChargesLocal,
            @Field("bankSettlementLocal") String bankSettlementLocal,
            @Field("totalGstPayableLocal") String totalGstPayableLocal,
            @Field("tcsLocal") String tcsLocal,
            @Field("gstPayableLocal") String gstPayableLocal,
            @Field("profitLocal") String profitLocal,
            @Field("profitPercentageLocal") String profitPercentageLocal,
            @Field("commissionFeesZonal") String commissionFeesZonal,
            @Field("fixedFeesZonal") String fixedFeesZonal,
            @Field("collectionFeesZonal") String collectionFeesZonal,
            @Field("shippingFeesZonal") String shippingFeesZonal,
            @Field("CFCSZonal") String CFCSZonal,
            @Field("gstOnCFCSZonal") String gstOnCFCSZonal,
            @Field("totalChargesZonal") String totalChargesZonal,
            @Field("bankSettlementZonal") String bankSettlementZonal,
            @Field("totalGstPayableZonal") String totalGstPayableZonal,
            @Field("tcsZonal") String tcsZonal,
            @Field("gstPayableZonal") String gstPayableZonal,
            @Field("profitZonal") String profitZonal,
            @Field("profitPercentageZonal") String profitPercentageZonal,
            @Field("commissionFeesNational") String commissionFeesNational,
            @Field("fixedFeesNational") String fixedFeesNational,
            @Field("collectionFeesNational") String collectionFeesNational,
            @Field("shippingFeesNational") String shippingFeesNational,
            @Field("CFCSNational") String CFCSNational,
            @Field("gstOnCFCSNational") String gstOnCFCSNational,
            @Field("totalChargesNational") String totalChargesNational,
            @Field("bankSettlementNational") String bankSettlementNational,
            @Field("totalGstPayableNational") String totalGstPayableNational,
            @Field("tcsNational") String tcsNational,
            @Field("gstPayableNational") String gstPayableNational,
            @Field("profitNational") String profitNational,
            @Field("profitPercentageNational") String profitPercentageNational
    );

    @FormUrlEncoded
    @POST("amazon/app/calculate")
    Call<AmazonCalculationResponse> amazonCalculation(
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
    Call<category> getCategoriesAmazon( );

    @FormUrlEncoded
    @POST("amazon/app/showSubCategory")
    Call<subCategory> getSubCategoriesAmazon(
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
            @Field("selfShipNational") String selfShipNational,
            @Field("referralFeesLocal") String referralFeesLocal,
            @Field("closingFeesLocal") String closingFeesLocal,
            @Field("shippingFeesLocal") String shippingFeesLocal,
            @Field("RCSLocal") String RCSLocal,
            @Field("gstOnRCSLocal") String gstOnRCSLocal,
            @Field("totalChargesLocal") String totalChargesLocal,
            @Field("gstClaimLocal") String gstClaimLocal,
            @Field("bankSettlementLocal") String bankSettlementLocal,
            @Field("totalGstPayableLocal") String totalGstPayableLocal,
            @Field("tcsLocal") String tcsLocal,
            @Field("gstPayableLocal") String gstPayableLocal,
            @Field("profitLocal") String profitLocal,
            @Field("profitPercentageLocal") String profitPercentageLocal,
            @Field("referralFeesRegional") String referralFeesRegional,
            @Field("closingFeesRegional") String closingFeesRegional,
            @Field("shippingFeesRegional") String shippingFeesRegional,
            @Field("RCSRegional") String RCSRegional,
            @Field("gstOnRCSRegional") String gstOnRCSRegional,
            @Field("totalChargesRegional") String totalChargesRegional,
            @Field("gstClaimRegional") String gstClaimRegional,
            @Field("bankSettlementRegional") String bankSettlementRegional,
            @Field("totalGstPayableRegional") String totalGstPayableRegional,
            @Field("tcsRegional") String tcsRegional,
            @Field("gstPayableRegional") String gstPayableRegional,
            @Field("profitRegional") String profitRegional,
            @Field("profitPercentageRegional") String profitPercentageRegional,
            @Field("referralFeesNational") String referralFeesNational,
            @Field("closingFeesNational") String closingFeesNational,
            @Field("shippingFeesNational") String shippingFeesNational,
            @Field("RCSNational") String RCSNational,
            @Field("gstOnRCSNational") String gstOnRCSNational,
            @Field("totalChargesNational") String totalChargesNational,
            @Field("gstClaimNational") String gstClaimNational,
            @Field("bankSettlementNational") String bankSettlementNational,
            @Field("totalGstPayableNational") String totalGstPayableNational,
            @Field("tcsNational") String tcsNational,
            @Field("gstPayableNational") String gstPayableNational,
            @Field("profitNational") String profitNational,
            @Field("profitPercentageNational") String profitPercentageNational
    );

    @FormUrlEncoded
    @POST("amazon/app/fba/calculate")
    Call<AmazonCalculationResponse> amazonFbaCalculation(
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
    @POST("amazon/app/fba/toSave")
    Call<AmazonCalculationResponse> savedAmazonFba(
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
            @Field("height") String height,
            @Field("referralFeesLocal") String referralFeesLocal,
            @Field("closingFeesLocal") String closingFeesLocal,
            @Field("shippingFeesLocal") String shippingFeesLocal,
            @Field("RCFLocal") String RCFLocal,
            @Field("gstOnRCFLocal") String gstOnRCFLocal,
            @Field("totalChargesLocal") String totalChargesLocal,
            @Field("gstClaimLocal") String gstClaimLocal,
            @Field("bankSettlementLocal") String bankSettlementLocal,
            @Field("totalGstPayableLocal") String totalGstPayableLocal,
            @Field("tcsLocal") String tcsLocal,
            @Field("gstPayableLocal") String gstPayableLocal,
            @Field("profitLocal") String profitLocal,
            @Field("profitPercentageLocal") String profitPercentageLocal,
            @Field("referralFeesRegional") String referralFeesRegional,
            @Field("closingFeesRegional") String closingFeesRegional,
            @Field("shippingFeesRegional") String shippingFeesRegional,
            @Field("RCFRegional") String RCSRegional,
            @Field("gstOnRCFRegional") String gstOnRCSRegional,
            @Field("totalChargesRegional") String totalChargesRegional,
            @Field("gstClaimRegional") String gstClaimRegional,
            @Field("bankSettlementRegional") String bankSettlementRegional,
            @Field("totalGstPayableRegional") String totalGstPayableRegional,
            @Field("tcsRegional") String tcsRegional,
            @Field("gstPayableRegional") String gstPayableRegional,
            @Field("profitRegional") String profitRegional,
            @Field("profitPercentageRegional") String profitPercentageRegional,
            @Field("referralFeesNational") String referralFeesNational,
            @Field("closingFeesNational") String closingFeesNational,
            @Field("shippingFeesNational") String shippingFeesNational,
            @Field("RCFNational") String RCSNational,
            @Field("gstOnRCFNational") String gstOnRCSNational,
            @Field("totalChargesNational") String totalChargesNational,
            @Field("gstClaimNational") String gstClaimNational,
            @Field("bankSettlementNational") String bankSettlementNational,
            @Field("totalGstPayableNational") String totalGstPayableNational,
            @Field("tcsNational") String tcsNational,
            @Field("gstPayableNational") String gstPayableNational,
            @Field("profitNational") String profitNational,
            @Field("profitPercentageNational") String profitPercentageNational
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