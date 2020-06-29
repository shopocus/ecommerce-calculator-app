package com.ecommerce.calculator.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.adapter.AmazonSectionAdapter;
import com.ecommerce.calculator.adapter.FlipkartSectionAdapter;
import com.ecommerce.calculator.adapter.OutputListAdapter;
import com.ecommerce.calculator.adapter.SectionPagerAdapter;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.models.output;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedData extends AppCompatActivity {

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

    private TextView textViewTitle;
    ImageButton edit;
    ListView itemList, result;
    CardView input_card, output_card;
    LinearLayout linearLayout;
    TabLayout tabLayout;
    ArrayList<String> Local = new ArrayList<>();
    ArrayList<String> Regional = new ArrayList<>();
    ArrayList<String> Metro = new ArrayList<>();
    ArrayList<String> RestOfIndia = new ArrayList<>();
    ArrayList<String> Kerela = new ArrayList<>();
    ArrayList<String> Zonal = new ArrayList<>();
    ArrayList<String> National = new ArrayList<>();
    ViewPager viewPager;
    LoadingDialog loadingDialog;
    ImageButton backGo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.saved_result_common);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab_layout);
        linearLayout = findViewById(R.id.linearlayout);
        input_card = findViewById(R.id.input_card);
        output_card = findViewById(R.id.result_card);
        textViewTitle = findViewById(R.id.title);
        itemList = findViewById(R.id.text_view_input);
        result = findViewById(R.id.text_view_result);

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag = "true";
                SharedPrefManager.getInstance(SavedData.this)
                        .saveFlag(flag);
                Intent intent = new Intent(SavedData.this, FragmentSelection.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        backGo = findViewById(R.id.backGo);
        backGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavedData.super.finish();
            }
        });

        FetchData();
    }

    public void FetchData() {
        String company = SharedPrefManager.getInstance(SavedData.this).getCompany();
        String title = SharedPrefManager.getInstance(SavedData.this).getTitle();
        Call<TitleDataResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getData(company, title);

        call.enqueue(new Callback<TitleDataResponse>() {
            @Override
            public void onResponse(@NotNull Call<TitleDataResponse> call, @NotNull Response<TitleDataResponse> response) {
                if (response.isSuccessful()) {
                    TitleDataResponse td = response.body();
                    assert td != null;
                    if (td.getInput().getTitle().equals(title)) {
                        loadingDialog.dismissDialog();
                        input_card.setVisibility(View.VISIBLE);
                        output_card.setVisibility(View.VISIBLE);
                        textViewTitle.setText(td.getInput().getTitle());
                        switch (company) {
                            case "meesho":
                                meeshoSavedData(td);
                                break;
                            case "clubFactory":
                                clubFactoryData(td);
                                break;
                            case "flipkart":
                                flipkartData(td);
                                break;
                            case "amazon":
                                amazonData(td);
                                break;
                            case "amazonFba":
                                amazonFbaData(td);
                                break;
                            case "ebay":
                                ebayData(td);
                                break;
                        }
                    }
                } else if (response.code() == 401) {
                    Toast.makeText(SavedData.this, "Session Expire! Please Login Again", Toast.LENGTH_SHORT).show();
                    Intent intent_logout = new Intent(SavedData.this, HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_logout);
                }
            }

            @Override
            public void onFailure(@NotNull Call<TitleDataResponse> call, @NotNull Throwable t) {
                Toast.makeText(SavedData.this, "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void meeshoSavedData(TitleDataResponse td) {
        result.setVisibility(View.VISIBLE);
        output text1 = new output("Category", String.valueOf(td.getInput().getCategory()));
        output text2 = new output("Selling Price", String.valueOf(td.getInput().getSellingPrice()));
        output text3 = new output("Purchase Price", String.valueOf(td.getInput().getProductPriceWithoutGst()));
        output text4 = new output("GST on product", String.valueOf(td.getInput().getGstOnProduct()));
        output text5 = new output("Inward Shipping", String.valueOf(td.getInput().getInwardShipping()));
        output text6 = new output("Packaging Expenses", String.valueOf(td.getInput().getPackagingExpense()));
        output text7 = new output("Labour", String.valueOf(td.getInput().getLabour()));
        output text8 = new output("Storage fees", String.valueOf(td.getInput().getStorageFee()));
        output text9 = new output("Other Charges", String.valueOf(td.getInput().getOther()));
        output text10 = new output("Discount on Price", String.valueOf(td.getInput().getDiscountAmount()));
        output text11 = new output("Discount Percentage", String.valueOf(td.getInput().getDiscountPercent()));

        ArrayList<output> inputList = new ArrayList<>();
        inputList.add(text1);
        inputList.add(text2);
        inputList.add(text3);
        inputList.add(text4);
        inputList.add(text5);
        inputList.add(text6);
        inputList.add(text7);
        inputList.add(text8);
        inputList.add(text9);
        inputList.add(text10);
        inputList.add(text11);

        OutputListAdapter adapter = new OutputListAdapter(SavedData.this, R.layout.output_row, inputList);
        itemList.setAdapter(adapter);
        SavedData.setListViewHeightBasedOnChildren(itemList);

        output text12 = new output("Commission Fees", td.getOutput().getCommissionFees());
        output text13 = new output("Shipping Fees", td.getOutput().getShippingFees());
        output text14 = new output("CS", td.getOutput().getCS());
        output text15 = new output("GST On CS", td.getOutput().getGstOnCS());
        output text16 = new output("Total Charges", td.getOutput().getTotalCharges());
        output text17 = new output("Bank Settlement", td.getOutput().getBankSettlement());
        output text18 = new output("GST Claim", td.getOutput().getGstClaim());
        output text19 = new output("GST Payable", td.getOutput().getGstPayable());
        output text20 = new output("Total GST Payable", td.getOutput().getTotalGstPayable());
        output text21 = new output("TCS", td.getOutput().getTcs());
        output text22 = new output("Profit", td.getOutput().getProfit());
        output text23 = new output("Profit Percentage", td.getOutput().getProfitPercentage());

        ArrayList<output> outputList = new ArrayList<>();
        outputList.add(text12);
        outputList.add(text13);
        outputList.add(text14);
        outputList.add(text15);
        outputList.add(text16);
        outputList.add(text17);
        outputList.add(text18);
        outputList.add(text19);
        outputList.add(text20);
        outputList.add(text21);
        outputList.add(text22);
        outputList.add(text23);

//        ArrayList<String> title = new ArrayList<>();
//        title.add("Category");
//        title.add("Selling Price");
//        title.add("Purchase Price");
//        title.add("GST on Product");
//        title.add("Weight");
//        title.add("Courier");
//        title.add("Payment Mode");
//        title.add("Inward Shipping");
//        title.add("Packaging Expenses");
//        title.add("Labour");
//        title.add("Storage Fees");
//        title.add("Other Charges");
//        title.add("Discount on Price");
//        title.add("Discount Percentage");

        ArrayList<String> input = new ArrayList<>();
        input.add(td.getInput().getCategory());
        input.add(td.getInput().getSellingPrice());
        input.add(td.getInput().getProductPriceWithoutGst());
        input.add(td.getInput().getGstOnProduct());
        input.add(td.getInput().getInwardShipping());
        input.add(td.getInput().getPackagingExpense());
        input.add(td.getInput().getLabour());
        input.add(td.getInput().getStorageFee());
        input.add(td.getInput().getOther());
        input.add(td.getInput().getDiscountAmount());
        input.add(td.getInput().getDiscountPercent());

        bundle.putStringArrayList("input", input);

        OutputListAdapter adapterOutput = new OutputListAdapter(SavedData.this, R.layout.output_row, outputList);
        result.setAdapter(adapterOutput);
        SavedData.setListViewHeightBasedOnChildren(result);
    }

    void clubFactoryData(TitleDataResponse td) {
        viewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        output text1 = new output("Category", String.valueOf(td.getInput().getCategory()));
        output text2 = new output("Selling Price", String.valueOf(td.getInput().getSellingPrice()));
        output text3 = new output("Purchase Price", String.valueOf(td.getInput().getProductPriceWithoutGst()));
        output text4 = new output("GST on product", String.valueOf(td.getInput().getGstOnProduct()));
        output text5 = new output("Weight", String.valueOf(td.getInput().getWeight()));
        output text6 = new output("Courier", String.valueOf(td.getInput().getCourier()));
        output text7 = new output("Payment Mode", String.valueOf(td.getInput().getPaymentMode()));
        output text8 = new output("Inward Shipping", String.valueOf(td.getInput().getInwardShipping()));
        output text9 = new output("Packaging Expenses", String.valueOf(td.getInput().getPackagingExpense()));
        output text10 = new output("Labour", String.valueOf(td.getInput().getLabour()));
        output text11 = new output("Storage fees", String.valueOf(td.getInput().getStorageFee()));
        output text12 = new output("Other Charges", String.valueOf(td.getInput().getOther()));
        output text13 = new output("Discount on Price", String.valueOf(td.getInput().getDiscountAmount()));
        output text14 = new output("Discount Percentage", String.valueOf(td.getInput().getDiscountPercent()));

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

        OutputListAdapter adapter = new OutputListAdapter(SavedData.this, R.layout.output_row, outputList);
        itemList.setAdapter(adapter);
        setListViewHeightBasedOnChildren(itemList);

        ArrayList<String> input = new ArrayList<>();
        input.add(td.getInput().getCategory());
        input.add(td.getInput().getSellingPrice());
        input.add(td.getInput().getProductPriceWithoutGst());
        input.add(td.getInput().getGstOnProduct());
        input.add(td.getInput().getWeight());
        input.add(td.getInput().getCourier());
        input.add(td.getInput().getPaymentMode());
        input.add(td.getInput().getInwardShipping());
        input.add(td.getInput().getPackagingExpense());
        input.add(td.getInput().getLabour());
        input.add(td.getInput().getStorageFee());
        input.add(td.getInput().getOther());
        input.add(td.getInput().getDiscountAmount());
        input.add(td.getInput().getDiscountPercent());

        Local.add(String.valueOf(td.getOutput().getLocal().getCommissionFees()));
        Local.add(String.valueOf(td.getOutput().getLocal().getShippingFees()));
        if(td.getInput().getPaymentMode().equals("postpaid")){
            Local.add(String.valueOf(td.getOutput().getLocal().getCS()));
            Local.add(String.valueOf(td.getOutput().getLocal().getGstOnCS()));
        }else{
            Local.add(String.valueOf(td.getOutput().getLocal().getPrepaidFees()));
            Local.add(String.valueOf(td.getOutput().getLocal().getCSP()));
            Local.add(String.valueOf(td.getOutput().getLocal().getGstOnCSP()));
        }
        Local.add(String.valueOf(td.getOutput().getLocal().getTotalCharges()));
        Local.add(String.valueOf(td.getOutput().getLocal().getBankSettlement()));
        Local.add(String.valueOf(td.getOutput().getLocal().getGstClaim()));
        Local.add(String.valueOf(td.getOutput().getLocal().getGstPayable()));
        Local.add(String.valueOf(td.getOutput().getLocal().getTotalGstPayable()));
        Local.add(String.valueOf(td.getOutput().getLocal().getTcs()));
        Local.add(String.valueOf(td.getOutput().getLocal().getProfit()));
        Local.add(String.valueOf(td.getOutput().getLocal().getProfitPercentage()));

        Regional.add(String.valueOf(td.getOutput().getRegional().getCommissionFees()));
        Regional.add(String.valueOf(td.getOutput().getRegional().getShippingFees()));
        if(td.getInput().getPaymentMode().equals("postpaid")){
            Regional.add(String.valueOf(td.getOutput().getRegional().getCS()));
            Regional.add(String.valueOf(td.getOutput().getRegional().getGstOnCS()));
        }else{
            Regional.add(String.valueOf(td.getOutput().getRegional().getPrepaidFees()));
            Regional.add(String.valueOf(td.getOutput().getRegional().getCSP()));
            Regional.add(String.valueOf(td.getOutput().getRegional().getGstOnCSP()));
        }
        Regional.add(String.valueOf(td.getOutput().getRegional().getTotalCharges()));
        Regional.add(String.valueOf(td.getOutput().getRegional().getBankSettlement()));
        Regional.add(String.valueOf(td.getOutput().getRegional().getGstClaim()));
        Regional.add(String.valueOf(td.getOutput().getRegional().getGstPayable()));
        Regional.add(String.valueOf(td.getOutput().getRegional().getTotalGstPayable()));
        Regional.add(String.valueOf(td.getOutput().getRegional().getTcs()));
        Regional.add(String.valueOf(td.getOutput().getRegional().getProfit()));
        Regional.add(String.valueOf(td.getOutput().getRegional().getProfitPercentage()));

        Metro.add(String.valueOf(td.getOutput().getMetro().getCommissionFees()));
        Metro.add(String.valueOf(td.getOutput().getMetro().getShippingFees()));
        if(td.getInput().getPaymentMode().equals("postpaid")){
            Metro.add(String.valueOf(td.getOutput().getMetro().getCS()));
            Metro.add(String.valueOf(td.getOutput().getMetro().getGstOnCS()));
        }else{
            Metro.add(String.valueOf(td.getOutput().getMetro().getPrepaidFees()));
            Metro.add(String.valueOf(td.getOutput().getMetro().getCSP()));
            Metro.add(String.valueOf(td.getOutput().getMetro().getGstOnCSP()));
        }
        Metro.add(String.valueOf(td.getOutput().getMetro().getTotalCharges()));
        Metro.add(String.valueOf(td.getOutput().getMetro().getBankSettlement()));
        Metro.add(String.valueOf(td.getOutput().getMetro().getGstClaim()));
        Metro.add(String.valueOf(td.getOutput().getMetro().getGstPayable()));
        Metro.add(String.valueOf(td.getOutput().getMetro().getTotalGstPayable()));
        Metro.add(String.valueOf(td.getOutput().getMetro().getTcs()));
        Metro.add(String.valueOf(td.getOutput().getMetro().getProfit()));
        Metro.add(String.valueOf(td.getOutput().getMetro().getProfitPercentage()));

        RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getCommissionFees()));
        RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getShippingFees()));
        if(td.getInput().getPaymentMode().equals("postpaid")){
            RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getCS()));
            RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getGstOnCS()));
        }else{
            RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getPrepaidFees()));
            RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getCSP()));
            RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getGstOnCSP()));
        }
        RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getTotalCharges()));
        RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getBankSettlement()));
        RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getGstClaim()));
        RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getGstPayable()));
        RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getTotalGstPayable()));
        RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getTcs()));
        RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getProfit()));
        RestOfIndia.add(String.valueOf(td.getOutput().getRestOfIndia().getProfitPercentage()));

        Kerela.add(String.valueOf(td.getOutput().getKerela().getCommissionFees()));
        Kerela.add(String.valueOf(td.getOutput().getKerela().getShippingFees()));
        if(td.getInput().getPaymentMode().equals("postpaid")){
            Kerela.add(String.valueOf(td.getOutput().getKerela().getCS()));
            Kerela.add(String.valueOf(td.getOutput().getKerela().getGstOnCS()));
        }else{
            Kerela.add(String.valueOf(td.getOutput().getKerela().getPrepaidFees()));
            Kerela.add(String.valueOf(td.getOutput().getKerela().getCSP()));
            Kerela.add(String.valueOf(td.getOutput().getKerela().getGstOnCSP()));
        }
        Kerela.add(String.valueOf(td.getOutput().getKerela().getTotalCharges()));
        Kerela.add(String.valueOf(td.getOutput().getKerela().getBankSettlement()));
        Kerela.add(String.valueOf(td.getOutput().getKerela().getGstClaim()));
        Kerela.add(String.valueOf(td.getOutput().getKerela().getGstPayable()));
        Kerela.add(String.valueOf(td.getOutput().getKerela().getTotalGstPayable()));
        Kerela.add(String.valueOf(td.getOutput().getKerela().getTcs()));
        Kerela.add(String.valueOf(td.getOutput().getKerela().getProfit()));
        Kerela.add(String.valueOf(td.getOutput().getKerela().getProfitPercentage()));


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
        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(SavedData.this, getSupportFragmentManager(),
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

    void flipkartData(TitleDataResponse td) {
        viewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        output text1 = new output("Category", String.valueOf(td.getCategory()));
        output text2 = new output("Selling Price", String.valueOf(td.getSellingPrice()));
        output text3 = new output("Purchase Price", String.valueOf(td.getProductPriceWithoutGst()));
        output text4 = new output("GST on product", String.valueOf(td.getGstOnProduct()));
        output text5 = new output("Weight", String.valueOf(td.getWeight()));
        output text6 = new output("Length", String.valueOf(td.getLength()));
        output text7 = new output("Breadth", String.valueOf(td.getBreadth()));
        output text8 = new output("Height", String.valueOf(td.getHeight()));
        output text9 = new output("Customer Type", String.valueOf(td.getCustomerType()));
        output text10 = new output("Payment Mode", String.valueOf(td.getPaymentMode()));
        output text11 = new output("Inward Shipping", String.valueOf(td.getInwardShipping()));
        output text12 = new output("Packaging Expenses", String.valueOf(td.getPackagingExpense()));
        output text13 = new output("Labour", String.valueOf(td.getLabour()));
        output text14 = new output("Storage fees", String.valueOf(td.getStorageFee()));
        output text15 = new output("Other Charges", String.valueOf(td.getOther()));
        output text16 = new output("Discount on Price", String.valueOf(td.getDiscountAmount()));
        output text17 = new output("Discount Percentage", String.valueOf(td.getDiscountPercent()));

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
        outputList.add(text15);
        outputList.add(text16);
        outputList.add(text17);

        OutputListAdapter adapter = new OutputListAdapter(SavedData.this, R.layout.output_row, outputList);
        itemList.setAdapter(adapter);
        setListViewHeightBasedOnChildren(itemList);

        ArrayList<String> input = new ArrayList<>();
        input.add(td.getCategory());
        input.add(td.getSellingPrice());
        input.add(td.getProductPriceWithoutGst());
        input.add(td.getGstOnProduct());
        input.add(td.getWeight());
        input.add(td.getLength());
        input.add(td.getBreadth());
        input.add(td.getHeight());
        input.add(td.getCustomerType());
        input.add(td.getPaymentMode());
        input.add(td.getInwardShipping());
        input.add(td.getPackagingExpense());
        input.add(td.getLabour());
        input.add(td.getStorageFee());
        input.add(td.getOther());
        input.add(td.getDiscountAmount());
        input.add(td.getDiscountPercent());

        Local.add(String.valueOf(td.getFlipkartLocal().getCommissionFees()));
        Local.add(String.valueOf(td.getFlipkartLocal().getFixedFees()));
        Local.add(String.valueOf(td.getFlipkartLocal().getCollectionFees()));
        Local.add(String.valueOf(td.getFlipkartLocal().getShippingFees()));
        Local.add(String.valueOf(td.getFlipkartLocal().getCFCS()));
        Local.add(String.valueOf(td.getFlipkartLocal().getGstOnCFCS()));
        Local.add(String.valueOf(td.getFlipkartLocal().getTotalCharges()));
        Local.add(String.valueOf(td.getFlipkartLocal().getBankSettlement()));
        Local.add(String.valueOf(td.getFlipkartLocal().getTotalGstPayable()));
        Local.add(String.valueOf(td.getFlipkartLocal().getTcs()));
        Local.add(String.valueOf(td.getFlipkartLocal().getGstPayable()));
        Local.add(String.valueOf(td.getFlipkartLocal().getProfit()));
        Local.add(String.valueOf(td.getFlipkartLocal().getProfitPercentage()));

        Zonal.add(String.valueOf(td.getFlipkartZonal().getCommissionFees()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getFixedFees()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getCollectionFees()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getShippingFees()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getCFCS()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getGstOnCFCS()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getTotalCharges()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getBankSettlement()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getTotalGstPayable()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getTcs()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getGstPayable()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getProfit()));
        Zonal.add(String.valueOf(td.getFlipkartZonal().getProfitPercentage()));

        National.add(String.valueOf(td.getFlipkartNational().getCommissionFees()));
        National.add(String.valueOf(td.getFlipkartNational().getFixedFees()));
        National.add(String.valueOf(td.getFlipkartNational().getCollectionFees()));
        National.add(String.valueOf(td.getFlipkartNational().getShippingFees()));
        National.add(String.valueOf(td.getFlipkartNational().getCFCS()));
        National.add(String.valueOf(td.getFlipkartNational().getGstOnCFCS()));
        National.add(String.valueOf(td.getFlipkartNational().getTotalCharges()));
        National.add(String.valueOf(td.getFlipkartNational().getBankSettlement()));
        National.add(String.valueOf(td.getFlipkartNational().getTotalGstPayable()));
        National.add(String.valueOf(td.getFlipkartNational().getTcs()));
        National.add(String.valueOf(td.getFlipkartNational().getGstPayable()));
        National.add(String.valueOf(td.getFlipkartNational().getProfit()));
        National.add(String.valueOf(td.getFlipkartNational().getProfitPercentage()));

        bundle.putStringArrayList("input", input);
        bundle.putStringArrayList("Local", Local);
        bundle.putStringArrayList("Zonal", Zonal);
        bundle.putStringArrayList("National", National);

        tabLayout.addTab(tabLayout.newTab().setText("Local"));
        tabLayout.addTab(tabLayout.newTab().setText("Zonal"));
        tabLayout.addTab(tabLayout.newTab().setText("National"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
        FlipkartSectionAdapter flipkartSectionAdapter = new FlipkartSectionAdapter(SavedData.this, getSupportFragmentManager(),
                tabLayout.getTabCount(), bundle);
        viewPager.setAdapter(flipkartSectionAdapter);
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

    void amazonData(TitleDataResponse td) {
        viewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        output text1 = new output("Category", String.valueOf(td.getCategory()));
        output text2 = new output("subcategory", String.valueOf(td.getSubcategory()));
        output text3 = new output("Selling Price", String.valueOf(td.getSellingPrice()));
        output text4 = new output("Purchase Price", String.valueOf(td.getProductPriceWithoutGst()));
        output text5 = new output("GST on product", String.valueOf(td.getGstOnProduct()));
        output text6 = new output("Shipment Type", String.valueOf(td.getShipmentType()));
        output text15 = new output("Inward Shipping", String.valueOf(td.getInwardShipping()));
        output text16 = new output("Packaging Expenses", String.valueOf(td.getPackagingExpense()));
        output text17 = new output("Labour", String.valueOf(td.getLabour()));
        output text18 = new output("Storage fees", String.valueOf(td.getStorageFee()));
        output text19 = new output("Other Charges", String.valueOf(td.getOther()));
        output text20 = new output("Discount on Price", String.valueOf(td.getDiscountAmount()));
        output text21 = new output("Discount Percentage", String.valueOf(td.getDiscountPercent()));

        ArrayList<output> outputList = new ArrayList<>();
        outputList.add(text1);
        outputList.add(text2);
        outputList.add(text3);
        outputList.add(text4);
        outputList.add(text5);
        outputList.add(text6);
        if (String.valueOf(td.getShipmentType()).equals("easyShip")) {
            output text7 = new output("EasyShip Type", td.getEasyShip().getEasyShipType());
            output text8 = new output("Weight", String.valueOf(td.getEasyShip().getWeight()));
            output text9 = new output("Length", String.valueOf(td.getEasyShip().getLength()));
            output text10 = new output("Breadth", String.valueOf(td.getEasyShip().getBreadth()));
            output text11 = new output("Height", String.valueOf(td.getEasyShip().getHeight()));
            outputList.add(text7);
            outputList.add(text8);
            outputList.add(text9);
            outputList.add(text10);
            outputList.add(text11);
        } else {
            output text12 = new output("SelfShipLocal", String.valueOf(td.getSelfShip().getSelfShipLocal()));
            output text13 = new output("SelfShipRegional", String.valueOf(td.getSelfShip().getSelfShipRegional()));
            output text14 = new output("SelfShipNational", String.valueOf(td.getSelfShip().getSelfShipNational()));
            outputList.add(text12);
            outputList.add(text13);
            outputList.add(text14);
        }
        outputList.add(text15);
        outputList.add(text16);
        outputList.add(text17);
        outputList.add(text18);
        outputList.add(text19);
        outputList.add(text20);
        outputList.add(text21);

        OutputListAdapter adapter = new OutputListAdapter(SavedData.this, R.layout.output_row, outputList);
        itemList.setAdapter(adapter);
        setListViewHeightBasedOnChildren(itemList);

        ArrayList<String> input = new ArrayList<>();
        input.add(td.getCategory());
        input.add(td.getSubcategory());
        input.add(td.getSellingPrice());
        input.add(td.getProductPriceWithoutGst());
        input.add(td.getGstOnProduct());
        input.add(td.getInwardShipping());
        input.add(td.getPackagingExpense());
        input.add(td.getLabour());
        input.add(td.getStorageFee());
        input.add(td.getOther());
        input.add(td.getDiscountAmount());
        input.add(td.getDiscountPercent());
        input.add(td.getShipmentType());
        if (String.valueOf(td.getShipmentType()).equals("easyShip")) {
            input.add(td.getEasyShip().getEasyShipType());
            input.add(String.valueOf(td.getEasyShip().getWeight()));
            input.add(String.valueOf(td.getEasyShip().getLength()));
            input.add(String.valueOf(td.getEasyShip().getBreadth()));
            input.add(String.valueOf(td.getEasyShip().getHeight()));
        } else {
            input.add(String.valueOf(td.getSelfShip().getSelfShipLocal()));
            input.add(String.valueOf(td.getSelfShip().getSelfShipRegional()));
            input.add(String.valueOf(td.getSelfShip().getSelfShipNational()));
        }

        Local.add(String.valueOf(td.getAmazonLocal().getReferralFees()));
        Local.add(String.valueOf(td.getAmazonLocal().getClosingFees()));
        Local.add(String.valueOf(td.getAmazonLocal().getShippingFees()));
        Local.add(String.valueOf(td.getAmazonLocal().getRCS()));
        Local.add(String.valueOf(td.getAmazonLocal().getGstOnRCS()));
        Local.add(String.valueOf(td.getAmazonLocal().getTotalCharges()));
        Local.add(String.valueOf(td.getAmazonLocal().getGstClaim()));
        Local.add(String.valueOf(td.getAmazonLocal().getBankSettlement()));
        Local.add(String.valueOf(td.getAmazonLocal().getTotalGstPayable()));
        Local.add(String.valueOf(td.getAmazonLocal().getTcs()));
        Local.add(String.valueOf(td.getAmazonLocal().getGstPayable()));
        Local.add(String.valueOf(td.getAmazonLocal().getProfit()));
        Local.add(String.valueOf(td.getAmazonLocal().getProfitPercentage()));

        Regional.add(String.valueOf(td.getAmazonRegional().getReferralFees()));
        Regional.add(String.valueOf(td.getAmazonRegional().getClosingFees()));
        Regional.add(String.valueOf(td.getAmazonRegional().getShippingFees()));
        Regional.add(String.valueOf(td.getAmazonRegional().getRCS()));
        Regional.add(String.valueOf(td.getAmazonRegional().getGstOnRCS()));
        Regional.add(String.valueOf(td.getAmazonRegional().getTotalCharges()));
        Regional.add(String.valueOf(td.getAmazonRegional().getGstClaim()));
        Regional.add(String.valueOf(td.getAmazonRegional().getBankSettlement()));
        Regional.add(String.valueOf(td.getAmazonRegional().getTotalGstPayable()));
        Regional.add(String.valueOf(td.getAmazonRegional().getTcs()));
        Regional.add(String.valueOf(td.getAmazonRegional().getGstPayable()));
        Regional.add(String.valueOf(td.getAmazonRegional().getProfit()));
        Regional.add(String.valueOf(td.getAmazonRegional().getProfitPercentage()));

        National.add(String.valueOf(td.getAmazonNational().getReferralFees()));
        National.add(String.valueOf(td.getAmazonNational().getClosingFees()));
        National.add(String.valueOf(td.getAmazonNational().getShippingFees()));
        National.add(String.valueOf(td.getAmazonNational().getRCS()));
        National.add(String.valueOf(td.getAmazonNational().getGstOnRCS()));
        National.add(String.valueOf(td.getAmazonNational().getTotalCharges()));
        National.add(String.valueOf(td.getAmazonNational().getGstClaim()));
        National.add(String.valueOf(td.getAmazonNational().getBankSettlement()));
        National.add(String.valueOf(td.getAmazonNational().getTotalGstPayable()));
        National.add(String.valueOf(td.getAmazonNational().getTcs()));
        National.add(String.valueOf(td.getAmazonNational().getGstPayable()));
        National.add(String.valueOf(td.getAmazonNational().getProfit()));
        National.add(String.valueOf(td.getAmazonNational().getProfitPercentage()));

        bundle.putStringArrayList("input", input);
        bundle.putStringArrayList("Local", Local);
        bundle.putStringArrayList("Regional", Regional);
        bundle.putStringArrayList("National", National);

        tabLayout.addTab(tabLayout.newTab().setText("Local"));
        tabLayout.addTab(tabLayout.newTab().setText("Regional"));
        if (td.getEasyShip().getWeight() <= 12000 && td.getEasyShip().getLength() * td.getEasyShip().getBreadth() * td.getEasyShip().getHeight() / 5 <= 12000) {
            tabLayout.addTab(tabLayout.newTab().setText("National"));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
        AmazonSectionAdapter amazonSectionAdapter = new AmazonSectionAdapter(SavedData.this, getSupportFragmentManager(),
                tabLayout.getTabCount(), bundle);
        viewPager.setAdapter(amazonSectionAdapter);
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

    void amazonFbaData(TitleDataResponse td) {
        viewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        output text1 = new output("Category", String.valueOf(td.getCategory()));
        output text2 = new output("subcategory", String.valueOf(td.getSubcategory()));
        output text3 = new output("Selling Price", String.valueOf(td.getSellingPrice()));
        output text4 = new output("Purchase Price", String.valueOf(td.getProductPriceWithoutGst()));
        output text5 = new output("GST on product", String.valueOf(td.getGstOnProduct()));
        output text6 = new output("Weight", String.valueOf(td.getWeight()));
        output text7 = new output("Length", String.valueOf(td.getLength()));
        output text8 = new output("Breadth", String.valueOf(td.getBreadth()));
        output text9 = new output("Height", String.valueOf(td.getHeight()));
        output text10 = new output("Inward Shipping", String.valueOf(td.getInwardShipping()));
        output text11 = new output("Other Charges", String.valueOf(td.getOther()));
        output text12 = new output("Discount on Price", String.valueOf(td.getDiscountAmount()));
        output text13 = new output("Discount Percentage", String.valueOf(td.getDiscountPercent()));

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

        OutputListAdapter adapter = new OutputListAdapter(SavedData.this, R.layout.output_row, outputList);
        itemList.setAdapter(adapter);
        setListViewHeightBasedOnChildren(itemList);

        ArrayList<String> input = new ArrayList<>();
        input.add(td.getCategory());
        input.add(td.getSubcategory());
        input.add(td.getSellingPrice());
        input.add(td.getProductPriceWithoutGst());
        input.add(td.getGstOnProduct());
        input.add(td.getWeight());
        input.add(td.getLength());
        input.add(td.getBreadth());
        input.add(td.getHeight());
        input.add(td.getInwardShipping());
        input.add(td.getOther());
        input.add(td.getDiscountAmount());
        input.add(td.getDiscountPercent());


        Local.add(String.valueOf(td.getAmazonFbaLocal().getReferralFees()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getClosingFees()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getShippingFees()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getRCF()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getGstOnRCF()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getTotalCharges()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getGstClaim()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getBankSettlement()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getTotalGstPayable()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getTcs()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getGstPayable()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getProfit()));
        Local.add(String.valueOf(td.getAmazonFbaLocal().getProfitPercentage()));

        Regional.add(String.valueOf(td.getAmazonFbaRegional().getReferralFees()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getClosingFees()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getShippingFees()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getRCF()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getGstOnRCF()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getTotalCharges()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getGstClaim()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getBankSettlement()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getTotalGstPayable()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getTcs()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getGstPayable()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getProfit()));
        Regional.add(String.valueOf(td.getAmazonFbaRegional().getProfitPercentage()));

        National.add(String.valueOf(td.getAmazonFbaNational().getReferralFees()));
        National.add(String.valueOf(td.getAmazonFbaNational().getClosingFees()));
        National.add(String.valueOf(td.getAmazonFbaNational().getShippingFees()));
        National.add(String.valueOf(td.getAmazonFbaNational().getRCF()));
        National.add(String.valueOf(td.getAmazonFbaNational().getGstOnRCF()));
        National.add(String.valueOf(td.getAmazonFbaNational().getTotalCharges()));
        National.add(String.valueOf(td.getAmazonFbaNational().getGstClaim()));
        National.add(String.valueOf(td.getAmazonFbaNational().getBankSettlement()));
        National.add(String.valueOf(td.getAmazonFbaNational().getTotalGstPayable()));
        National.add(String.valueOf(td.getAmazonFbaNational().getTcs()));
        National.add(String.valueOf(td.getAmazonFbaNational().getGstPayable()));
        National.add(String.valueOf(td.getAmazonFbaNational().getProfit()));
        National.add(String.valueOf(td.getAmazonFbaNational().getProfitPercentage()));

        bundle.putStringArrayList("input", input);
        bundle.putStringArrayList("Local", Local);
        bundle.putStringArrayList("Regional", Regional);
        bundle.putStringArrayList("National", National);

        tabLayout.addTab(tabLayout.newTab().setText("Local"));
        tabLayout.addTab(tabLayout.newTab().setText("Regional"));
        if (Double.parseDouble(td.getWeight()) <= 30000 && Double.parseDouble(td.getLength()) * Double.parseDouble(td.getBreadth()) * Double.parseDouble(td.getHeight()) / 5 <= 30000) {
            tabLayout.addTab(tabLayout.newTab().setText("National"));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
        AmazonSectionAdapter amazonSectionAdapter = new AmazonSectionAdapter(SavedData.this, getSupportFragmentManager(),
                tabLayout.getTabCount(), bundle);
        viewPager.setAdapter(amazonSectionAdapter);
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

    void ebayData(TitleDataResponse td) {
        result.setVisibility(View.VISIBLE);
        output text1 = new output("Selling Price", String.valueOf(td.getSellingPrice()));
        output text2 = new output("Purchase Price", String.valueOf(td.getProductPriceWithoutGst()));
        output text3 = new output("GST on product", String.valueOf(td.getGstOnProduct()));
        output text4 = new output("Inward Shipping", String.valueOf(td.getInwardShipping()));
        output text5 = new output("Packaging Expenses", String.valueOf(td.getPackagingExpense()));
        output text6 = new output("Labour", String.valueOf(td.getLabour()));
        output text7 = new output("Storage fees", String.valueOf(td.getStorageFee()));
        output text8 = new output("Commission", String.valueOf(td.getCommission()));
        output text9 = new output("Shipping", String.valueOf(td.getShipping()));
        output text10 = new output("Payment Gateway Charge", String.valueOf(td.getPaymentGatewayCharge()));
        output text11 = new output("Other Charges", String.valueOf(td.getOther()));
        output text12 = new output("Discount on Price", String.valueOf(td.getDiscountAmount()));
        output text13 = new output("Discount Percentage", String.valueOf(td.getDiscountPercent()));

        ArrayList<output> inputList = new ArrayList<>();
        inputList.add(text1);
        inputList.add(text2);
        inputList.add(text3);
        inputList.add(text4);
        inputList.add(text5);
        inputList.add(text6);
        inputList.add(text7);
        inputList.add(text8);
        inputList.add(text9);
        inputList.add(text10);
        inputList.add(text11);
        inputList.add(text12);
        inputList.add(text13);

        OutputListAdapter adapter = new OutputListAdapter(SavedData.this, R.layout.output_row, inputList);
        itemList.setAdapter(adapter);
        SavedData.setListViewHeightBasedOnChildren(itemList);

        output text14 = new output("Commission Fees", td.getCommissionFees());
        output text15 = new output("Shipping Fees", td.getShippingFees());
        output text16 = new output("Payment Gateway Fees", td.getPaymentGatewayFees());
        output text17 = new output("Commission Fees + Shipping Fees + Payment Gateway Fees", td.getCSP());
        output text18 = new output("GST On CSP", td.getGstOnCSP());
        output text19 = new output("Total Charges", td.getTotalCharges());
        output text20 = new output("Bank Settlement", td.getBankSettlement());
        output text21 = new output("Total GST Payable", td.getTotalGstPayable());
        output text22 = new output("GST Claim", td.getGstClaim());
        output text23 = new output("TCS", td.getTcs());
        output text24 = new output("GST Payable", td.getGstPayable());
        output text25 = new output("Profit", td.getProfit());
        output text26 = new output("Profit Percentage", td.getProfitPercentage());

        ArrayList<output> outputList = new ArrayList<>();
        outputList.add(text14);
        outputList.add(text15);
        outputList.add(text16);
        outputList.add(text17);
        outputList.add(text18);
        outputList.add(text19);
        outputList.add(text20);
        outputList.add(text21);
        outputList.add(text22);
        outputList.add(text23);
        outputList.add(text24);
        outputList.add(text25);
        outputList.add(text26);

        ArrayList<String> input = new ArrayList<>();
        input.add(td.getSellingPrice());
        input.add(td.getProductPriceWithoutGst());
        input.add(td.getGstOnProduct());
        input.add(td.getInwardShipping());
        input.add(td.getPackagingExpense());
        input.add(td.getLabour());
        input.add(td.getStorageFee());
        input.add(td.getCommission());
        input.add(td.getShipping());
        input.add(td.getPaymentGatewayCharge());
        input.add(td.getOther());
        input.add(td.getDiscountAmount());
        input.add(td.getDiscountPercent());

        bundle.putStringArrayList("input", input);

        OutputListAdapter adapterOutput = new OutputListAdapter(SavedData.this, R.layout.output_row, outputList);
        result.setAdapter(adapterOutput);
        SavedData.setListViewHeightBasedOnChildren(result);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight() + 4;
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
