package com.ecommerce.calculator.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.ecommerce.calculator.fragments.input;
import com.ecommerce.calculator.fragments.saved;

public class PageAdapter extends FragmentStatePagerAdapter {

    int counttab;

    public PageAdapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                input input = new input();
                return input;
            case 1:
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
