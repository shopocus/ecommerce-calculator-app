package com.ecommerce.calculator.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
        return sharedPreferences.getString("token", null) != null;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("mobile_no", null)
        );
    }

    public void saveToken(String token) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token", token);

        editor.apply();

    }

    public String getToken() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        return token;
    }

//    public void saveData(TitleDataResponse dataResponse) {
//
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//            editor.putString("title", dataResponse.getTitle());
//            editor.putString("category", dataResponse.getCategory());
//            editor.putString("sellingPrice", dataResponse.getSellingPrice());
//            editor.putString("gstOnProduct", dataResponse.getGstOnProduct());
//            editor.putString("productPriceWithoutGst", dataResponse.getProductPriceWithoutGst());
//            editor.putString("inwardShipping", dataResponse.getInwardShipping());
//            editor.putString("packagingExpense", dataResponse.getPackagingExpense());
//            editor.putString("labour", dataResponse.getLabour());
//            editor.putString("storageFee", dataResponse.getStorageFee());
//            editor.putString("other", dataResponse.getOther());
//            editor.putString("discountAmount", dataResponse.getDiscountAmount());
//            editor.putString("discountPercent", dataResponse.getDiscountPercent());
//            editor.putString("bankSettlement", dataResponse.getBankSettlement());
//            editor.putString("totalMeeshoCommision", dataResponse.getTotalMeeshoCommision());
//            editor.putString("profit", dataResponse.getProfit());
//            editor.putString("totalGstPayable", dataResponse.getTotalGstPayable());
//            editor.putString("tcs", dataResponse.getTcs());
//            editor.putString("gstPayable", dataResponse.getGstPayable());
//            editor.putString("gstClaim", dataResponse.getGstClaim());
//            editor.putString("profitPercentage", dataResponse.getProfitPercentage());
//
//        editor.apply();
//    }

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

    public void saveList(ArrayList<String> list) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("list", json);

        editor.apply();
    }

    public ArrayList<String> getList(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list = gson.fromJson(json, type);
        return list;
    }

    public void saveTitle(String title){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("title", title);

        editor.apply();
    }

    public String getTitle(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String title = (sharedPreferences.getString("title",null));
        return title;
    }

    public void saveCompany(String company){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("company", company);

        editor.apply();
    }

    public String getCompany(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String company = (sharedPreferences.getString("company",null));
        return company;
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}