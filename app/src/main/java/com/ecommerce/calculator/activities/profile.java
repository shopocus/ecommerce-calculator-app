package com.ecommerce.calculator.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.ecommerce.calculator.R;

public class profile extends AppCompatActivity {

    private TextView textViewEmail, textViewName, textViewMobile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shopocus");
        toolbar.setSubtitle("Business Calculator");

        textViewEmail = (TextView)findViewById(R.id.textViewEmail);
        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewMobile = (TextView)findViewById(R.id.textViewMobile);

        textViewEmail.setText(SharedPrefManager.getInstance(profile.this).getUser().getName());
        textViewName.setText(SharedPrefManager.getInstance(profile.this).getUser().getEmail());
        textViewMobile.setText(SharedPrefManager.getInstance(profile.this).getUser().getMobile_no());
    }
}