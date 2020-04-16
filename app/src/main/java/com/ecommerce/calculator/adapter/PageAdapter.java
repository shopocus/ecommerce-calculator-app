package com.ecommerce.calculator.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.ecommerce.calculator.fragments.input;
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
        //switch(position){
          //  case 0:
                if(SharedPrefManager.getInstance(c).getCompany().equals("meesho")) {
                    if (position == 0) {
                        input input = new input();
                        return input;
                    } else {
                        saved saved = new saved();
                        return saved;
                    }
                }
            //case 1:
        return null;
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
