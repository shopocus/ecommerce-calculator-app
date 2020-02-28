package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ecommerce.calculator.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;
import com.ecommerce.calculator.R;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.widget.Toast;

public class FragmentSelection extends AppCompatActivity {

   // private static final String TAG = "input";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_tab);

//        if(getIntent().getStringExtra(TitleAdapter.KEY).equals("1")){
//            String title = getIntent().getStringExtra(TitleAdapter.KEY);
//            Toast.makeText(this, "ho gya", Toast.LENGTH_LONG).show();
//            Log.d(TAG, title);
//
            //Toolbar toolbar = findViewById(R.id.toolbar);
            //setActionBar(toolbar);
            // toolbar = findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            // getSupportActionBar().setTitle("back arrow");
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Shopocus");
            toolbar.setSubtitle("Business Calculator");
            // getSupportActionBar().setIcon(R.drawable.shopocus);
            // getSupportActionBar().setDisplayShowTitleEnabled(true);

//        Intent intent = getIntent();
//        String mtitle = intent.getStringExtra("ititle");
//        byte[] mbytes = getIntent().getByteArrayExtra("iImage");
//        Bitmap bitmap = BitmapFactory.decodeByteArray(mbytes, 0, mbytes.length);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText("Input"));
            tabLayout.addTab(tabLayout.newTab().setText("Saved"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));

            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(pageAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent = new Intent(FragmentSelection.this, profile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(FragmentSelection.this).clear();
                Intent intent_logout = new Intent(FragmentSelection.this, LoginActivity.class);
                intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_logout);
        }
        return super.onOptionsItemSelected(item);
    }
}
