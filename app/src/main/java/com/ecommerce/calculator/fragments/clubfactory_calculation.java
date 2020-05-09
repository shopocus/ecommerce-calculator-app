package com.ecommerce.calculator.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import com.ecommerce.calculator.activities.HomeScreen;
import com.ecommerce.calculator.adapter.SectionPagerAdapter;
import com.ecommerce.calculator.models.ClubFactoryCalculationResponse;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.models.progressButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;

public class clubfactory_calculation extends Fragment implements View.OnClickListener {

    private ImageButton details, expenses, discounts, save, sendEmail;
    private TextView pp, gst, transport, packaging, labour, storage, other, price, percentage, line1, line2, line3, line4, line5,
            line6, line7, rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9;
    private EditText num1, num2, num4, num5, num6, num7, num8, num9, num10, num11;
    LinearLayout linearLayout;

    Spinner num3, categories;
    String categoryFinal;
    ArrayList<String> list = new ArrayList<>();
    ListView itemList;
    CardView result_card;
    Double[] items = new Double[8] ;
    String[] gst_array = {"0", "5", "12", "18", "28"};
    String spinner_ans;
    private ProgressBar progressBar;
    private TextView textView;
    private String myText;
    RadioGroup radioGroup;
    RadioButton radioButton;
    SwitchCompat switchCompat;
    String status;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.clubfactory_calculation, container, false);

        itemList = view.findViewById(R.id.text_view_result);
        result_card = view.findViewById(R.id.result_card);
        linearLayout = view.findViewById(R.id.linearlayout);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        details = view.findViewById(R.id.details_dropdown);
        details.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (pp.getVisibility() == View.GONE) {
                    pp.setVisibility(View.VISIBLE);
                    rs1.setVisibility(View.VISIBLE);
                    num2.setVisibility(View.VISIBLE);
                    gst.setVisibility(View.VISIBLE);
                    rs2.setVisibility(View.VISIBLE);
                    num3.setVisibility(View.VISIBLE);
                    line1.setVisibility(View.VISIBLE);
                    details.setRotation(180);
                } else {
                    pp.setVisibility(View.GONE);
                    rs1.setVisibility(View.GONE);
                    num2.setVisibility(View.GONE);
                    gst.setVisibility(View.GONE);
                    rs2.setVisibility(View.GONE);
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
                    rs3.setVisibility(View.VISIBLE);
                    num4.setVisibility(View.VISIBLE);
                    packaging.setVisibility(View.VISIBLE);
                    rs4.setVisibility(View.VISIBLE);
                    num5.setVisibility(View.VISIBLE);
                    labour.setVisibility(View.VISIBLE);
                    rs5.setVisibility(View.VISIBLE);
                    num6.setVisibility(View.VISIBLE);
                    storage.setVisibility(View.VISIBLE);
                    rs6.setVisibility(View.VISIBLE);
                    num7.setVisibility(View.VISIBLE);
                    other.setVisibility(View.VISIBLE);
                    rs7.setVisibility(View.VISIBLE);
                    num8.setVisibility(View.VISIBLE);
                    line2.setVisibility(View.VISIBLE);
                    line3.setVisibility(View.VISIBLE);
                    line4.setVisibility(View.VISIBLE);
                    line5.setVisibility(View.VISIBLE);
                    expenses.setRotation(180);
                } else {
                    transport.setVisibility(View.GONE);
                    rs3.setVisibility(View.GONE);
                    num4.setVisibility(View.GONE);
                    packaging.setVisibility(View.GONE);
                    rs4.setVisibility(View.GONE);
                    num5.setVisibility(View.GONE);
                    labour.setVisibility(View.GONE);
                    rs5.setVisibility(View.GONE);
                    num6.setVisibility(View.GONE);
                    storage.setVisibility(View.GONE);
                    rs6.setVisibility(View.GONE);
                    num7.setVisibility(View.GONE);
                    other.setVisibility(View.GONE);
                    rs7.setVisibility(View.GONE);
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
                    rs8.setVisibility(View.VISIBLE);
                    num9.setVisibility(View.VISIBLE);
                    percentage.setVisibility(View.VISIBLE);
                    rs9.setVisibility(View.VISIBLE);
                    num10.setVisibility(View.VISIBLE);
                    line6.setVisibility(View.VISIBLE);
                    discounts.setRotation(180);
                } else {
                    price.setVisibility(View.GONE);
                    rs7.setVisibility(View.GONE);
                    num9.setVisibility(View.GONE);
                    percentage.setVisibility(View.GONE);
                    rs8.setVisibility(View.GONE);
                    num10.setVisibility(View.GONE);
                    line6.setVisibility(View.GONE);
                    discounts.setRotation(0);
                }
            }
        });

        View calculate = view.findViewById(R.id.calculate);
        calculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressButton progressButton = new progressButton(getActivity(), v);
                progressButton.ButtonActivated();
                InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                View focusedView = getActivity().getCurrentFocus();
                if (focusedView != null) {
                    inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                calculate();
            }
        });

        ImageButton reset = view.findViewById(R.id.reset);
        reset.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                num1.requestFocus();
                num1.setText("");
                categories.setSelection(0);
                num2.setText("");
                num3.setSelection(0);
                num4.setText("");
                num5.setText("");
                num6.setText("");
                num7.setText("");
                num8.setText("");
                num9.setText("");
                num10.setText("");
                num11.setText("");
                result_card.setVisibility(View.GONE);
            }
        });

        save = view.findViewById(R.id.save);
        save.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(getActivity());
                mydialog.setTitle("Title");

                final EditText title = new EditText(getActivity());
                title.setInputType(InputType.TYPE_CLASS_TEXT);
                title.setHint("Enter Title");
                title.requestFocus();
                title.setPadding(70,40,50,40);
                mydialog.setView(title);

                mydialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myText = title.getText().toString();
                        if (myText.equals("")) {
                            Toast.makeText(getContext(), "Enter the Title", Toast.LENGTH_SHORT).show();
                        } else {
                            save();
                        }
                    }
                });
                mydialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                mydialog.show();
            }
        });

        sendEmail = view.findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                share();
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setUpViewPager(viewPager);
        //tabLayout.setupWithViewPager(viewPager);
        //TabLayout tabLayout = findViewById(R.id.tab_layout);

    }

    private void setUpViewPager(ViewPager viewPager) {
//        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());
//        adapter.addFragment(new Local(), "Local");
//        adapter.addFragment(new Regional(), "Regional");
//        adapter.addFragment(new Metro(), "Metro");
//        adapter.addFragment(new RestOfIndia(), "Rest Of India");
//        adapter.addFragment(new Kerala(), "Kerala");

       // viewPager.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categories = view.findViewById(R.id.category);

        list = SharedPrefManager.getInstance(getActivity()).getList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_activated_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        categories.setAdapter(adapter);

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryFinal = list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        details = view.findViewById(R.id.details_dropdown);
        radioGroup = view.findViewById(R.id.radioGroup);
        switchCompat = view.findViewById(R.id.courier_switch);
        switchCompat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(switchCompat.isChecked()){
                    status = "delivery";
                }else{
                    status = "other";
                }
            }
        });
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
        rs1 = view.findViewById(R.id.rs1);
        rs2 = view.findViewById(R.id.rs2);
        rs3 = view.findViewById(R.id.rs3);
        rs4 = view.findViewById(R.id.rs4);
        rs5 = view.findViewById(R.id.rs5);
        rs6 = view.findViewById(R.id.rs6);
        rs7 = view.findViewById(R.id.rs7);
        rs8 = view.findViewById(R.id.rs8);
        rs9 = view.findViewById(R.id.rs9);
        expenses = view.findViewById(R.id.expenses_dropdown);
        discounts = view.findViewById(R.id.discounts_dropdown);
        price = view.findViewById(R.id.text_price);
        percentage = view.findViewById(R.id.text_percentage);
        num1 = view.findViewById(R.id.number1);
        num2 = view.findViewById(R.id.number2);

        num3 = view.findViewById(R.id.number3);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, gst_array);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        num3.setAdapter(adapter1);
        num3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_ans = gst_array[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        num4 = view.findViewById(R.id.number4);
        num5 = view.findViewById(R.id.number5);
        num6 = view.findViewById(R.id.number6);
        num7 = view.findViewById(R.id.number7);
        num8 = view.findViewById(R.id.number8);
        num9 = view.findViewById(R.id.number9);
        num10 = view.findViewById(R.id.number10);
        num11 = view.findViewById(R.id.number11);
        progressBar = view.findViewById(R.id.loader);
        textView = view.findViewById(R.id.calculate_textview);

        if(SharedPrefManager.getInstance(getActivity()).getFlag().equals("true")){
            int selection = adapter.getPosition(SharedPrefManager.getInstance(getActivity()).getData().getCategory());
            categories.setSelection(selection);
            num1.setText(SharedPrefManager.getInstance(getActivity()).getData().getProductPriceWithoutGst());
            num2.setText(SharedPrefManager.getInstance(getActivity()).getData().getProductPriceWithoutGst());
            int value = Integer.parseInt(SharedPrefManager.getInstance(getActivity()).getData().getGstOnProduct());
            switch (value){
                case 0:
                    num3.setSelection(0);
                    break;
                case 5:
                    num3.setSelection(1);
                    break;
                case 12:
                    num3.setSelection(2);
                    break;
                case 18:
                    num3.setSelection(3);
                    break;
                case 28:
                    num3.setSelection(4);
                    break;
            }
            num4.setText(SharedPrefManager.getInstance(getActivity()).getData().getInwardShipping());
            num5.setText(SharedPrefManager.getInstance(getActivity()).getData().getPackagingExpense());
            num6.setText(SharedPrefManager.getInstance(getActivity()).getData().getLabour());
            num7.setText(SharedPrefManager.getInstance(getActivity()).getData().getStorageFee());
            num8.setText(SharedPrefManager.getInstance(getActivity()).getData().getOther());
            num9.setText(SharedPrefManager.getInstance(getActivity()).getData().getDiscountAmount());
            num10.setText(SharedPrefManager.getInstance(getActivity()).getData().getDiscountPercent());

            String flag = "false";
            SharedPrefManager.getInstance(getActivity())
                    .saveFlag(flag);
        }
    }

    protected void calculate() {

        if (num1.getText().toString().isEmpty()) {
            num1.setError("Selling Price is required");
            num1.requestFocus();
            ButtonFinished();
            return;
        }

        if (num2.getText().toString().isEmpty()) {
            num2.setError("Purchase Price is required");
            num2.requestFocus();
            ButtonFinished();
            return;
        }

        if (num11.getText().toString().isEmpty()) {
            num11.setError("Weight is required");
            num11.requestFocus();
            ButtonFinished();
            return;
        }

        if(Double.parseDouble(num1.getText().toString()) <= 0){
            num1.setError("Selling Price not valid");
            num1.requestFocus();
            ButtonFinished();
            return;
        }

        if(Double.parseDouble(num2.getText().toString()) <= 0){
            num2.setError("Purchase Price is not valid");
            num2.requestFocus();
            ButtonFinished();
            return;
        }

        if(num4.getText().toString().isEmpty())
            num4.setText("0");
        if(num5.getText().toString().isEmpty())
            num5.setText("0");
        if(num6.getText().toString().isEmpty())
            num6.setText("0");
        if(num7.getText().toString().isEmpty())
            num7.setText("0");
        if(num8.getText().toString().isEmpty())
            num8.setText("0");
        if(num9.getText().toString().isEmpty())
            num9.setText("0");
        if(num10.getText().toString().isEmpty())
            num10.setText("0");

        if (Double.parseDouble(num9.getText().toString()) > 0 && Double.parseDouble(num10.getText().toString()) > 0) {
            num9.setError("One discount criteria valid at a time");
            num9.requestFocus();
            ButtonFinished();
            return;
        }

        String category = categoryFinal;
        double number1 = Double.parseDouble(num1.getText().toString());
        double number2 = Double.parseDouble(num2.getText().toString());
        double number3 = Double.parseDouble(spinner_ans);
        double number4 = Double.parseDouble(num4.getText().toString());
        double number5 = Double.parseDouble(num5.getText().toString());
        double number6 = Double.parseDouble(num6.getText().toString());
        double number7 = Double.parseDouble(num7.getText().toString());
        double number8 = Double.parseDouble(num8.getText().toString());
        double number9 = Double.parseDouble(num9.getText().toString());
        double number10 = Double.parseDouble(num10.getText().toString());
        double number11 = Double.parseDouble(num11.getText().toString());

        Call<ClubFactoryCalculationResponse> call = RetrofitClient
                .getInstance().getApi().clubFactoyrCalculation(category, number1, number3, number2, number4, number5, number6, number7, number8, number10, number9,
                        number11, checkButton(getView()), status);

        call.enqueue(new Callback<ClubFactoryCalculationResponse>() {
            @Override
            public void onResponse(Call<ClubFactoryCalculationResponse> call, Response<ClubFactoryCalculationResponse> response) {
                if(response.isSuccessful()) {
                    ClubFactoryCalculationResponse CalculateResponse = response.body();
                    ButtonFinished();

                    ArrayList<String> Local = new ArrayList<>();
                    Local.add(String.valueOf(CalculateResponse.getLocal().getBankSettlement()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getTotalCommision()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getTotalGstPayable()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getTcs()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getGstPayable()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getGstClaim()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getProfit()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getProfitPercentage()));

                    ArrayList<String> Regional = new ArrayList<>();
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getBankSettlement()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getTotalCommision()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getTotalGstPayable()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getTcs()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getGstPayable()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getGstClaim()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getProfit()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getProfitPercentage()));

                    ArrayList<String> Metro = new ArrayList<>();
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getBankSettlement()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getTotalCommision()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getTotalGstPayable()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getTcs()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getGstPayable()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getGstClaim()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getProfit()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getProfitPercentage()));

                    ArrayList<String> RestOfIndia = new ArrayList<>();
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getBankSettlement()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getTotalCommision()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getTotalGstPayable()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getTcs()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getGstPayable()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getGstClaim()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getProfit()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getProfitPercentage()));

                    ArrayList<String> Kerela = new ArrayList<>();
                    Kerela.add(String.valueOf(CalculateResponse.getKerela().getBankSettlement()));
                    Kerela.add(String.valueOf(CalculateResponse.getKerela().getTotalCommision()));
                    Kerela.add(String.valueOf(CalculateResponse.getKerela().getTotalGstPayable()));
                    Kerela.add(String.valueOf(CalculateResponse.getKerela().getTcs()));
                    Kerela.add(String.valueOf(CalculateResponse.getKerela().getGstPayable()));
                    Kerela.add(String.valueOf(CalculateResponse.getKerela().getGstClaim()));
                    Kerela.add(String.valueOf(CalculateResponse.getKerela().getProfit()));
                    Kerela.add(String.valueOf(CalculateResponse.getKerela().getProfitPercentage()));

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
                    SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getContext(), getChildFragmentManager(), tabLayout.getTabCount(), bundle);
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

                    save.setImageResource(R.drawable.ic_bookmark_border);
                    save.setEnabled(true);
                }else if(response.code() == 501){
                    Toast.makeText(getContext(), "Session Expire! Please Login Again", Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(getContext()).clear();
                    Intent intent_logout = new Intent(getContext(), HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_logout);
                }
            }
            @Override
            public void onFailure(Call<ClubFactoryCalculationResponse> call, Throwable t) {
                ButtonFinished();
                Toast.makeText(getContext(), "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void save(){
        String title = String.valueOf(myText);
        String category = categoryFinal;
        String sellingPrice = num1.getText().toString().trim();
        String gstOnProduct = spinner_ans;
        String productPriceWithoutGst = num2.getText().toString().trim();
        String inwardShipping = num4.getText().toString().trim();
        String packagingExpense = num5.getText().toString().trim();
        String labour = num6.getText().toString().trim();
        String storageFee = num7.getText().toString().trim();
        String other = num8.getText().toString().trim();
        String discountPercent = num10.getText().toString().trim();
        String discountAmount = num9.getText().toString().trim();
        String bankSettlement = String.valueOf(items[0]);
        String totalMeeshoCommision = String.valueOf(items[1]);
        String profit = String.valueOf(items[2]);
        String totalGstPayable = String.valueOf(items[3]);
        String tcs = String.valueOf(items[4]);
        String gstPayable = String.valueOf(items[5]);
        String gstClaim = String.valueOf(items[6]);
        String profitPercentage = String.valueOf(items[7]);

        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .savedclubFactory(title, category , sellingPrice, gstOnProduct, productPriceWithoutGst, inwardShipping, packagingExpense, labour, storageFee,
                        other, discountPercent, discountAmount, bankSettlement, totalMeeshoCommision, profit, totalGstPayable, tcs, gstPayable,
                        gstClaim, profitPercentage, "", "", "", "",""
                ,"","","","","","","",
                        "","","","","","","",
                        "","","","","","",
                        "","","","","","","",
                        "","","");

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if(response.isSuccessful()) {
                    MessageResponse dr = response.body();
                    if (dr.getMessage().equals("data_saved")) {
                        save.setImageResource(R.drawable.ic_bookmark);
                        save.setEnabled(false);
                        new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Successfully Saved")
                                .setConfirmText("Ok")
                                .setConfirmButtonBackgroundColor(getResources().getColor(R.color.colorPrimaryDark))
                                .show();
                    } else {
                        Toast.makeText(getContext(), "Title Name Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }else if(response.code() == 401){
                    Toast.makeText(getContext(), "Session Expire! Please Login Again", Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(getContext()).clear();
                    Intent intent_logout = new Intent(getContext(), HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_logout);
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void share(){
        String content = "INPUT" + "\n" +
                "Selling Price: " + num1.getText().toString().trim() + "\n" +
                "GST On Product: " + spinner_ans + "\n" +
                "Product Price Without GST: " + num2.getText().toString().trim() + "\n" +
                "Inward Shipping: " + num4.getText().toString().trim() + "\n" +
                "Packaging Expense: " + num5.getText().toString().trim() + "\n" +
                "Labour: " + num6.getText().toString().trim() + "\n" +
                "Storage Fee: " + num7.getText().toString().trim() + "\n" +
                "Other: " + num8.getText().toString().trim() + "\n" +
                "Discount Percent: " + num10.getText().toString().trim() + "\n" +
                "Discount Amount: " + num9.getText().toString().trim() + "\n\n" +
                "OUTPUT" + "\n" +
                "Bank Settlement: " + items[0] + "\n" +
                "Total Meesho Commision: " + items[1] + "\n" +
                "Profit: " + items[2] + "\n" +
                "Total GST Payable: " + items[3] + "\n" +
                "Tcs: " + items[4] + "\n" +
                "GST Payable: " + items[5] + "\n" +
                "GST Claim: " + items[6] + "\n" +
                "Profit Percentage: " + items[7];

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = content;
        String shareSub = "Your Bill";
        intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(intent, "Share using"));
    }

    void ButtonFinished(){
        progressBar.setVisibility(View.GONE);
        textView.setText("Calculate");
    }

    public String checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = v.findViewById(radioId);
        return radioButton.getText().toString();
    }

    @Override
    public void onClick(View v) {

    }
}