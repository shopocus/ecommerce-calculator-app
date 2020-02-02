package com.ecommerce.calculator.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ecommerce.calculator.R;

public class calculation extends AppCompatActivity{

    ImageButton details, expenses, discounts;
    TextView pp, gst, transport, packaging, labour, storage, other, price, percentage, line1, line2, line3, line4, line5, line6, textViewResult;
    EditText num1, num2, num3, num4, num5, num6, num7, num8, num9, num10;
   // Button calculate,reset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

       /* input fragment = new input();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.pager, fragment).commit();*/
        //manager.beginTransaction().replace(R.id.pager, fragment).commit();

        /*FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.pager, fragment);
        fragmentTransaction.commit();*/

        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.pager, new input()).commit();*/

        details = (ImageButton) findViewById(R.id.details_dropdown);
        pp = (TextView) findViewById(R.id.text_pp);
        gst = (TextView) findViewById(R.id.text_gst);
        transport = (TextView) findViewById(R.id.text_transport);
        packaging = (TextView) findViewById(R.id.text_packaging);
        labour = (TextView) findViewById(R.id.text_labour);
        storage = (TextView) findViewById(R.id.text_Storage);
        other = (TextView) findViewById(R.id.text_other);
        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        line4 = (TextView) findViewById(R.id.line4);
        line5 = (TextView) findViewById(R.id.line5);
        line6 = (TextView) findViewById(R.id.line6);
        expenses = (ImageButton) findViewById(R.id.expenses_dropdown);
        discounts = (ImageButton) findViewById(R.id.discounts_dropdown);
        price = (TextView) findViewById(R.id.text_price);
        percentage = (TextView) findViewById(R.id.text_percentage);
        num1 = (EditText) findViewById(R.id.number1);
        num2 = (EditText) findViewById(R.id.number2);
        num3 = (EditText) findViewById(R.id.number3);
        num4 = (EditText) findViewById(R.id.number4);
        num5 = (EditText) findViewById(R.id.number5);
        num6 = (EditText) findViewById(R.id.number6);
        num7 = (EditText) findViewById(R.id.number7);
        num8 = (EditText) findViewById(R.id.number8);
        num9 = (EditText) findViewById(R.id.number9);
        num10 = (EditText) findViewById(R.id.number10);
        textViewResult = (TextView) findViewById(R.id.text_view_result);
       // calculate();

    }





}
