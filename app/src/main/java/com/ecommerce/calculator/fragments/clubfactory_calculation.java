package com.ecommerce.calculator.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;

import com.ecommerce.calculator.HeightWrappingViewPager;
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

    private ImageButton details, expenses, discounts, save, sendEmail, reset;
    private EditText sellingPrice, purchasePrice, inwardShipping, packagingExpenses, labour, storageFee, otherCharges, discountByPrice, discountByPercentage, weight;
    LinearLayout linearLayout, lengthBunch, breadthBunch, heightBunch, customerTypeBunch;

    CardView productDetailsCard, expensesCard, discountsCard;
    Spinner gstOnProduct, categories;
    String categoryFinal;
    ArrayList<String> list = new ArrayList<>();
    ListView itemList;
    CardView result_card;
    String[] gst_array = {"0", "5", "12", "18", "28"};
    String spinner_ans;
    private ProgressBar progressBar;
    private TextView textView, line2, line3, line4;
    private String myText;
    RadioGroup payMode, courier;
    RadioButton radioButton, radioButtonPostpaid, radioButtonDelivery, radioButtonPrepaid, radioButtonOther;
    String courierOption="delhivery",paymentOption="postpaid";
    HeightWrappingViewPager viewPager;
    TabLayout tabLayout;
    ArrayList<String> Local = new ArrayList<>();
    ArrayList<String> Regional = new ArrayList<>();
    ArrayList<String> Metro = new ArrayList<>();
    ArrayList<String> RestOfIndia = new ArrayList<>();
    ArrayList<String> Kerela = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.clubfactory_calculation, container, false);

        itemList = view.findViewById(R.id.text_view_result);
        result_card = view.findViewById(R.id.result_card);
        linearLayout = view.findViewById(R.id.linearlayout);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);

        details = view.findViewById(R.id.details_dropdown);
        details.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (productDetailsCard.getVisibility() == View.GONE) {
                    productDetailsCard.setVisibility(View.VISIBLE);
                    details.setRotation(180);
                } else {
                    productDetailsCard.setVisibility(View.GONE);
                    details.setRotation(0);
                }
            }
        });

        expenses = view.findViewById(R.id.expenses_dropdown);
        expenses.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expensesCard.getVisibility() == View.GONE) {
                    expensesCard.setVisibility(View.VISIBLE);
                    expenses.setRotation(180);
                } else {
                    expensesCard.setVisibility(View.GONE);
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
                if (discountsCard.getVisibility() == View.GONE) {
                    discountsCard.setVisibility(View.VISIBLE);
                    discounts.setRotation(180);
                } else {
                    discountsCard.setVisibility(View.GONE);
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

        payMode = view.findViewById(R.id.payMode);
        payMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = view.findViewById(checkedId);
                paymentOption = radioButton.getText().toString();
            }
        });

        courier = view.findViewById(R.id.courier);
        courier.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = view.findViewById(checkedId);
                courierOption = radioButton.getText().toString();
            }
        });

        reset = view.findViewById(R.id.reset);
        reset.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sellingPrice.requestFocus();
                sellingPrice.setText("");
                categories.setSelection(0);
                purchasePrice.setText("");
                gstOnProduct.setSelection(0);
                inwardShipping.setText("");
                packagingExpenses.setText("");
                labour.setText("");
                storageFee.setText("");
                otherCharges.setText("");
                discountByPrice.setText("");
                discountByPercentage.setText("");
                weight.setText("");
                radioButtonPostpaid.setChecked(true);
                radioButtonDelivery.setChecked(true);
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
        radioButtonPostpaid = view.findViewById(R.id.radio_postpaid);
        radioButtonDelivery = view.findViewById(R.id.radio_delhivery);
        radioButtonPrepaid = view.findViewById(R.id.radio_prepaid);
        radioButtonOther = view.findViewById(R.id.radio_other);
        productDetailsCard = view.findViewById(R.id.productDetailsCard);
        expensesCard = view.findViewById(R.id.expensesCard);
        discountsCard = view.findViewById(R.id.discountsCard);
        expenses = view.findViewById(R.id.expenses_dropdown);
        discounts = view.findViewById(R.id.discounts_dropdown);
        lengthBunch = view.findViewById(R.id.lengthBunch);
        breadthBunch = view.findViewById(R.id.breadthBunch);
        heightBunch = view.findViewById(R.id.heightBunch);
        customerTypeBunch = view.findViewById(R.id.customerTypeBunch);
        line2 = view.findViewById(R.id.line2);
        line3 = view.findViewById(R.id.line3);
        line4 = view.findViewById(R.id.line4);
        lengthBunch.setVisibility(View.GONE);
        breadthBunch.setVisibility(View.GONE);
        heightBunch.setVisibility(View.GONE);
        customerTypeBunch.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);
        line3.setVisibility(View.GONE);
        line4.setVisibility(View.GONE);

        sellingPrice = view.findViewById(R.id.sellingPrice);
        purchasePrice = view.findViewById(R.id.purchasePrice);

        gstOnProduct = view.findViewById(R.id.gst);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, gst_array);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        gstOnProduct.setAdapter(adapter1);
        gstOnProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_ans = gst_array[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        inwardShipping = view.findViewById(R.id.inwardShipping);
        packagingExpenses = view.findViewById(R.id.packagingExpenses);
        labour = view.findViewById(R.id.labour);
        storageFee = view.findViewById(R.id.storageFee);
        otherCharges = view.findViewById(R.id.otherCharges);
        discountByPrice = view.findViewById(R.id.discountByPrice);
        discountByPercentage = view.findViewById(R.id.discountByPercentage);
        weight = view.findViewById(R.id.weight);
        progressBar = view.findViewById(R.id.loader);
        textView = view.findViewById(R.id.calculate_textview);

        if(SharedPrefManager.getInstance(getActivity()).getFlag().equals("true")){
            Bundle bundle = this.getArguments();
            ArrayList<String> bundle_input = bundle.getStringArrayList("input");
            int selection = adapter.getPosition(bundle_input.get(0));
            categories.setSelection(selection);
            sellingPrice.setText(bundle_input.get(1));
            purchasePrice.setText(bundle_input.get(2));
            int value = Integer.parseInt(bundle_input.get(3));
            switch (value){
                case 0:
                    gstOnProduct.setSelection(0);
                    break;
                case 5:
                    gstOnProduct.setSelection(1);
                    break;
                case 12:
                    gstOnProduct.setSelection(2);
                    break;
                case 18:
                    gstOnProduct.setSelection(3);
                    break;
                case 28:
                    gstOnProduct.setSelection(4);
                    break;
            }
            weight.setText(bundle_input.get(4));
            if(bundle_input.get(6).equals("prepaid")){
                radioButtonPrepaid.setChecked(true);
            }else{
                radioButtonPostpaid.setChecked(true);
            }
            if(bundle_input.get(5).equals("delhivery")){
                radioButtonDelivery.setChecked(true);
            }else{
                radioButtonOther.setChecked(true);
            }
            inwardShipping.setText(bundle_input.get(7));
            packagingExpenses.setText(bundle_input.get(8));
            labour.setText(bundle_input.get(9));
            storageFee.setText(bundle_input.get(10));
            otherCharges.setText(bundle_input.get(11));
            discountByPrice.setText(bundle_input.get(12));
            discountByPercentage.setText(bundle_input.get(13));

            String flag = "false";
            SharedPrefManager.getInstance(getActivity())
                    .saveFlag(flag);
        }
    }

    protected void calculate() {

        if (sellingPrice.getText().toString().isEmpty()) {
            sellingPrice.setError("Selling Price is required");
            sellingPrice.requestFocus();
            ButtonFinished();
            return;
        }

        if (purchasePrice.getText().toString().isEmpty()) {
            purchasePrice.setError("Purchase Price is required");
            purchasePrice.requestFocus();
            ButtonFinished();
            return;
        }

        if (weight.getText().toString().isEmpty()) {
            weight.setError("Weight is required");
            weight.requestFocus();
            ButtonFinished();
            return;
        }

        if(Double.parseDouble(sellingPrice.getText().toString()) <= 0){
            sellingPrice.setError("Selling Price not valid");
            sellingPrice.requestFocus();
            ButtonFinished();
            return;
        }

        if(Double.parseDouble(purchasePrice.getText().toString()) <= 0){
            purchasePrice.setError("Purchase Price is not valid");
            purchasePrice.requestFocus();
            ButtonFinished();
            return;
        }

        if(inwardShipping.getText().toString().isEmpty())
            inwardShipping.setText("0");
        if(packagingExpenses.getText().toString().isEmpty())
            packagingExpenses.setText("0");
        if(labour.getText().toString().isEmpty())
            labour.setText("0");
        if(storageFee.getText().toString().isEmpty())
            storageFee.setText("0");
        if(otherCharges.getText().toString().isEmpty())
            otherCharges.setText("0");
        if(discountByPrice.getText().toString().isEmpty())
            discountByPrice.setText("0");
        if(discountByPercentage.getText().toString().isEmpty())
            discountByPercentage.setText("0");

        if (Double.parseDouble(discountByPrice.getText().toString()) > 0 && Double.parseDouble(discountByPercentage.getText().toString()) > 0) {
            discountByPrice.setError("One discount criteria valid at a time");
            discountByPrice.requestFocus();
            ButtonFinished();
            return;
        }

        String category = categoryFinal;
        double number1 = Double.parseDouble(sellingPrice.getText().toString());
        double number2 = Double.parseDouble(purchasePrice.getText().toString());
        double number3 = Double.parseDouble(spinner_ans);
        double number4 = Double.parseDouble(inwardShipping.getText().toString());
        double number5 = Double.parseDouble(packagingExpenses.getText().toString());
        double number6 = Double.parseDouble(labour.getText().toString());
        double number7 = Double.parseDouble(storageFee.getText().toString());
        double number8 = Double.parseDouble(otherCharges.getText().toString());
        double number9 = Double.parseDouble(discountByPrice.getText().toString());
        double number10 = Double.parseDouble(discountByPercentage.getText().toString());
        double number11 = Double.parseDouble(weight.getText().toString());

        Call<ClubFactoryCalculationResponse> call = RetrofitClient
                .getInstance().getApi().clubFactoyrCalculation(category, number1, number3, number2, number4, number5, number6, number7, number8, number10, number9,
                        number11, paymentOption, courierOption);

        call.enqueue(new Callback<ClubFactoryCalculationResponse>() {
            @Override
            public void onResponse(Call<ClubFactoryCalculationResponse> call, Response<ClubFactoryCalculationResponse> response) {
                if(response.isSuccessful()) {
                    ClubFactoryCalculationResponse CalculateResponse = response.body();
                    ButtonFinished();

                    result_card.setVisibility(View.VISIBLE);
                    tabLayout.removeAllTabs();

                    Local.add(String.valueOf(CalculateResponse.getLocal().getBankSettlement()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getTotalCommision()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getTotalGstPayable()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getTcs()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getGstPayable()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getGstClaim()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getProfit()));
                    Local.add(String.valueOf(CalculateResponse.getLocal().getProfitPercentage()));

                    Regional.add(String.valueOf(CalculateResponse.getRegional().getBankSettlement()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getTotalCommision()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getTotalGstPayable()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getTcs()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getGstPayable()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getGstClaim()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getProfit()));
                    Regional.add(String.valueOf(CalculateResponse.getRegional().getProfitPercentage()));

                    Metro.add(String.valueOf(CalculateResponse.getMetro().getBankSettlement()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getTotalCommision()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getTotalGstPayable()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getTcs()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getGstPayable()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getGstClaim()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getProfit()));
                    Metro.add(String.valueOf(CalculateResponse.getMetro().getProfitPercentage()));

                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getBankSettlement()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getTotalCommision()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getTotalGstPayable()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getTcs()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getGstPayable()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getGstClaim()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getProfit()));
                    RestOfIndia.add(String.valueOf(CalculateResponse.getRestOfIndia().getProfitPercentage()));

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
        String sellingPrice = this.sellingPrice.getText().toString().trim();
        String gstOnProduct = spinner_ans;
        String productPriceWithoutGst = purchasePrice.getText().toString().trim();
        String inwardShipping = this.inwardShipping.getText().toString().trim();
        String packagingExpense = packagingExpenses.getText().toString().trim();
        String labour = this.labour.getText().toString().trim();
        String storageFee = this.storageFee.getText().toString().trim();
        String other = otherCharges.getText().toString().trim();
        String discountPercent = discountByPercentage.getText().toString().trim();
        String discountAmount = discountByPrice.getText().toString().trim();
        String weight = this.weight.getText().toString().trim();

        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .savedclubFactory(title, category, sellingPrice, gstOnProduct, productPriceWithoutGst, inwardShipping, packagingExpense, labour,
                        storageFee, other, discountPercent, discountAmount, weight, paymentOption, courierOption, Local.get(0), Local.get(1),
                        Local.get(2), Local.get(3), Local.get(4), Local.get(5), Local.get(6), Local.get(7), Regional.get(0), Regional.get(1),
                        Regional.get(2), Regional.get(3), Regional.get(4), Regional.get(5), Regional.get(6), Regional.get(7), Metro.get(0),
                        Metro.get(1), Metro.get(2), Metro.get(3), Metro.get(4), Metro.get(5), Metro.get(6), Metro.get(7), RestOfIndia.get(0),
                        RestOfIndia.get(1), RestOfIndia.get(2), RestOfIndia.get(3), RestOfIndia.get(4), RestOfIndia.get(5), RestOfIndia.get(6),
                        RestOfIndia.get(7), Kerela.get(0), Kerela.get(1), Kerela.get(2), Kerela.get(3), Kerela.get(4), Kerela.get(5), Kerela.get(6),
                        Kerela.get(7));

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
        String content = "INPUT" + "\n\n" +
                "Category: " + categoryFinal + "\n" +
                "Selling Price: " + sellingPrice.getText().toString().trim() + "\n" +
                "GST On Product: " + spinner_ans + "\n" +
                "Product Price Without GST: " + purchasePrice.getText().toString().trim() + "\n" +
                "Weight: " + weight.getText().toString().trim() + "\n" +
                "Courier" + courierOption + "\n" +
                "Payment Mode" + paymentOption + "\n" +
                "Inward Shipping: " + inwardShipping.getText().toString().trim() + "\n" +
                "Packaging Expense: " + packagingExpenses.getText().toString().trim() + "\n" +
                "Labour: " + labour.getText().toString().trim() + "\n" +
                "Storage Fee: " + storageFee.getText().toString().trim() + "\n" +
                "Other: " + otherCharges.getText().toString().trim() + "\n" +
                "Discount Percent: " + discountByPercentage.getText().toString().trim() + "\n" +
                "Discount Amount: " + discountByPrice.getText().toString().trim() + "\n\n" +
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
                "Bank Settlement: " + Regional.get(0) + "\n" +
                "Total Commision: " + Regional.get(1) + "\n" +
                "Total GST Payable: " + Regional.get(2) + "\n" +
                "Tcs: " + Regional.get(3) + "\n" +
                "GST Payable: " + Regional.get(4) + "\n" +
                "GST Claim: " + Regional.get(5) + "\n" +
                "Profit: " + Regional.get(6) + "\n" +
                "Profit Percentage: " + Regional.get(7) + "\n" +
                "Metro" + "\n" +
                "Bank Settlement: " + Metro.get(0) + "\n" +
                "Total Commision: " + Metro.get(1) + "\n" +
                "Total GST Payable: " + Metro.get(2) + "\n" +
                "Tcs: " + Metro.get(3) + "\n" +
                "GST Payable: " + Metro.get(4) + "\n" +
                "GST Claim: " + Metro.get(5) + "\n" +
                "Profit: " + Metro.get(6) + "\n" +
                "Profit Percentage: " + Metro.get(7) + "\n" +
                "Rest Of India" + "\n" +
                "Bank Settlement: " + RestOfIndia.get(0) + "\n" +
                "Total Commision: " + RestOfIndia.get(1) + "\n" +
                "Total GST Payable: " + RestOfIndia.get(2) + "\n" +
                "Tcs: " + RestOfIndia.get(3) + "\n" +
                "GST Payable: " + RestOfIndia.get(4) + "\n" +
                "GST Claim: " + RestOfIndia.get(5) + "\n" +
                "Profit: " + RestOfIndia.get(6) + "\n" +
                "Profit Percentage: " + RestOfIndia.get(7) + "\n" +
                "Kerala" + "\n" +
                "Bank Settlement: " + Kerela.get(0) + "\n" +
                "Total Commision: " + Kerela.get(1) + "\n" +
                "Total GST Payable: " + Kerela.get(2) + "\n" +
                "Tcs: " + Kerela.get(3) + "\n" +
                "GST Payable: " + Kerela.get(4) + "\n" +
                "GST Claim: " + Kerela.get(5) + "\n" +
                "Profit: " + Kerela.get(6) + "\n" +
                "Profit Percentage: " + Kerela.get(7);

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