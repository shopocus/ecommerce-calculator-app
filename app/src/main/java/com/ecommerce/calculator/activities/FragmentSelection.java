package com.ecommerce.calculator.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import com.ecommerce.calculator.fragments.input;
import com.ecommerce.calculator.fragments.saved;
import com.ecommerce.calculator.R;
import androidx.appcompat.app.AppCompatActivity;

public class FragmentSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_tab);
    }
}
