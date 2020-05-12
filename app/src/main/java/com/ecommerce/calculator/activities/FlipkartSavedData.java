package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlipkartSavedData extends AppCompatActivity {

    private TextView textViewTitle, textViewCategory, textViewSellingPrice, textViewPurchasePrice, textViewGst, textViewWeight, textViewCourier,
            textViewPaymentMode, textViewInwardShipping, textViewPackagingExpenses, textViewLabour, textViewStorageFees, textViewOther,
            textViewByPrice, textViewByPercentage, textViewBankSettlement, textViewTotalCommision, textViewProfit, textViewTotalGstPayable,
            textViewTcs, textViewGstPayable, textViewGstClaim, textViewProfitPercentage;

    Button edit,close;
    CardView input_card,output_card;
    LinearLayout buttons, linearLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_result);

        linearLayout = findViewById(R.id.linearlayout);
        input_card = findViewById(R.id.input_card);
        output_card = findViewById(R.id.output_card);
        buttons = findViewById(R.id.buttons);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewCategory = findViewById(R.id.textViewCategory);
        textViewSellingPrice = findViewById(R.id.textViewSellingPrice);
        textViewPurchasePrice = findViewById(R.id.textViewPurchasePrice);
        textViewGst = findViewById(R.id.textViewGst);
        textViewWeight = findViewById(R.id.textViewWeight);
        textViewCourier = findViewById(R.id.textViewCourier);
        textViewPaymentMode = findViewById(R.id.textViewPaymentMode);
        textViewInwardShipping = findViewById(R.id.textViewInwardShipping);
        textViewPackagingExpenses = findViewById(R.id.textViewPackagingExpense);
        textViewLabour = findViewById(R.id.textViewLabour);
        textViewStorageFees = findViewById(R.id.textViewStorageFees);
        textViewOther = findViewById(R.id.textViewOther);
        textViewByPrice = findViewById(R.id.textViewByPrice);
        textViewByPercentage = findViewById(R.id.textViewByPercentage);
        textViewBankSettlement = findViewById(R.id.textViewBankSettlement);
        textViewTotalCommision = findViewById(R.id.textViewMeeshoCommision);
        textViewProfit = findViewById(R.id.textViewProfit);
        textViewTotalGstPayable = findViewById(R.id.textViewTotalGstPayable);
        textViewTcs = findViewById(R.id.textViewTcs);
        textViewGstPayable = findViewById(R.id.textViewGstPayable);
        textViewGstClaim = findViewById(R.id.textViewGstClaim);
        textViewProfitPercentage = findViewById(R.id.textViewProfitPercentage);

//        close = findViewById(R.id.action_close);
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getDialog().dismiss();
//            }
//        });

        edit = findViewById(R.id.action_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag = "true";
                SharedPrefManager.getInstance(FlipkartSavedData.this)
                        .saveFlag(flag);
                Intent intent = new Intent(FlipkartSavedData.this, FragmentSelection.class);
                startActivity(intent);
            }
        });
        FetchData();
    }

    public void FetchData(){
        String company = SharedPrefManager.getInstance(FlipkartSavedData.this).getCompany();
        String title = SharedPrefManager.getInstance(FlipkartSavedData.this).getTitle();
        Call<TitleDataResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getData(company, title);

        call.enqueue(new Callback<TitleDataResponse>() {
            @Override
            public void onResponse(Call<TitleDataResponse> call, Response<TitleDataResponse> response) {
                if (response.isSuccessful()) {
                    TitleDataResponse td = response.body();
                    if (td.getTitle().equals(title)) {
//                        SharedPrefManager.getInstance(getActivity())
//                                .saveData(td);
//                        input_card.setVisibility(getView().VISIBLE);
//                        output_card.setVisibility(getView().VISIBLE);
//                        buttons.setVisibility(getView().VISIBLE);
//                        textViewTitle.setVisibility(getView().VISIBLE);
                        textViewTitle.setText(td.getTitle());
                        textViewCategory.setText(td.getCategory());
                        textViewSellingPrice.setText(td.getSellingPrice());
                        textViewPurchasePrice.setText(td.getProductPriceWithoutGst());
                        textViewGst.setText(td.getGstOnProduct());
                        textViewWeight.setText(td.getWeight());
                        textViewCourier.setText(td.getCourier());
                        textViewPaymentMode.setText(td.getPaymentMode());
                        textViewInwardShipping.setText(td.getInwardShipping());
                        textViewPackagingExpenses.setText(td.getPackagingExpense());
                        textViewLabour.setText(td.getLabour());
                        textViewStorageFees.setText(td.getStorageFee());
                        textViewOther.setText(td.getOther());
                        textViewByPrice.setText(td.getDiscountAmount());
                        textViewByPercentage.setText(td.getDiscountPercent());
                        textViewBankSettlement.setText(td.getBankSettlement());
                        textViewTotalCommision.setText(td.getTotalMeeshoCommision());
                        textViewProfit.setText(td.getProfit());
                        textViewTotalGstPayable.setText(td.getTotalGstPayable());
                        textViewTcs.setText(td.getTcs());
                        textViewGstPayable.setText(td.getGstPayable());
                        textViewGstClaim.setText(td.getGstClaim());
                        textViewProfitPercentage.setText(td.getProfitPercentage());
                    }
                }else if(response.code() == 401){
                    Toast.makeText(FlipkartSavedData.this, "Session Expire! Please Login Again", Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(FlipkartSavedData.this).clear();
                    Intent intent_logout = new Intent(FlipkartSavedData.this, HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_logout);
                }
            }
            @Override
            public void onFailure(Call<TitleDataResponse> call, Throwable t) {
                Toast.makeText(FlipkartSavedData.this, "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
