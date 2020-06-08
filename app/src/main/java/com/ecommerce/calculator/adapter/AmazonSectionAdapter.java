package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecommerce.calculator.fragments.AmazonLocal;
import com.ecommerce.calculator.fragments.AmazonNational;
import com.ecommerce.calculator.fragments.AmazonRegional;
import com.ecommerce.calculator.fragments.FlipkartLocal;
import com.ecommerce.calculator.fragments.National;
import com.ecommerce.calculator.fragments.Zonal;

public class AmazonSectionAdapter  extends FragmentPagerAdapter {

    int counttab;
    Context c;
    Bundle bundle;

    // private int mCurrentPosition = -1;

    public AmazonSectionAdapter(Context c, @NonNull FragmentManager fm, int counttab, Bundle bundle) {
        super(fm);
        this.c = c;
        this.counttab = counttab;
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                AmazonLocal amazonLocal = new AmazonLocal();
                amazonLocal.setArguments(bundle);
                return amazonLocal;
            case 1:
                AmazonRegional amazonRegional = new AmazonRegional();
                amazonRegional.setArguments(bundle);
                return amazonRegional;
            case 2:
                AmazonNational amazonNational = new AmazonNational();
                amazonNational.setArguments(bundle);
                return amazonNational;
        }
        return null;
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
