package com.ecommerce.calculator.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.activities.HomeScreen;
import com.ecommerce.calculator.adapter.FlipkartSectionAdapter;
import com.ecommerce.calculator.adapter.SectionPagerAdapter;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.ClubFactoryCalculationResponse;
import com.ecommerce.calculator.models.FlipkartCalculationResponse;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.models.progressButton;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class flipkart_calculation extends Fragment implements View.OnClickListener  {

    private ImageButton details, expenses, discounts, save, sendEmail;
    private TextView pp, gst, weight, courier, payment, transport, packaging, labour, storage, other, price, percentage, line1, line2, line3, line4, line5,
            line6, line8, line9, line10, rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,gm1;
    private EditText num1, num2, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13,num14;
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
    RadioGroup radioGroup, radioGroupCourier;
    RadioButton radioButton, radioButtonPostpaid, radioButtonDelivery, radioButtonPrepaid, radioButtonOther;
    String customerTypeOption,paymentOption;
    ViewPager viewPager;
    TabLayout tabLayout;
    ArrayList<String> Local = new ArrayList<>();
    ArrayList<String> Zonal = new ArrayList<>();
    ArrayList<String> National = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.flipkart_calculation, container, false);
        itemList = view.findViewById(R.id.text_view_result);
        result_card = view.findViewById(R.id.result_card);
        linearLayout = view.findViewById(R.id.linearlayout);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);

        details = view.findViewById(R.id.details_dropdown);
        details.setOnClickListener(new View.OnClickListener()
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
                    weight.setVisibility(View.VISIBLE);
                    gm1.setVisibility(View.VISIBLE);
                    courier.setVisibility(View.VISIBLE);
                    line8.setVisibility(View.VISIBLE);
                    line9.setVisibility(View.VISIBLE);
                    line10.setVisibility(View.VISIBLE);
                    payment.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    radioGroupCourier.setVisibility(View.VISIBLE);
                    num11.setVisibility(View.VISIBLE);
                    details.setRotation(180);
                } else {
                    pp.setVisibility(View.GONE);
                    rs1.setVisibility(View.GONE);
                    num2.setVisibility(View.GONE);
                    gst.setVisibility(View.GONE);
                    rs2.setVisibility(View.GONE);
                    num3.setVisibility(View.GONE);
                    line1.setVisibility(View.GONE);
                    weight.setVisibility(View.GONE);
                    gm1.setVisibility(View.GONE);
                    courier.setVisibility(View.GONE);
                    line8.setVisibility(View.GONE);
                    line9.setVisibility(View.GONE);
                    line10.setVisibility(View.GONE);
                    payment.setVisibility(View.GONE);
                    radioGroup.setVisibility(View.GONE);
                    radioGroupCourier.setVisibility(View.GONE);
                    num11.setVisibility(View.GONE);
                    details.setRotation(0);
                }
            }
        });

        expenses = view.findViewById(R.id.expenses_dropdown);
        expenses.setOnClickListener(new View.OnClickListener() {
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
        discounts.setOnClickListener(new View.OnClickListener()
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
        calculate.setOnClickListener(new View.OnClickListener()
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

        radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = view.findViewById(checkedId);
                paymentOption = radioButton.getText().toString();
            }
        });

        radioGroupCourier = view.findViewById(R.id.radioGroupCourier);
        radioGroupCourier.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = view.findViewById(checkedId);
                customerTypeOption = radioButton.getText().toString();
            }
        });

        ImageButton reset = view.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener()
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
                radioButtonPostpaid.setChecked(true);
                radioButtonDelivery.setChecked(true);
                result_card.setVisibility(View.GONE);
            }
        });

        save = view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener()
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
                           // save();
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
        sendEmail.setOnClickListener(new View.OnClickListener()
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
        pp = view.findViewById(R.id.text_pp);
        radioButtonPostpaid = view.findViewById(R.id.radio_postpaid);
        radioButtonDelivery = view.findViewById(R.id.radio_delivery);
        radioButtonPrepaid = view.findViewById(R.id.radio_prepaid);
        radioButtonOther = view.findViewById(R.id.radio_other);
        gst = view.findViewById(R.id.text_gst);
        weight = view.findViewById(R.id.text_weight);
        courier = view.findViewById(R.id.text_courier);
        payment = view.findViewById(R.id.text_payment);
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
        line8 = view.findViewById(R.id.line8);
        line9 = view.findViewById(R.id.line9);
        line10 = view.findViewById(R.id.line10);
        gm1 = view.findViewById(R.id.gm1);
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
        num12 = view.findViewById(R.id.number12);
        num13 = view.findViewById(R.id.number13);
        num14 = view.findViewById(R.id.number14);
        progressBar = view.findViewById(R.id.loader);
        textView = view.findViewById(R.id.calculate_textview);

        if(SharedPrefManager.getInstance(getActivity()).getFlag().equals("true")){
            Bundle bundle = this.getArguments();
            ArrayList<String> bundle_input = bundle.getStringArrayList("input");
            int selection = adapter.getPosition(bundle_input.get(0));
            categories.setSelection(selection);
            num1.setText(bundle_input.get(1));
            num2.setText(bundle_input.get(2));
            int value = Integer.parseInt(bundle_input.get(3));
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
            num11.setText(bundle_input.get(4));
            if(bundle_input.get(5) == "prepaid"){
                radioButtonPrepaid.setChecked(true);
            }else{
                radioButtonPostpaid.setChecked(true);
            }
            if(bundle_input.get(6) == "delhivery"){
                radioButtonDelivery.setChecked(true);
            }else{
                radioButtonOther.setChecked(true);
            }
            num4.setText(bundle_input.get(7));
            num5.setText(bundle_input.get(8));
            num6.setText(bundle_input.get(9));
            num7.setText(bundle_input.get(10));
            num8.setText(bundle_input.get(11));
            num9.setText(bundle_input.get(12));
            num10.setText(bundle_input.get(13));

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
        double number12 = Double.parseDouble(num12.getText().toString());
        double number13 = Double.parseDouble(num13.getText().toString());
        double number14 = Double.parseDouble(num14.getText().toString());

        Call<FlipkartCalculationResponse> call = RetrofitClient
                .getInstance().getApi().flipkartCalculation(category, number1, number3, number2, number4, number5, number6, number7, number8, number10, number9,
                        number11, number12, number13, number14, paymentOption, customerTypeOption);

        call.enqueue(new Callback<FlipkartCalculationResponse>() {
            @Override
            public void onResponse(Call<FlipkartCalculationResponse> call, Response<FlipkartCalculationResponse> response) {
                if(response.isSuccessful()) {
                    FlipkartCalculationResponse CalculateResponse = response.body();
                    ButtonFinished();

                    result_card.setVisibility(View.VISIBLE);
                    tabLayout.removeAllTabs();

                    Local.add(String.valueOf(CalculateResponse.getLocal().getBankSettlement()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getTotalGstPayable()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getTcs()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getGstPayable()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getProfit()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getProfitPercentage()));

                    Zonal.add(String.valueOf(CalculateResponse.getRegional().getBankSettlement()));
                    Zonal.add(String.valueOf(CalculateResponse.getRegional().getTotalGstPayable()));
                    Zonal.add(String.valueOf(CalculateResponse.getRegional().getTcs()));
                    Zonal.add(String.valueOf(CalculateResponse.getRegional().getGstPayable()));
                    Zonal.add(String.valueOf(CalculateResponse.getRegional().getProfit()));
                    Zonal.add(String.valueOf(CalculateResponse.getRegional().getProfitPercentage()));

                    National.add(String.valueOf(CalculateResponse.getMetro().getBankSettlement()));
                    National.add(String.valueOf(CalculateResponse.getMetro().getTotalGstPayable()));
                    National.add(String.valueOf(CalculateResponse.getMetro().getTcs()));
                    National.add(String.valueOf(CalculateResponse.getMetro().getGstPayable()));
                    National.add(String.valueOf(CalculateResponse.getMetro().getProfit()));
                    National.add(String.valueOf(CalculateResponse.getMetro().getProfitPercentage()));

                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("Local", Local);
                    bundle.putStringArrayList("Zonal", Zonal);
                    bundle.putStringArrayList("National", National);

                    tabLayout.addTab(tabLayout.newTab().setText("Local"));
                    tabLayout.addTab(tabLayout.newTab().setText("Zonal"));
                    tabLayout.addTab(tabLayout.newTab().setText("National"));
                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
                    FlipkartSectionAdapter flipkartSectionAdapter = new FlipkartSectionAdapter(getContext(), getChildFragmentManager(), tabLayout.getTabCount(), bundle);
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
            public void onFailure(Call<FlipkartCalculationResponse> call, Throwable t) {
                ButtonFinished();
                Toast.makeText(getContext(), "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void save(){
//        String title = String.valueOf(myText);
//        String category = categoryFinal;
//        String sellingPrice = num1.getText().toString().trim();
//        String gstOnProduct = spinner_ans;
//        String productPriceWithoutGst = num2.getText().toString().trim();
//        String inwardShipping = num4.getText().toString().trim();
//        String packagingExpense = num5.getText().toString().trim();
//        String labour = num6.getText().toString().trim();
//        String storageFee = num7.getText().toString().trim();
//        String other = num8.getText().toString().trim();
//        String discountPercent = num10.getText().toString().trim();
//        String discountAmount = num9.getText().toString().trim();
//        String weight = num11.getText().toString().trim();
//
//        Call<MessageResponse> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .savedclubFactory(title, category, sellingPrice, gstOnProduct, productPriceWithoutGst, inwardShipping, packagingExpense, labour,
//                        storageFee, other, discountPercent, discountAmount, weight, paymentOption, customerTypeOption, Local.get(0), Local.get(1),
//                        Local.get(2), Local.get(3), Local.get(4), Local.get(5), Local.get(6), Local.get(7), Zonal.get(0), Zonal.get(1),
//                        Zonal.get(2), Zonal.get(3), Zonal.get(4), Zonal.get(5), Zonal.get(6), Zonal.get(7), National.get(0),
//                        National.get(1), National.get(2), National.get(3), National.get(4), National.get(5), National.get(6), National.get(7));
//
//        call.enqueue(new Callback<MessageResponse>() {
//            @Override
//            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
//                if(response.isSuccessful()) {
//                    MessageResponse dr = response.body();
//                    if (dr.getMessage().equals("data_saved")) {
//                        save.setImageResource(R.drawable.ic_bookmark);
//                        save.setEnabled(false);
//                        new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
//                                .setTitleText("Successfully Saved")
//                                .setConfirmText("Ok")
//                                .setConfirmButtonBackgroundColor(getResources().getColor(R.color.colorPrimaryDark))
//                                .show();
//                    } else {
//                        Toast.makeText(getContext(), "Title Name Already Exists", Toast.LENGTH_SHORT).show();
//                    }
//                }else if(response.code() == 401){
//                    Toast.makeText(getContext(), "Session Expire! Please Login Again", Toast.LENGTH_SHORT).show();
//                    SharedPrefManager.getInstance(getContext()).clear();
//                    Intent intent_logout = new Intent(getContext(), HomeScreen.class);
//                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent_logout);
//                }
//            }
//            @Override
//            public void onFailure(Call<MessageResponse> call, Throwable t) {
//                Toast.makeText(getContext(), "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void share(){
        String content = "INPUT" + "\n\n" +
                "Category: " + categoryFinal + "\n" +
                "Selling Price: " + num1.getText().toString().trim() + "\n" +
                "GST On Product: " + spinner_ans + "\n" +
                "Product Price Without GST: " + num2.getText().toString().trim() + "\n" +
                "Weight: " + num11.getText().toString().trim() + "\n" +
                "Courier" + customerTypeOption + "\n" +
                "Payment Mode" + paymentOption + "\n" +
                "Inward Shipping: " + num4.getText().toString().trim() + "\n" +
                "Packaging Expense: " + num5.getText().toString().trim() + "\n" +
                "Labour: " + num6.getText().toString().trim() + "\n" +
                "Storage Fee: " + num7.getText().toString().trim() + "\n" +
                "Other: " + num8.getText().toString().trim() + "\n" +
                "Discount Percent: " + num10.getText().toString().trim() + "\n" +
                "Discount Amount: " + num9.getText().toString().trim() + "\n\n" +
                "OUTPUT" + "\n\n" +
                "Local" + "\n" +
                "Bank Settlement: " + Local.get(0) + "\n" +
                "Total Commision: " + Local.get(1) + "\n" +
                "Total GST Payable: " + Local.get(2) + "\n" +
                "Tcs: " + Local.get(3) + "\n" +
                "GST Payable: " + Local.get(4) + "\n" +
                "GST Claim: " + Local.get(5) + "\n" +
                "Profit: " + Local.get(6) + "\n" +
                "Profit Percentage: " + Local.get(7) + "\n" +
                "Regional" + "\n" +
                "Bank Settlement: " + Zonal.get(0) + "\n" +
                "Total Commision: " + Zonal.get(1) + "\n" +
                "Total GST Payable: " + Zonal.get(2) + "\n" +
                "Tcs: " + Zonal.get(3) + "\n" +
                "GST Payable: " + Zonal.get(4) + "\n" +
                "GST Claim: " + Zonal.get(5) + "\n" +
                "Profit: " + Zonal.get(6) + "\n" +
                "Profit Percentage: " + Zonal.get(7) + "\n" +
                "Metro" + "\n" +
                "Bank Settlement: " + National.get(0) + "\n" +
                "Total Commision: " + National.get(1) + "\n" +
                "Total GST Payable: " + National.get(2) + "\n" +
                "Tcs: " + National.get(3) + "\n" +
                "GST Payable: " + National.get(4) + "\n" +
                "GST Claim: " + National.get(5) + "\n" +
                "Profit: " + National.get(6) + "\n" +
                "Profit Percentage: " + National.get(7) + "\n" +
                "Rest Of India" + "\n";

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

        @Override
    public void onClick(View v) {

    }
}
