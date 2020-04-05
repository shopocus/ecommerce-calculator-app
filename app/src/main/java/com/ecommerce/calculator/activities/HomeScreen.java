package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.ecommerce.calculator.R;

public class HomeScreen  extends AppCompatActivity implements View.OnClickListener {

    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        if(Build.VERSION.SDK_INT>=21){
            window=this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.homescreen_background));
        }

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            case R.id.buttonLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

}
