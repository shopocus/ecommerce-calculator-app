package com.ecommerce.calculator.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.ecommerce.calculator.R;

public class calculation extends AppCompatActivity {

    ImageButton details,expenses,discounts;
    TextView pp,gst,transport,packaging,labour,storage,other,price,percentage,line1,line2,line3,line4,line5,line6;
    EditText num2,num3,num4,num5,num6,num7,num8,num9,num10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        details = (ImageButton) findViewById(R.id.details_dropdown);
        pp = (TextView) findViewById(R.id.text_pp);
        gst = (TextView) findViewById(R.id.text_gst);
        transport = (TextView) findViewById(R.id.text_transport);
        packaging = (TextView) findViewById(R.id.text_packaging);
        labour = (TextView) findViewById(R.id.text_labour);
        storage = (TextView)findViewById(R.id.text_Storage);
        other = (TextView)findViewById(R.id.text_other);
        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        line4 = (TextView) findViewById(R.id.line4);
        line5 = (TextView) findViewById(R.id.line5);
        line6 = (TextView) findViewById(R.id.line6);
        expenses = (ImageButton) findViewById(R.id.expenses_dropdown);
        discounts = (ImageButton)findViewById(R.id.discounts_dropdown);
        price = (TextView)findViewById(R.id.text_price);
        percentage = (TextView)findViewById(R.id.text_percentage);
        num2 = (EditText) findViewById(R.id.number2);
        num3 = (EditText) findViewById(R.id.number3);
        num4 = (EditText) findViewById(R.id.number4);
        num5 = (EditText) findViewById(R.id.number5);
        num6 = (EditText)findViewById(R.id.number6);
        num7 = (EditText)findViewById(R.id.number7);
        num8 = (EditText)findViewById(R.id.number8);
        num9 = (EditText)findViewById(R.id.number9);
        num10 = (EditText)findViewById(R.id.number10);
    }

    public void extension_details(View view){
        if(pp.getVisibility()==View.GONE) {
            pp.setVisibility(View.VISIBLE);
            num2.setVisibility(View.VISIBLE);
            gst.setVisibility(View.VISIBLE);
            num3.setVisibility(View.VISIBLE);
            line1.setVisibility(view.VISIBLE);
            details.setRotation(180);
        }
        else{
            pp.setVisibility(View.GONE);
            num2.setVisibility(View.GONE);
            gst.setVisibility(View.GONE);
            num3.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
            details.setRotation(180);
        }
    }
    public void extension_expenses(View view){
        if(transport.getVisibility()==View.GONE) {
            transport.setVisibility(View.VISIBLE);
            num4.setVisibility(View.VISIBLE);
            packaging.setVisibility(View.VISIBLE);
            num5.setVisibility(View.VISIBLE);
            labour.setVisibility(View.VISIBLE);
            num6.setVisibility(View.VISIBLE);
            storage.setVisibility(View.VISIBLE);
            num7.setVisibility(View.VISIBLE);
            other.setVisibility(View.VISIBLE);
            num8.setVisibility(View.VISIBLE);
            line2.setVisibility(view.VISIBLE);
            line3.setVisibility(view.VISIBLE);
            line4.setVisibility(view.VISIBLE);
            line5.setVisibility(view.VISIBLE);
            expenses.setRotation(180);
        }
        else{
            transport.setVisibility(View.GONE);
            num4.setVisibility(View.GONE);
            packaging.setVisibility(View.GONE);
            num5.setVisibility(View.GONE);
            labour.setVisibility(View.GONE);
            num6.setVisibility(View.GONE);
            storage.setVisibility(View.GONE);
            num7.setVisibility(View.GONE);
            other.setVisibility(View.GONE);
            num8.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            line3.setVisibility(View.GONE);
            line4.setVisibility(View.GONE);
            line5.setVisibility(View.GONE);
            expenses.setRotation(180);
        }
    }

    public void extension_discounts(View view){
        if(price.getVisibility()==View.GONE) {
            price.setVisibility(View.VISIBLE);
            num9.setVisibility(View.VISIBLE);
            percentage.setVisibility(View.VISIBLE);
            num10.setVisibility(View.VISIBLE);
            line6.setVisibility(view.VISIBLE);
            discounts.setRotation(180);
        }
        else{
            price.setVisibility(View.GONE);
            num9.setVisibility(View.GONE);
            percentage.setVisibility(View.GONE);
            num10.setVisibility(View.GONE);
            line6.setVisibility(View.GONE);
            discounts.setRotation(180);
        }
    }
}
