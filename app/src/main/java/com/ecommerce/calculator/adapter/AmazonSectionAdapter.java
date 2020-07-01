package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecommerce.calculator.fragments.AmazonFbaLocal;
import com.ecommerce.calculator.fragments.AmazonFbaNational;
import com.ecommerce.calculator.fragments.AmazonFbaRegional;
import com.ecommerce.calculator.fragments.AmazonLocal;
import com.ecommerce.calculator.fragments.AmazonNational;
import com.ecommerce.calculator.fragments.AmazonRegional;
import com.ecommerce.calculator.storage.SharedPrefManager;

public class AmazonSectionAdapter extends FragmentPagerAdapter {

    int counttab;
    Context c;
    Bundle bundle;

    public AmazonSectionAdapter(Context c, @NonNull FragmentManager fm, int counttab, Bundle bundle) {
        super(fm);
        this.c = c;
        this.counttab = counttab;
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if(SharedPrefManager.getInstance(c).getCompany().equals("amazon")) {
                    AmazonLocal amazonLocal = new AmazonLocal();
                    amazonLocal.setArguments(bundle);
                    return amazonLocal;
                }else{
                    AmazonFbaLocal amazonFbaLocal = new AmazonFbaLocal();
                    amazonFbaLocal.setArguments(bundle);
                    return amazonFbaLocal;
                }
            case 1:
                if(SharedPrefManager.getInstance(c).getCompany().equals("amazon")) {
                    AmazonRegional amazonRegional = new AmazonRegional();
                    amazonRegional.setArguments(bundle);
                    return amazonRegional;
                }else{
                    AmazonFbaRegional amazonFbaRegional = new AmazonFbaRegional();
                    amazonFbaRegional.setArguments(bundle);
                    return amazonFbaRegional;
                }
            case 2:
                if(SharedPrefManager.getInstance(c).getCompany().equals("amazon")) {
                    AmazonNational amazonNational = new AmazonNational();
                    amazonNational.setArguments(bundle);
                    return amazonNational;
                }else{
                    AmazonFbaNational amazonFbaNational = new AmazonFbaNational();
                    amazonFbaNational.setArguments(bundle);
                    return amazonFbaNational;
                }
        }
        return null;
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
