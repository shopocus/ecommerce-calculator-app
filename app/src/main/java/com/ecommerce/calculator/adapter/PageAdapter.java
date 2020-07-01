package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ecommerce.calculator.fragments.flipkart_calculation;
import com.ecommerce.calculator.fragments.amazon_calculation;
import com.ecommerce.calculator.fragments.amazonFba_calculation;
import com.ecommerce.calculator.fragments.ebay_calculation;
import com.ecommerce.calculator.fragments.meesho_calculation;
import com.ecommerce.calculator.fragments.clubfactory_calculation;
import com.ecommerce.calculator.fragments.saved;
import com.ecommerce.calculator.storage.SharedPrefManager;

public class PageAdapter extends FragmentStatePagerAdapter {

    int counttab;
    Context c;
    Bundle bundle;

    public PageAdapter(Context c, FragmentManager fm, int counttab, Bundle bundle) {
        super(fm);
        this.c = c;
        this.counttab = counttab;
        this.bundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            if (SharedPrefManager.getInstance(c).getCompany().equals("meesho")) {
                meesho_calculation meesho_calculation = new meesho_calculation();
                meesho_calculation.setArguments(bundle);
                return meesho_calculation;
            } else if (SharedPrefManager.getInstance(c).getCompany().equals("clubFactory")) {
                clubfactory_calculation clubfactory_calculation = new clubfactory_calculation();
                clubfactory_calculation.setArguments(bundle);
                return clubfactory_calculation;
            } else if (SharedPrefManager.getInstance(c).getCompany().equals("flipkart")) {
                flipkart_calculation flipkart_calculation = new flipkart_calculation();
                flipkart_calculation.setArguments(bundle);
                return flipkart_calculation;
            } else if (SharedPrefManager.getInstance(c).getCompany().equals("amazon")) {
                amazon_calculation amazon_calculation = new amazon_calculation();
                amazon_calculation.setArguments(bundle);
                return amazon_calculation;
            } else if (SharedPrefManager.getInstance(c).getCompany().equals("amazonFba")) {
                amazonFba_calculation amazonFba_calculation = new amazonFba_calculation();
                amazonFba_calculation.setArguments(bundle);
                return amazonFba_calculation;
            } else if (SharedPrefManager.getInstance(c).getCompany().equals("ebay")) {
                ebay_calculation ebay_calculation = new ebay_calculation();
                ebay_calculation.setArguments(bundle);
                return ebay_calculation;
            }
        } else {
            saved saved = new saved();
            return saved;
        }
        return null;
    }

    @Override
    public int getCount() {
        return counttab;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
