package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecommerce.calculator.fragments.FlipkartLocal;
import com.ecommerce.calculator.fragments.National;
import com.ecommerce.calculator.fragments.Zonal;
import com.ecommerce.calculator.utils.DynamicHeightViewPager;

public class FlipkartSectionAdapter extends FragmentPagerAdapter {

    int counttab;
    Context c;
    Bundle bundle;

   // private int mCurrentPosition = -1;

    public FlipkartSectionAdapter(Context c, @NonNull FragmentManager fm, int counttab, Bundle bundle) {
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
                FlipkartLocal flipkartLocal = new FlipkartLocal();
                flipkartLocal.setArguments(bundle);
                return flipkartLocal;
            case 1:
                Zonal zonal = new Zonal();
                zonal.setArguments(bundle);
                return zonal;
            case 2:
                National national = new National();
                national.setArguments(bundle);
                return national;
        }
        return null;
    }

    @Override
    public int getCount() {
        return counttab;
    }

//    public void setPrimaryItem(ViewGroup container, int position, Object object){
//        super.setPrimaryItem(container, position, object);
//        if(position != mCurrentPosition && container instanceof DynamicHeightViewPager){
//            Fragment fragment = (Fragment) object;
//            DynamicHeightViewPager pager = (DynamicHeightViewPager) container;
//            if(fragment != null && fragment.getView() != null){
//                mCurrentPosition = position;
//                pager.measureCurrentView(fragment.getView());
//            }
//        }
//    }

}
