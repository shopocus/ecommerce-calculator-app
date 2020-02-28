package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.storage.SharedPrefManager;

public class Data extends AppCompatActivity {

   // public static final String KEY = "title";

    private TextView textViewSellingPrice, textViewPurchasePrice, textViewGst, textViewInwardShipping, textViewPackagingExpenses, textViewLabour,
    textViewStorageFees, textViewOther, textViewByPrice, textViewByPercentage, textViewBankSettlement, textViewTotalMeeshoCommision, textViewProfit,
    textViewTotalGstPayable, textViewTcs, textViewGstPayable, textViewGstClaim, textViewProfitPercentage;

    ImageButton edit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shopocus");
        toolbar.setSubtitle("Business Calculator");

        textViewSellingPrice = (TextView)findViewById(R.id.textViewSellingPrice);
        textViewPurchasePrice = (TextView)findViewById(R.id.textViewPurchasePrice);
        textViewGst = (TextView)findViewById(R.id.textViewGst);
        textViewInwardShipping = (TextView)findViewById(R.id.textViewInwardShipping);
        textViewPackagingExpenses = (TextView)findViewById(R.id.textViewPackagingExpense);
        textViewLabour = (TextView)findViewById(R.id.textViewLabour);
        textViewStorageFees = (TextView)findViewById(R.id.textViewStorageFees);
        textViewOther = (TextView)findViewById(R.id.textViewOther);
        textViewByPrice = (TextView)findViewById(R.id.textViewByPrice);
        textViewByPercentage = (TextView)findViewById(R.id.textViewByPercentage);
        textViewBankSettlement = (TextView)findViewById(R.id.textViewBankSettlement);
        textViewTotalMeeshoCommision = (TextView)findViewById(R.id.textViewMeeshoCommision);
        textViewProfit = (TextView)findViewById(R.id.textViewProfit);
        textViewTotalGstPayable = (TextView)findViewById(R.id.textViewTotalGstPayable);
        textViewTcs = (TextView)findViewById(R.id.textViewTcs);
        textViewGstPayable = (TextView)findViewById(R.id.textViewGstPayable);
        textViewGstClaim = (TextView)findViewById(R.id.textViewGstClaim);
        textViewProfitPercentage = (TextView)findViewById(R.id.textViewProfitPercentage);

        textViewSellingPrice.setText(SharedPrefManager.getInstance(Data.this).getData().getProductPriceWithoutGst());
        textViewPurchasePrice.setText(SharedPrefManager.getInstance(Data.this).getData().getProductPriceWithoutGst());
        textViewGst.setText(SharedPrefManager.getInstance(Data.this).getData().getGstOnProduct());
        textViewInwardShipping.setText(SharedPrefManager.getInstance(Data.this).getData().getInwardShipping());
        textViewPackagingExpenses.setText(SharedPrefManager.getInstance(Data.this).getData().getPackagingExpense());
        textViewLabour.setText(SharedPrefManager.getInstance(Data.this).getData().getLabour());
        textViewStorageFees.setText(SharedPrefManager.getInstance(Data.this).getData().getStorageFee());
        textViewOther.setText(SharedPrefManager.getInstance(Data.this).getData().getOther());
        textViewByPrice.setText(SharedPrefManager.getInstance(Data.this).getData().getDiscountAmount());
        textViewByPercentage.setText(SharedPrefManager.getInstance(Data.this).getData().getDiscountPercent());
        textViewBankSettlement.setText(SharedPrefManager.getInstance(Data.this).getData().getBankSettlement());
        textViewTotalMeeshoCommision.setText(SharedPrefManager.getInstance(Data.this).getData().getTotalMeeshoCommision());
        textViewProfit.setText(SharedPrefManager.getInstance(Data.this).getData().getProfit());
        textViewTotalGstPayable.setText(SharedPrefManager.getInstance(Data.this).getData().getTotalGstPayable());
        textViewTcs.setText(SharedPrefManager.getInstance(Data.this).getData().getTcs());
        textViewGstPayable.setText(SharedPrefManager.getInstance(Data.this).getData().getGstPayable());
        textViewGstClaim.setText(SharedPrefManager.getInstance(Data.this).getData().getGstClaim());
        textViewProfitPercentage.setText(SharedPrefManager.getInstance(Data.this).getData().getProfitPercentage());

        edit = (ImageButton)findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String flag = "true";
                SharedPrefManager.getInstance(Data.this)
                        .saveFlag(flag);
                Intent intent = new Intent(Data.this, FragmentSelection.class);
                //intent.putExtra(KEY,0);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
