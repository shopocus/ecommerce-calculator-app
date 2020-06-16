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

import com.ecommerce.calculator.HeightWrappingViewPager;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.activities.HomeScreen;
import com.ecommerce.calculator.adapter.FlipkartSectionAdapter;
import com.ecommerce.calculator.api.RetrofitClient;
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

    private ImageButton details, expenses, discounts, save, sendEmail, reset;
    private EditText sellingPrice, purchasePrice, inwardShipping, packagingExpenses, labour, storageFee, otherCharges, discountByPrice, discountByPercentage, weight, length, breadth, height;
    LinearLayout linearLayout, courierBunch;

    CardView productDetailsCard, expensesCard, discountsCard;
    Spinner gstOnProduct, categories;
    String categoryFinal;
    ArrayList<String> list = new ArrayList<>();
    ListView itemList;
    CardView result_card;
    String[] gst_array = {"0", "5", "12", "18", "28"};
    String spinner_ans;
    private ProgressBar progressBar;
    private TextView textView;
    private String myText;
    RadioGroup payMode, customerType;
    RadioButton radioButton, radioButtonPostpaid, radioButtonBronze, radioButtonPrepaid, radioButtonSilver, radioButtonGold;
    String customerTypeOption="bronze",paymentOption="postpaid";
    HeightWrappingViewPager viewPager;
    TabLayout tabLayout;
    ArrayList<String> Local = new ArrayList<>();
    ArrayList<String> Zonal = new ArrayList<>();
    ArrayList<String> National = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.amazon_calculation, container, false);

        details = view.findViewById(R.id.details_dropdown);
        details.setOnClickListener(new View.OnClickListener()
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
        expenses.setOnClickListener(new View.OnClickListener() {
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
        discounts.setOnClickListener(new View.OnClickListener()
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

        payMode = view.findViewById(R.id.payMode);
        payMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = view.findViewById(checkedId);
                paymentOption = radioButton.getText().toString();
            }
        });

        customerType = view.findViewById(R.id.easyShipmentType);
        customerType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = view.findViewById(checkedId);
                customerTypeOption = radioButton.getText().toString();
            }
        });

        reset = view.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener()
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
                length.setText("");
                breadth.setText("");
                height.setText("");
                radioButtonPostpaid.setChecked(true);
                radioButtonBronze.setChecked(true);
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

        itemList = view.findViewById(R.id.text_view_result);
        result_card = view.findViewById(R.id.result_card);
        linearLayout = view.findViewById(R.id.linearlayout);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);
        productDetailsCard = view.findViewById(R.id.productDetailsCard);
        expensesCard = view.findViewById(R.id.expensesCard);
        discountsCard = view.findViewById(R.id.discountsCard);
        details = view.findViewById(R.id.details_dropdown);
        radioButtonPostpaid = view.findViewById(R.id.radio_postpaid);
        radioButtonBronze = view.findViewById(R.id.radio_bronze);
        radioButtonPrepaid = view.findViewById(R.id.radio_prepaid);
        radioButtonSilver = view.findViewById(R.id.radio_silver);
        radioButtonGold = view.findViewById(R.id.radio_gold);
        expenses = view.findViewById(R.id.expenses_dropdown);
        discounts = view.findViewById(R.id.discounts_dropdown);
        courierBunch = view.findViewById(R.id.courierBunch);
        courierBunch.setVisibility(View.GONE);

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
        length = view.findViewById(R.id.length);
        breadth = view.findViewById(R.id.breadth);
        height = view.findViewById(R.id.height);
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
            length.setText(bundle_input.get(5));
            breadth.setText(bundle_input.get(6));
            height.setText(bundle_input.get(7));
            if(bundle_input.get(9).equals("prepaid")){
                radioButtonPrepaid.setChecked(true);
            }else{
                radioButtonPostpaid.setChecked(true);
            }
            if(bundle_input.get(8).equals("bronze")){
                radioButtonBronze.setChecked(true);
            }else if(bundle_input.get(8).equals("silver")){
                radioButtonSilver.setChecked(true);
            }else{
                radioButtonGold.setChecked(true);
            }
            inwardShipping.setText(bundle_input.get(10));
            packagingExpenses.setText(bundle_input.get(11));
            labour.setText(bundle_input.get(12));
            storageFee.setText(bundle_input.get(13));
            otherCharges.setText(bundle_input.get(14));
            discountByPrice.setText(bundle_input.get(15));
            discountByPercentage.setText(bundle_input.get(16));

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

        if (weight.getText().toString().isEmpty()) {
            weight.setError("Weight is required");
            weight.requestFocus();
            ButtonFinished();
            return;
        }

        if (length.getText().toString().isEmpty()) {
            length.setError("Length is required");
            length.requestFocus();
            ButtonFinished();
            return;
        }

        if (breadth.getText().toString().isEmpty()) {
            breadth.setError("Breadth is required");
            breadth.requestFocus();
            ButtonFinished();
            return;
        }

        if (height.getText().toString().isEmpty()) {
            height.setError("Height is required");
            height.requestFocus();
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
        double number12 = Double.parseDouble(length.getText().toString());
        double number13 = Double.parseDouble(breadth.getText().toString());
        double number14 = Double.parseDouble(height.getText().toString());

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

                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getCommissionFees()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getFixedFees()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getCollectionFees()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getShippingFees()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getCFCS()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getGstOnCFCS()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getTotalCharges()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getBankSettlement()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getTotalGstPayable()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getTcs()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getGstPayable()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getProfit()));
                    Local.add(String.valueOf(CalculateResponse.getFlipkartLocal().getProfitPercentage()));

                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getCommissionFees()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getFixedFees()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getCollectionFees()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getShippingFees()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getCFCS()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getGstOnCFCS()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getTotalCharges()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getBankSettlement()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getTotalGstPayable()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getTcs()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getGstPayable()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getProfit()));
                    Zonal.add(String.valueOf(CalculateResponse.getFlipkartZonal().getProfitPercentage()));

                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getCommissionFees()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getFixedFees()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getCollectionFees()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getShippingFees()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getCFCS()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getGstOnCFCS()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getTotalCharges()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getBankSettlement()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getTotalGstPayable()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getTcs()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getGstPayable()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getProfit()));
                    National.add(String.valueOf(CalculateResponse.getFlipkartNational().getProfitPercentage()));

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
        String length = this.length.getText().toString().trim();
        String breadth = this.breadth.getText().toString().trim();
        String height = this.height.getText().toString().trim();
        String payMode = paymentOption;
        String customerType = customerTypeOption;

        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .savedFlipkart(title, category, sellingPrice, gstOnProduct, productPriceWithoutGst, inwardShipping, packagingExpense, labour,
                        storageFee, other, discountPercent, discountAmount, weight, length, breadth, height, payMode, customerType,
                        Local.get(0), Local.get(1), Local.get(2), Local.get(3), Local.get(4), Local.get(5), Local.get(6), Local.get(7), Local.get(8),
                        Local.get(9), Local.get(10), Local.get(11), Local.get(12), Zonal.get(0), Zonal.get(1), Zonal.get(2), Zonal.get(3), Zonal.get(4),
                        Zonal.get(5), Zonal.get(6), Zonal.get(7), Zonal.get(8), Zonal.get(9), Zonal.get(10), Zonal.get(11), Zonal.get(12),
                        National.get(0), National.get(1), National.get(2), National.get(3), National.get(4), National.get(5), National.get(6),
                        National.get(7), National.get(8), National.get(9), National.get(10), National.get(11), National.get(12));

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
                "Courier" + customerTypeOption + "\n" +
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
