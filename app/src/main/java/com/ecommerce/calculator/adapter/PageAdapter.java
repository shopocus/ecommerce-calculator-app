package com.ecommerce.calculator.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.ecommerce.calculator.fragments.input;
import com.ecommerce.calculator.fragments.clubfactory_calculation;
import com.ecommerce.calculator.fragments.saved;
import com.ecommerce.calculator.storage.SharedPrefManager;

public class PageAdapter extends FragmentStatePagerAdapter {

    int counttab;
    Context c;

    public PageAdapter(Context c, FragmentManager fm, int counttab) {
        super(fm);
        this.c = c;
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            if (SharedPrefManager.getInstance(c).getCompany().equals("meesho")) {
                //if (position == 0) {
                input input = new input();
                return input;
            }
            if (SharedPrefManager.getInstance(c).getCompany().equals("club factory")) {
                //if (position == 0) {
                clubfactory_calculation clubfactory_calculation = new clubfactory_calculation();
                return clubfactory_calculation;
            }
        }else {
            saved saved = new saved();
            return saved;
        }
        return null;
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
