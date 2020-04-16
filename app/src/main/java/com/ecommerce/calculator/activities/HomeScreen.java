package com.ecommerce.calculator.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.storage.SharedPrefManager;

public class HomeScreen  extends AppCompatActivity implements View.OnClickListener {

   // Window window;

    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.homescreen_background);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.homescreen);

//        if(Build.VERSION.SDK_INT>=21){
//            window=this.getWindow();
//            window.setStatusBarColor(this.getResources().getColor(R.color.white));
//        }

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, Menu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
//                TextView signup = findViewById(R.id.buttonSignUp);
//                signup.setBackground(getResources().getDrawable(R.drawable.homescreen_button));
//                signup.setEnabled(false);
                startActivity(new Intent(this, Registration.class));
                break;
            case R.id.buttonLogin:
//                TextView login = findViewById(R.id.buttonLogin);
//                login.setBackground(getResources().getDrawable(R.drawable.button_background));
//                login.setEnabled(false);
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

}
