package com.ecommerce.calculator.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.adapter.OutputListAdapter;
import com.ecommerce.calculator.adapter.SectionPagerAdapter;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.models.output;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlipkartSavedData extends AppCompatActivity {

    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.toolbar);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    Bundle bundle = new Bundle();

    private TextView textViewTitle, textViewCategory, textViewSellingPrice, textViewPurchasePrice, textViewGst, textViewWeight, textViewCourier,
            textViewPaymentMode, textViewInwardShipping, textViewPackagingExpenses, textViewLabour, textViewStorageFees, textViewOther,
            textViewByPrice, textViewByPercentage, textViewBankSettlement, textViewTotalCommision, textViewProfit, textViewTotalGstPayable,
            textViewTcs, textViewGstPayable, textViewGstClaim, textViewProfitPercentage;

    ImageButton edit;
    ListView itemList;
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
        setStatusBarGradiant(this);
        setContentView(R.layout.saved_result_clubfactory);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setSubtitle(getResources().getString(R.string.app_name));

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab_layout);
        linearLayout = findViewById(R.id.linearlayout);
        input_card = findViewById(R.id.input_card);
        output_card = findViewById(R.id.output_card);
        buttons = findViewById(R.id.buttons);
        textViewTitle = findViewById(R.id.title);
//        textViewCategory = findViewById(R.id.textViewCategory);
//        textViewSellingPrice = findViewById(R.id.textViewSellingPrice);
//        textViewPurchasePrice = findViewById(R.id.textViewPurchasePrice);
//        textViewGst = findViewById(R.id.textViewGst);
//        textViewWeight = findViewById(R.id.textViewWeight);
//        textViewCourier = findViewById(R.id.textViewCourier);
//        textViewPaymentMode = findViewById(R.id.textViewPaymentMode);
//        textViewInwardShipping = findViewById(R.id.textViewInwardShipping);
//        textViewPackagingExpenses = findViewById(R.id.textViewPackagingExpense);
//        textViewLabour = findViewById(R.id.textViewLabour);
//        textViewStorageFees = findViewById(R.id.textViewStorageFees);
//        textViewOther = findViewById(R.id.textViewOther);
//        textViewByPrice = findViewById(R.id.textViewByPrice);
//        textViewByPercentage = findViewById(R.id.textViewByPercentage);
//        textViewBankSettlement = findViewById(R.id.textViewBankSettlement);
//        textViewTotalCommision = findViewById(R.id.textViewMeeshoCommision);
//        textViewProfit = findViewById(R.id.textViewProfit);
//        textViewTotalGstPayable = findViewById(R.id.textViewTotalGstPayable);
//        textViewTcs = findViewById(R.id.textViewTcs);
//        textViewGstPayable = findViewById(R.id.textViewGstPayable);
//        textViewGstClaim = findViewById(R.id.textViewGstClaim);
//        textViewProfitPercentage = findViewById(R.id.textViewProfitPercentage);
        itemList = findViewById(R.id.text_view_input);

//        close = findViewById(R.id.action_close);
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getDialog().dismiss();
//            }
//        });

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag = "true";
                SharedPrefManager.getInstance(FlipkartSavedData.this)
                        .saveFlag(flag);
                Intent intent = new Intent(FlipkartSavedData.this, FragmentSelection.class);
                intent.putExtras(bundle);
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
                       // getSupportActionBar().setTitle(td.getTitle());
                        output text1 = new output("Category", String.valueOf(td.getCategory()));
                        output text2 = new output("Selling Price", String.valueOf(td.getSellingPrice()));
                        output text3 = new output("Purchase Price", String.valueOf(td.getProductPriceWithoutGst()));
                        output text4 = new output("GST on product", String.valueOf(td.getGstOnProduct()));
                        output text5 = new output("Weight", String.valueOf(td.getWeight()));
                        output text6 = new output("Courier", String.valueOf(td.getCourier()));
                        output text7 = new output("Payment Mode", String.valueOf(td.getPaymentMode()));
                        output text8 = new output("Inward Shipping", String.valueOf(td.getInwardShipping()));
                        output text9 = new output("Packaging Expenses", String.valueOf(td.getPackagingExpense()));
                        output text10 = new output("Labour", String.valueOf(td.getLabour()));
                        output text11 = new output("Storage fees", String.valueOf(td.getStorageFee()));
                        output text12 = new output("Other Charges", String.valueOf(td.getOther()));
                        output text13 = new output("Discount on Price", String.valueOf(td.getDiscountAmount()));
                        output text14 = new output("Discount Percentage", String.valueOf(td.getDiscountPercent()));

                        ArrayList<String> input = new ArrayList<>();
                        input.add(td.getCategory());
                        input.add(td.getSellingPrice());
                        input.add(td.getProductPriceWithoutGst());
                        input.add(td.getGstOnProduct());
                        input.add(td.getWeight());
                        input.add(td.getCourier());
                        input.add(td.getPaymentMode());
                        input.add(td.getInwardShipping());
                        input.add(td.getPackagingExpense());
                        input.add(td.getLabour());
                        input.add(td.getStorageFee());
                        input.add(td.getOther());
                        input.add(td.getDiscountAmount());
                        input.add(td.getDiscountPercent());

                        ArrayList<output> outputList = new ArrayList<>();
                        outputList.add(text1);
                        outputList.add(text2);
                        outputList.add(text3);
                        outputList.add(text4);
                        outputList.add(text5);
                        outputList.add(text6);
                        outputList.add(text7);
                        outputList.add(text8);
                        outputList.add(text9);
                        outputList.add(text10);
                        outputList.add(text11);
                        outputList.add(text12);
                        outputList.add(text13);
                        outputList.add(text14);

                        OutputListAdapter adapter = new OutputListAdapter(FlipkartSavedData.this, R.layout.output_row, outputList);
                        itemList.setAdapter(adapter);
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


                        bundle.putStringArrayList("input", input);
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
