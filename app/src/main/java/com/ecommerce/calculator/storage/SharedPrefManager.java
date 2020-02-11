package com.ecommerce.calculator.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.ecommerce.calculator.models.CalculateResponse;
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
        editor.putString("mobile", user.getMobile_no());

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

//    public void saveResult(String text1, String text2, String text3, String text4, String text5, String text6, String text7, String text8){
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("bankSettlement", text1);
//        editor.putString("totalMeeshoCommision", text2);
//        editor.putString("profit", text3);
//        editor.putString("totalGstPayable", text4);
//        editor.putString("tcs", text5);
//        editor.putString("gstPayable", text6);
//        editor.putString("gstClaim", text7);
//        editor.putString("profitPercentage", text8);
//
//        editor.apply();
//    }
//
//    public CalculateResponse getResult() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return new CalculateResponse(
//                sharedPreferences.getFloat("bankSettlement", 0),
//                sharedPreferences.getFloat("totalMeeshoCommision", 0),
//                sharedPreferences.getFloat("profit", 0),
//                sharedPreferences.getFloat("totalGstPayable", 0),
//                sharedPreferences.getFloat("tcs", 0),
//                sharedPreferences.getFloat("gstPayable", 0),
//                sharedPreferences.getFloat("gstClaim", 0),
//                sharedPreferences.getFloat("profitPercentage", 0)
//        );
//    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
