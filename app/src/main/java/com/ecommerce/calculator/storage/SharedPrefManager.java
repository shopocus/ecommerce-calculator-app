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

    public CalculateResponse getResult() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new CalculateResponse(
                sharedPreferences.getFloat("bankSettlement", 0),
                sharedPreferences.getFloat("totalMeeshoCommision", 0),
                sharedPreferences.getFloat("profit", 0),
                sharedPreferences.getFloat("totalGstPayable", 0),
                sharedPreferences.getFloat("tcs", 0),
                sharedPreferences.getFloat("gstPayable", 0),
                sharedPreferences.getFloat("gstClaim", 0),
                sharedPreferences.getFloat("profitPercentage", 0)
        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
