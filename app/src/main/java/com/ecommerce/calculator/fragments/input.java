package com.ecommerce.calculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.CalculateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Button;
import android.view.View.OnClickListener;

public class input extends Fragment implements View.OnClickListener{

    private ImageButton details, expenses, discounts;
    private TextView pp, gst, transport, packaging, labour, storage, other, price, percentage, line1, line2, line3, line4, line5, line6, textViewResult;
    private EditText num1, num2, num3, num4, num5, num6, num7, num8, num9, num10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu, container, false);
        details = view.findViewById(R.id.details_dropdown);
        details.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
        });

        expenses = view.findViewById(R.id.expenses_dropdown);
        expenses.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        discounts = view.findViewById(R.id.discounts_dropdown);
        discounts.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
        });

        Button calculate = view.findViewById(R.id.calculate);
        calculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               calculate();
            }
        });

        return view;
    }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            details = view.findViewById(R.id.details_dropdown);
            pp = view.findViewById(R.id.text_pp);
            gst = view.findViewById(R.id.text_gst);
            transport = view.findViewById(R.id.text_transport);
            packaging = view.findViewById(R.id.text_packaging);
            labour = view.findViewById(R.id.text_labour);
            storage = view.findViewById(R.id.text_Storage);
            other = view.findViewById(R.id.text_other);
            line1 = view.findViewById(R.id.line1);
            line2 = view.findViewById(R.id.line2);
            line3 = view.findViewById(R.id.line3);
            line4 = view.findViewById(R.id.line4);
            line5 = view.findViewById(R.id.line5);
            line6 = view.findViewById(R.id.line6);
            expenses = view.findViewById(R.id.expenses_dropdown);
            discounts = view.findViewById(R.id.discounts_dropdown);
            price = view.findViewById(R.id.text_price);
            percentage = view.findViewById(R.id.text_percentage);
            num1 = view.findViewById(R.id.number1);
            num2 = view.findViewById(R.id.number2);
            num3 = view.findViewById(R.id.number3);
            num4 = view.findViewById(R.id.number4);
            num5 = view.findViewById(R.id.number5);
            num6 = view.findViewById(R.id.number6);
            num7 = view.findViewById(R.id.number7);
            num8 = view.findViewById(R.id.number8);
            num9 = view.findViewById(R.id.number9);
            num10 = view.findViewById(R.id.number10);
           // textViewResult = view.findViewById(R.id.text_view_resulta);

           // view.findViewById(R.id.reset).setOnClickListener(this);
           // view.findViewById(R.id.calculate).setOnClickListener(this);
        }

    protected void calculate() {
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
                Toast.makeText(getActivity(), "result", Toast.LENGTH_LONG).show();
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
             //   textViewResult.append(content);
                //}else {
                //    Toast.makeText(calculation.this, CalculateResponse.getMsg(), Toast.LENGTH_LONG).show();
                //}
            }

            @Override
            public void onFailure(Call<CalculateResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
