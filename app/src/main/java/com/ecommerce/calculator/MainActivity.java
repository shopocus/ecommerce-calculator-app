package com.ecommerce.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton details,expenses;
    TextView pp,gst,raw,transport;
    EditText num2,num3,num4,num5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        details = (ImageButton)findViewById(R.id.details_dropdown);
        pp = (TextView)findViewById(R.id.text_pp);
        gst = (TextView)findViewById(R.id.text_gst);
        transport = (TextView)findViewById(R.id.text_transport);
        raw = (TextView)findViewById(R.id.text_raw);
        expenses = (ImageButton)findViewById(R.id.expenses_dropdown);
        num2 = (EditText)findViewById(R.id.number2);
        num3 = (EditText)findViewById(R.id.number3);
        num4 = (EditText)findViewById(R.id.number4);
        num5 = (EditText)findViewById(R.id.number5);
    }

    public void extension_details(View view){
        if(pp.getVisibility()==View.GONE) {
            pp.setVisibility(View.VISIBLE);
            num2.setVisibility(View.VISIBLE);
            gst.setVisibility(View.VISIBLE);
            num3.setVisibility(View.VISIBLE);
        }
        else{
            pp.setVisibility(View.GONE);
            num2.setVisibility(View.GONE);
            gst.setVisibility(View.GONE);
            num3.setVisibility(View.GONE);
        }
    }
    public void extension_expenses(View view){
        if(transport.getVisibility()==View.GONE) {
            transport.setVisibility(View.VISIBLE);
            num4.setVisibility(View.VISIBLE);
            raw.setVisibility(View.VISIBLE);
            num5.setVisibility(View.VISIBLE);
        }
        else{
            transport.setVisibility(View.GONE);
            num4.setVisibility(View.GONE);
            raw.setVisibility(View.GONE);
            num5.setVisibility(View.GONE);
        }
    }

}
