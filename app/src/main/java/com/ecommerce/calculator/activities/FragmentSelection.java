package com.ecommerce.calculator.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.calculator.adapter.PageAdapter;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;
import com.ecommerce.calculator.R;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.ecommerce.calculator.fragments.saved;

import java.util.Objects;

public class FragmentSelection extends AppCompatActivity {

    ViewPager viewPager;
    AdView mAdView;
    TextView title;

    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.toolbar);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.options_tab);

        MobileAds.initialize(this, "ca-app-pub-1991858249857939/5362322607");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        title = findViewById(R.id.title);
        String company = SharedPrefManager.getInstance(FragmentSelection.this).getCompany();
        switch (company) {
            case "meesho":
                title.setText("Meesho");
                break;
            case "clubFactory":
                title.setText("Club Factory");
                break;
            case "flipkart":
                title.setText("Flipkart");
                break;
            case "amazon":
                title.setText("Amazon");
                break;
            case "amazonFba":
                title.setText("Amazon Fba");
                break;
            case "ebay":
                title.setText("Ebay");
                break;
            case "other":
                title.setText("Other");
                break;
        }

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Input"));
        tabLayout.addTab(tabLayout.newTab().setText("Saved"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));

        SharedPrefManager.getInstance(FragmentSelection.this).saveTempVar("false");

        Bundle bundle = getIntent().getExtras();

        viewPager = findViewById(R.id.pager);
        PageAdapter pageAdapter = new PageAdapter(getBaseContext(), getSupportFragmentManager(), tabLayout.getTabCount(), bundle);
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                View focusedView = getCurrentFocus();
                if (focusedView != null) {
                    assert inputManager != null;
                    inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                if (SharedPrefManager.getInstance(FragmentSelection.this).getTempVar().equals("true") && tab.getPosition() == 1) {
                    SharedPrefManager.getInstance(FragmentSelection.this).saveTempVar("false");
                    saved fragment = (saved) (viewPager.getAdapter()).instantiateItem(viewPager, tab.getPosition());
                    fragment.onResume();
                    viewPager.getAdapter().notifyDataSetChanged();
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
