package com.ecommerce.calculator.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecommerce.calculator.fragments.Kerala;
import com.ecommerce.calculator.fragments.Local;
import com.ecommerce.calculator.fragments.Metro;
import com.ecommerce.calculator.fragments.Regional;
import com.ecommerce.calculator.fragments.RestOfIndia;
import com.ecommerce.calculator.fragments.input;

import java.util.ArrayList;
import java.util.List;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    //private List<Fragment> fragmentList = new ArrayList<>();
    //private List<String> titleList = new ArrayList<>();
    int counttab;
    Context c;

    public SectionPagerAdapter(Context c, @NonNull FragmentManager fm, int counttab) {
        super(fm);
        this.c = c;
        this.counttab = counttab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                Local local = new Local();
                return local;
            case 1:
                Regional regional = new Regional();
                return regional;
            case 2:
                Metro metro = new Metro();
                return metro;
            case 3:
                RestOfIndia restOfIndia = new RestOfIndia();
                return restOfIndia;
            case 4:
                Kerala kerala = new Kerala();
                return kerala;
        }
        //return fragmentList.get(position);
        return null;
    }

    @Override
    public int getCount() {
      //  return fragmentList.size();
        return counttab;
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titleList.get(position);
//    }
//
//    public void addFragment(Fragment fragment, String title){
//        fragmentList.add(fragment);
//        titleList.add(title);
//    }
}
