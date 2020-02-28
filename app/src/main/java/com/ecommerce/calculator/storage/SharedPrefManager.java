package com.ecommerce.calculator.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.ecommerce.calculator.models.CalculateResponse;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.models.User;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }


    public void saveUser(User user) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("mobile_no", user.getMobile_no());

        editor.apply();

    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("email", null) != null;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("mobile_no", null)
        );
    }

    public void saveData(TitleDataResponse dataResponse) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("gstOnProduct", dataResponse.getGstOnProduct());
        editor.putString("productPriceWithoutGst", dataResponse.getProductPriceWithoutGst());
        editor.putString("inwardShipping", dataResponse.getInwardShipping());
        editor.putString("packagingExpense", dataResponse.getPackagingExpense());
        editor.putString("labour", dataResponse.getLabour());
        editor.putString("storageFee", dataResponse.getStorageFee());
        editor.putString("other", dataResponse.getOther());
        editor.putString("discountAmount", dataResponse.getDiscountAmount());
        editor.putString("discountPercent", dataResponse.getDiscountPercent());
        editor.putString("bankSettlement", dataResponse.getBankSettlement());
        editor.putString("totalMeeshoCommision", dataResponse.getTotalMeeshoCommision());
        editor.putString("profit", dataResponse.getProfit());
        editor.putString("totalGstPayable", dataResponse.getTotalGstPayable());
        editor.putString("tcs", dataResponse.getTcs());
        editor.putString("gstPayable", dataResponse.getGstPayable());
        editor.putString("gstClaim", dataResponse.getGstClaim());
        editor.putString("profitPercentage", dataResponse.getProfitPercentage());

        editor.apply();

    }

    public TitleDataResponse getData() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new TitleDataResponse(
                sharedPreferences.getString("gstOnProduct", null),
        sharedPreferences.getString("productPriceWithoutGst", null),
        sharedPreferences.getString("inwardShipping", null),
        sharedPreferences.getString("packagingExpense", null),
        sharedPreferences.getString("labour", null),
        sharedPreferences.getString("storageFee", null),
        sharedPreferences.getString("other", null),
        sharedPreferences.getString("discountAmount", null),
        sharedPreferences.getString("discountPercent", null),
        sharedPreferences.getString("bankSettlement", null),
        sharedPreferences.getString("totalMeeshoCommision", null),
        sharedPreferences.getString("profit", null),
        sharedPreferences.getString("totalGstPayable", null),
        sharedPreferences.getString("tcs", null),
        sharedPreferences.getString("gstPayable", null),
        sharedPreferences.getString("gstClaim", null),
        sharedPreferences.getString("profitPercentage", null)
        );
    }

    public void saveFlag(String flag){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("flag", flag);

        editor.apply();
    }

    public String getFlag(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String flag = (sharedPreferences.getString("flag",null));
        return flag;
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
