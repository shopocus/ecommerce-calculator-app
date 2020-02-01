package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.CalculateResponse;

import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class calculation extends AppCompatActivity implements View.OnClickListener{

    ImageButton details, expenses, discounts;
    TextView pp, gst, transport, packaging, labour, storage, other, price, percentage, line1, line2, line3, line4, line5, line6, textViewResult;
    EditText num1, num2, num3, num4, num5, num6, num7, num8, num9, num10;
   // Button calculate,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setTitle("back arrow");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayShowTitleEnabled(true);

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
        // calculate = (Button) findViewById(R.id.calcuate);
        // reset = (Button)findViewById(R.id.reset);
        findViewById(R.id.calculate).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);
    }

    private void calculate() {
        Toast.makeText(calculation.this, "yes", Toast.LENGTH_LONG).show();
        double number1 = Double.parseDouble(num1.getText().toString());
        double number2 = Double.parseDouble(num2.getText().toString());
        double number3 = Double.parseDouble(num3.getText().toString());
        double number4 = Double.parseDouble(num4.getText().toString());
        double number5 = Double.parseDouble(num5.getText().toString());
        double number6 = Double.parseDouble(num6.getText().toString());
        double number7 = Double.parseDouble(num7.getText().toString());
        double number8 = Double.parseDouble(num8.getText().toString());
        double number9 = Double.parseDouble(num9.getText().toString());
        double number10 = Double.parseDouble(num10.getText().toString());

        Call<CalculateResponse> call = RetrofitClient
                .getInstance().getApi().calculate(number1, number3, number2, number4, number5, number6, number7, number8, number10, number9);

        call.enqueue(new Callback<CalculateResponse>() {
            @Override
            public void onResponse(Call<CalculateResponse> call, Response<CalculateResponse> response) {
                CalculateResponse CalculateResponse = response.body();
                Toast.makeText(calculation.this, "yes boss", Toast.LENGTH_LONG).show();
                //textViewResult.setText(SharedPrefManager.getInstance(getApplication()).getResult());
               // if(CalculateResponse.getMsg().equals("")) {
                   String content = "";
                    content += "bankSettlement: " + CalculateResponse.getBankSettlement() + "\n";
                    content += "totalMeeshoCommision: " + CalculateResponse.getTotalMeeshoCommision() + "\n";
                    content += "profit: " + CalculateResponse.getProfit() + "\n";
                    content += "totalGstPayable: " + CalculateResponse.getTotalGstPayable() + "\n";
                    content += "tcs: " + CalculateResponse.getTcs() + "\n";
                    content += "gstPayable: " + CalculateResponse.getGstPayable() + "\n";
                    content += "gstClaim: " + CalculateResponse.getGstClaim() + "\n";
                    content += "profitPercentage: " + CalculateResponse.getProfitPercentage() + "\n";
                    textViewResult.append(content);
                //}else {
                //    Toast.makeText(calculation.this, CalculateResponse.getMsg(), Toast.LENGTH_LONG).show();
                //}
            }

            @Override
            public void onFailure(Call<CalculateResponse> call, Throwable t) {
                Toast.makeText(calculation.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void extension_details(View view) {
        if (pp.getVisibility() == View.GONE) {
            pp.setVisibility(View.VISIBLE);
            num2.setVisibility(View.VISIBLE);
            gst.setVisibility(View.VISIBLE);
            num3.setVisibility(View.VISIBLE);
            line1.setVisibility(View.VISIBLE);
            details.setRotation(180);
        } else {
            pp.setVisibility(View.GONE);
            num2.setVisibility(View.GONE);
            gst.setVisibility(View.GONE);
            num3.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
            details.setRotation(0);
        }
    }

    public void extension_expenses(View view) {
        if (transport.getVisibility() == View.GONE) {
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
            line2.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);
            line4.setVisibility(View.VISIBLE);
            line5.setVisibility(View.VISIBLE);
            expenses.setRotation(180);
        } else {
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
            expenses.setRotation(0);
        }
    }

    public void extension_discounts(View view) {
        if (price.getVisibility() == View.GONE) {
            price.setVisibility(View.VISIBLE);
            num9.setVisibility(View.VISIBLE);
            percentage.setVisibility(View.VISIBLE);
            num10.setVisibility(View.VISIBLE);
            line6.setVisibility(View.VISIBLE);
            discounts.setRotation(180);
        } else {
            price.setVisibility(View.GONE);
            num9.setVisibility(View.GONE);
            percentage.setVisibility(View.GONE);
            num10.setVisibility(View.GONE);
            line6.setVisibility(View.GONE);
            discounts.setRotation(0);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculate:
                calculate();
                break;
            case R.id.reset:
                startActivity(new Intent(this, calculation.class));
                break;
        }
    }
}
