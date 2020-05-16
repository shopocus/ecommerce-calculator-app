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
import androidx.viewpager.widget.ViewPager;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.adapter.SectionPagerAdapter;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

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
    TabLayout tabLayout;
    ArrayList<String> Local = new ArrayList<>();
    ArrayList<String> Regional = new ArrayList<>();
    ArrayList<String> Metro = new ArrayList<>();
    ArrayList<String> RestOfIndia = new ArrayList<>();
    ArrayList<String> Kerela = new ArrayList<>();
    ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_result_clubfactory);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab_layout);
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

//        edit = findViewById(R.id.action_edit);
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String flag = "true";
//                SharedPrefManager.getInstance(FlipkartSavedData.this)
//                        .saveFlag(flag);
//                Intent intent = new Intent(FlipkartSavedData.this, FragmentSelection.class);
//                startActivity(intent);
//            }
//        });

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
//                        textViewBankSettlement.setText(td.getBankSettlement());
//                        textViewTotalCommision.setText(td.getTotalMeeshoCommision());
//                        textViewProfit.setText(td.getProfit());
//                        textViewTotalGstPayable.setText(td.getTotalGstPayable());
//                        textViewTcs.setText(td.getTcs());
//                        textViewGstPayable.setText(td.getGstPayable());
//                        textViewGstClaim.setText(td.getGstClaim());
//                        textViewProfitPercentage.setText(td.getProfitPercentage());
                        Local.add(String.valueOf(td.getLocal().getBankSettlement()));
                        Local.add(String.valueOf(td.getLocal().getTotalCommision()));
                        Local.add(String.valueOf(td.getLocal().getTotalGstPayable()));
                        Local.add(String.valueOf(td.getLocal().getTcs()));
                        Local.add(String.valueOf(td.getLocal().getGstPayable()));
                        Local.add(String.valueOf(td.getLocal().getGstClaim()));
                        Local.add(String.valueOf(td.getLocal().getProfit()));
                        Local.add(String.valueOf(td.getLocal().getProfitPercentage()));

                        Regional.add(String.valueOf(td.getRegional().getBankSettlement()));
                        Regional.add(String.valueOf(td.getRegional().getTotalCommision()));
                        Regional.add(String.valueOf(td.getRegional().getTotalGstPayable()));
                        Regional.add(String.valueOf(td.getRegional().getTcs()));
                        Regional.add(String.valueOf(td.getRegional().getGstPayable()));
                        Regional.add(String.valueOf(td.getRegional().getGstClaim()));
                        Regional.add(String.valueOf(td.getRegional().getProfit()));
                        Regional.add(String.valueOf(td.getRegional().getProfitPercentage()));

                        Metro.add(String.valueOf(td.getMetro().getBankSettlement()));
                        Metro.add(String.valueOf(td.getMetro().getTotalCommision()));
                        Metro.add(String.valueOf(td.getMetro().getTotalGstPayable()));
                        Metro.add(String.valueOf(td.getMetro().getTcs()));
                        Metro.add(String.valueOf(td.getMetro().getGstPayable()));
                        Metro.add(String.valueOf(td.getMetro().getGstClaim()));
                        Metro.add(String.valueOf(td.getMetro().getProfit()));
                        Metro.add(String.valueOf(td.getMetro().getProfitPercentage()));

                        RestOfIndia.add(String.valueOf(td.getRestOfIndia().getBankSettlement()));
                        RestOfIndia.add(String.valueOf(td.getRestOfIndia().getTotalCommision()));
                        RestOfIndia.add(String.valueOf(td.getRestOfIndia().getTotalGstPayable()));
                        RestOfIndia.add(String.valueOf(td.getRestOfIndia().getTcs()));
                        RestOfIndia.add(String.valueOf(td.getRestOfIndia().getGstPayable()));
                        RestOfIndia.add(String.valueOf(td.getRestOfIndia().getGstClaim()));
                        RestOfIndia.add(String.valueOf(td.getRestOfIndia().getProfit()));
                        RestOfIndia.add(String.valueOf(td.getRestOfIndia().getProfitPercentage()));

                        Kerela.add(String.valueOf(td.getKerela().getBankSettlement()));
                        Kerela.add(String.valueOf(td.getKerela().getTotalCommision()));
                        Kerela.add(String.valueOf(td.getKerela().getTotalGstPayable()));
                        Kerela.add(String.valueOf(td.getKerela().getTcs()));
                        Kerela.add(String.valueOf(td.getKerela().getGstPayable()));
                        Kerela.add(String.valueOf(td.getKerela().getGstClaim()));
                        Kerela.add(String.valueOf(td.getKerela().getProfit()));
                        Kerela.add(String.valueOf(td.getKerela().getProfitPercentage()));

                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("Local", Local);
                        bundle.putStringArrayList("Regional", Regional);
                        bundle.putStringArrayList("Metro", Metro);
                        bundle.putStringArrayList("RestOfIndia", RestOfIndia);
                        bundle.putStringArrayList("Kerela", Kerela);

                        tabLayout.addTab(tabLayout.newTab().setText("Local"));
                        tabLayout.addTab(tabLayout.newTab().setText("Regional"));
                        tabLayout.addTab(tabLayout.newTab().setText("Metro"));
                        tabLayout.addTab(tabLayout.newTab().setText("Rest Of India"));
                        tabLayout.addTab(tabLayout.newTab().setText("Kerala"));
                        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
                        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(FlipkartSavedData.this, getSupportFragmentManager(),
                                tabLayout.getTabCount(), bundle);
                        viewPager.setAdapter(sectionPagerAdapter);
                        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                viewPager.setCurrentItem(tab.getPosition());
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });
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
