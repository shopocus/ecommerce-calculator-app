package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecommerce.calculator.fragments.Kerela;
import com.ecommerce.calculator.fragments.Local;
import com.ecommerce.calculator.fragments.Metro;
import com.ecommerce.calculator.fragments.Regional;
import com.ecommerce.calculator.fragments.RestOfIndia;

public class ClubFactoryPagerAdapter extends FragmentPagerAdapter {

    int counttab;
    Context c;
    Bundle bundle;

    public ClubFactoryPagerAdapter(Context c, @NonNull FragmentManager fm, int counttab, Bundle bundle) {
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
                Local local = new Local();
                local.setArguments(bundle);
                return local;
            case 1:
                Regional regional = new Regional();
                regional.setArguments(bundle);
                return regional;
            case 2:
                Metro metro = new Metro();
                metro.setArguments(bundle);
                return metro;
            case 3:
                RestOfIndia restOfIndia = new RestOfIndia();
                restOfIndia.setArguments(bundle);
                return restOfIndia;
            case 4:
                Kerela kerala = new Kerela();
                kerala.setArguments(bundle);
                return kerala;
        }
        return null;
    }

    @Override
    public int getCount() {
        return counttab;
    }
}