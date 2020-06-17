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

import com.ecommerce.calculator.HeightWrappingViewPager;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.adapter.AmazonSectionAdapter;
import com.ecommerce.calculator.models.AmazonCalculationResponse;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.models.subCategory;
import com.ecommerce.calculator.activities.HomeScreen;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.progressButton;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class amazon_calculation extends Fragment implements View.OnClickListener  {

        private ImageButton details, expenses, discounts, save, sendEmail, reset;
        private EditText sellingPrice, purchasePrice, inwardShipping, packagingExpenses, labour, storageFee, otherCharges, discountByPrice,
                discountByPercentage, weight, length, breadth, height, selfshipLocal, selfshipRegional, selfshipNational;
        LinearLayout linearLayout, weightBunch, lengthBunch, breadthBunch, heightBunch, customerTypeBunch, courierBunch, payModeBunch, selfshipLocalBunch,
                selfshipRegionalBunch, selfshipNationalBunch, easyShipmentTypeBunch, shipmentTypeBunch, subCategoryBunch;

        CardView productDetailsCard, expensesCard, discountsCard;
        Spinner gstOnProduct, categories, subCategories;
        String categoryFinal, subCategoryFinal;
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> subList = new ArrayList<>();
        ListView itemList;
        CardView result_card;
        String[] gst_array = {"0", "5", "12", "18", "28"};
        String spinner_ans;
        private ProgressBar progressBar;
        private TextView textView;
        private String myText;
        RadioGroup shipmentType, easyShipmentType;
        RadioButton radioButton, radioButtonEasyShip, radioButtonSelfShip, radioButtonPrime, radioButtonNonPrime;
        String easyShipmentTypeOption ="prime", shipmentTypeOption ="easyShip";
        HeightWrappingViewPager viewPager;
        TabLayout tabLayout;
        ArrayList<String> Local = new ArrayList<>();
        ArrayList<String> Regional = new ArrayList<>();
        ArrayList<String> National = new ArrayList<>();

        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.common_calculation, container, false);

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

            shipmentType = view.findViewById(R.id.shipmentType);
            shipmentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    radioButton = view.findViewById(checkedId);
                    shipmentTypeOption = radioButton.getText().toString();
                    if(shipmentTypeOption.equals("easyShip")){
                        easyShipmentTypeBunch.setVisibility(View.VISIBLE);
                        weightBunch.setVisibility(View.VISIBLE);
                        lengthBunch.setVisibility(View.VISIBLE);
                        breadthBunch.setVisibility(View.VISIBLE);
                        heightBunch.setVisibility(View.VISIBLE);
                        selfshipLocalBunch.setVisibility(View.GONE);
                        selfshipRegionalBunch.setVisibility(View.GONE);
                        selfshipNationalBunch.setVisibility(View.GONE);
                    }else{
                        selfshipLocalBunch.setVisibility(View.VISIBLE);
                        selfshipRegionalBunch.setVisibility(View.VISIBLE);
                        selfshipNationalBunch.setVisibility(View.VISIBLE);
                        easyShipmentTypeBunch.setVisibility(View.GONE);
                        weightBunch.setVisibility(View.GONE);
                        lengthBunch.setVisibility(View.GONE);
                        breadthBunch.setVisibility(View.GONE);
                        heightBunch.setVisibility(View.GONE);
                    }
                }
            });

            easyShipmentType = view.findViewById(R.id.easyShipmentType);
            easyShipmentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    radioButton = view.findViewById(checkedId);
                    easyShipmentTypeOption = radioButton.getText().toString();
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
                    subCategories.setSelection(0);
                    purchasePrice.setText("");
                    gstOnProduct.setSelection(0);
                    radioButtonEasyShip.setChecked(true);
                    radioButtonPrime.setChecked(true);
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
                    selfshipLocal.setText("");
                    selfshipRegional.setText("");
                    selfshipNational.setText("");
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
                    Toast.makeText(getActivity(), categoryFinal, Toast.LENGTH_SHORT).show();
                    subCategories(categoryFinal);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            subCategories = view.findViewById(R.id.subCategory);

            subCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    subCategoryFinal = subList.get(position);
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
            weightBunch = view.findViewById(R.id.weightBunch);
            lengthBunch = view.findViewById(R.id.lengthBunch);
            breadthBunch = view.findViewById(R.id.breadthBunch);
            heightBunch = view.findViewById(R.id.heightBunch);
            subCategoryBunch = view.findViewById(R.id.subCategoryBunch);
            subCategoryBunch.setVisibility(View.VISIBLE);
            shipmentTypeBunch = view.findViewById(R.id.shipmentTypeBunch);
            shipmentTypeBunch.setVisibility(View.VISIBLE);
            easyShipmentTypeBunch = view.findViewById(R.id.easyShipmentTypeBunch);
            easyShipmentTypeBunch.setVisibility(View.VISIBLE);
            selfshipLocalBunch = view.findViewById(R.id.selfshipLocalBunch);
            selfshipRegionalBunch = view.findViewById(R.id.selfshipRegionalBunch);
            selfshipNationalBunch = view.findViewById(R.id.selfshipNationalBunch);
            radioButtonEasyShip = view.findViewById(R.id.radio_easyship);
            radioButtonSelfShip = view.findViewById(R.id.radio_selfship);
            radioButtonPrime = view.findViewById(R.id.radio_prime);
            radioButtonNonPrime = view.findViewById(R.id.radio_nonPrime);
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
            selfshipLocal = view.findViewById(R.id.selfshipLocal);
            selfshipRegional = view.findViewById(R.id.selfshipRegional);
            selfshipNational = view.findViewById(R.id.selfshipNational);
            progressBar = view.findViewById(R.id.loader);
            textView = view.findViewById(R.id.calculate_textview);
            customerTypeBunch = view.findViewById(R.id.customerTypeBunch);
            payModeBunch = view.findViewById(R.id.payModeBunch);

            customerTypeBunch.setVisibility(View.GONE);
            payModeBunch.setVisibility(View.GONE);

            if(SharedPrefManager.getInstance(getActivity()).getFlag().equals("true")){
                Bundle bundle = this.getArguments();
                ArrayList<String> bundle_input = bundle.getStringArrayList("input");
                int selection = adapter.getPosition(bundle_input.get(0));
                categories.setSelection(selection);

                selection = adapter.getPosition(bundle_input.get(1));
                subCategories.setSelection(selection);
                sellingPrice.setText(bundle_input.get(2));
                purchasePrice.setText(bundle_input.get(3));
                int value = Integer.parseInt(bundle_input.get(4));
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
                inwardShipping.setText(bundle_input.get(5));
                packagingExpenses.setText(bundle_input.get(6));
                labour.setText(bundle_input.get(7));
                storageFee.setText(bundle_input.get(8));
                otherCharges.setText(bundle_input.get(9));
                discountByPrice.setText(bundle_input.get(10));
                discountByPercentage.setText(bundle_input.get(11));
                if(bundle_input.get(12).equals("easyShip")){
                    radioButtonEasyShip.setChecked(true);
                    if(bundle_input.get(13).equals("prime")){
                        radioButtonPrime.setChecked(true);
                    }else{
                        radioButtonNonPrime.setChecked(true);
                    }
                    weight.setText(bundle_input.get(14));
                    length.setText(bundle_input.get(15));
                    breadth.setText(bundle_input.get(16));
                    height.setText(bundle_input.get(17));
                }else{
                    radioButtonSelfShip.setChecked(true);
                    selfshipLocal.setText(bundle_input.get(13));
                    selfshipRegional.setText(bundle_input.get(14));
                    selfshipNational.setText(bundle_input.get(15));
                }

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

            double number11=0, number12=0, number13=0, number14=0, number15=0, number16=0, number17=0;

            if(shipmentTypeOption.equals("easyShip")) {

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
                number11 = Double.parseDouble(weight.getText().toString());
                number12 = Double.parseDouble(length.getText().toString());
                number13 = Double.parseDouble(breadth.getText().toString());
                number14 = Double.parseDouble(height.getText().toString());
            }
            else{
                if (selfshipLocal.getText().toString().isEmpty()) {
                    selfshipLocal.setError("SelfShip Local is required");
                    selfshipLocal.requestFocus();
                    ButtonFinished();
                    return;
                }

                if (selfshipRegional.getText().toString().isEmpty()) {
                    selfshipRegional.setError("SelfShip Regional is required");
                    selfshipRegional.requestFocus();
                    ButtonFinished();
                    return;
                }

                if (selfshipNational.getText().toString().isEmpty()) {
                    selfshipNational.setError("SelfShip National is required");
                    selfshipNational.requestFocus();
                    ButtonFinished();
                    return;
                }

                number15 = Double.parseDouble(selfshipLocal.getText().toString());
                number16 = Double.parseDouble(selfshipRegional.getText().toString());
                number17 = Double.parseDouble(selfshipNational.getText().toString());
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
            String subCategory = subCategoryFinal;
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

            Call<AmazonCalculationResponse> call = RetrofitClient
                    .getInstance().getApi().amazonCalculation(category, subCategory, number1, number3, number2, number4, number5, number6, number7,
                            number8, number10, number9, number11, number12, number13, number14,shipmentTypeOption, easyShipmentTypeOption,
                            number15, number16, number17);

            call.enqueue(new Callback<AmazonCalculationResponse>() {
                @Override
                public void onResponse(Call<AmazonCalculationResponse> call, Response<AmazonCalculationResponse> response) {
                    if(response.isSuccessful()) {
                        AmazonCalculationResponse CalculateResponse = response.body();
                        ButtonFinished();

                        result_card.setVisibility(View.VISIBLE);
                        tabLayout.removeAllTabs();

                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getReferralFees()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getClosingFees()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getShippingFees()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getRCS()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getGstOnRCS()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getTotalCharges()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getGstClaim()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getBankSettlement()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getTotalGstPayable()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getTcs()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getGstPayable()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getProfit()));
                        Local.add(String.valueOf(CalculateResponse.getAmazonLocal().getProfitPercentage()));

                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getReferralFees()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getClosingFees()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getShippingFees()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getRCS()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getGstOnRCS()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getTotalCharges()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getGstClaim()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getBankSettlement()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getTotalGstPayable()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getTcs()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getGstPayable()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getProfit()));
                        Regional.add(String.valueOf(CalculateResponse.getAmazonRegional().getProfitPercentage()));

                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getReferralFees()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getClosingFees()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getShippingFees()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getRCS()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getGstOnRCS()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getTotalCharges()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getGstClaim()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getBankSettlement()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getTotalGstPayable()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getTcs()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getGstPayable()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getProfit()));
                        National.add(String.valueOf(CalculateResponse.getAmazonNational().getProfitPercentage()));

                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("Local", Local);
                        bundle.putStringArrayList("Regional", Regional);
                        bundle.putStringArrayList("National", National);

                        tabLayout.addTab(tabLayout.newTab().setText("Local"));
                        tabLayout.addTab(tabLayout.newTab().setText("Regional"));
                        if(Double.parseDouble(weight.getText().toString()) <= 12000 || Double.parseDouble(length.getText().toString())*
                                Double.parseDouble(breadth.getText().toString())*Double.parseDouble(height.getText().toString())/5 <= 12000) {
                            tabLayout.addTab(tabLayout.newTab().setText("National"));
                        }
                        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
                        AmazonSectionAdapter flipkartSectionAdapter = new AmazonSectionAdapter(getContext(), getChildFragmentManager(), tabLayout.getTabCount(), bundle);
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
                public void onFailure(Call<AmazonCalculationResponse> call, Throwable t) {
                    ButtonFinished();
                    Toast.makeText(getContext(), "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void save(){
            String title = String.valueOf(myText);
            String category = categoryFinal;
            String subCategory = subCategoryFinal;
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
            String ssLocal = this.selfshipLocal.getText().toString().trim();
            String ssRegional = this.selfshipRegional.getText().toString().trim();
            String ssNational = this.selfshipNational.getText().toString().trim();

            Call<MessageResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .savedAmazon(title, category, subCategory, sellingPrice, gstOnProduct, productPriceWithoutGst, inwardShipping, packagingExpense,
                            labour, storageFee, other, discountPercent, discountAmount, weight, length, breadth, height, shipmentTypeOption,
                            easyShipmentTypeOption, ssLocal, ssRegional, ssNational, Local.get(0), Local.get(1), Local.get(2), Local.get(3),
                            Local.get(4), Local.get(5), Local.get(6), Local.get(7), Local.get(8), Local.get(9), Local.get(10), Local.get(11),
                            Local.get(12), Regional.get(0), Regional.get(1), Regional.get(2), Regional.get(3), Regional.get(4),
                            Regional.get(5), Regional.get(6), Regional.get(7), Regional.get(8), Regional.get(9), Regional.get(10), Regional.get(11),
                            Regional.get(12), National.get(0), National.get(1), National.get(2), National.get(3), National.get(4), National.get(5),
                            National.get(6), National.get(7), National.get(8), National.get(9), National.get(10), National.get(11), National.get(12));

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
                    "Sub Category: " + subCategoryFinal + "\n" +
                    "Selling Price: " + sellingPrice.getText().toString().trim() + "\n" +
                    "GST On Product: " + spinner_ans + "\n" +
                    "Product Price Without GST: " + purchasePrice.getText().toString().trim() + "\n" +
                    "Shipment Type: " + shipmentTypeOption + "\n";
            String content1 = "";
                    if(shipmentTypeOption.equals("easyShip")){
                        content1 = "Easy Shipment Type: " + easyShipmentTypeOption + "\n" +
                                "Weight: " + weight.getText().toString().trim() + "\n" +
                                "Length: " + length.getText().toString().trim() + "\n" +
                                "Breadth: " + breadth.getText().toString().trim() + "\n" +
                                "Height: " + height.getText().toString().trim() + "\n";
                    }else {
                        content1 = "Self Ship Local: " + selfshipLocal.getText().toString().trim() + "\n" +
                        "Self Ship Regional: " + selfshipRegional.getText().toString().trim() + "\n" +
                        "Self Ship National: " + selfshipNational.getText().toString().trim() + "\n";
                    }
                    String content2 = "Inward Shipping: " + inwardShipping.getText().toString().trim() + "\n" +
                    "Packaging Expense: " + packagingExpenses.getText().toString().trim() + "\n" +
                    "Labour: " + labour.getText().toString().trim() + "\n" +
                    "Storage Fee: " + storageFee.getText().toString().trim() + "\n" +
                    "Other: " + otherCharges.getText().toString().trim() + "\n" +
                    "Discount Percent: " + discountByPercentage.getText().toString().trim() + "\n" +
                    "Discount Amount: " + discountByPrice.getText().toString().trim() + "\n\n" +
                    "OUTPUT" + "\n\n" +
                    "Local" + "\n" +
                            "Referral Fees: " + Local.get(0) + "\n" +
                            "Closing Fees: " + Local.get(1) + "\n" +
                            "Shipping Fees: " + Local.get(2) + "\n" +
                            "Referral + Closing + Shipping Fees: " + Local.get(3) + "\n" +
                            "GST On RCS: " + Local.get(4) + "\n" +
                            "Total Charges: " + Local.get(5) + "\n" +
                            "GST Claim: " + Local.get(6) + "\n" +
                            "Bank Settlement: " + Local.get(7) + "\n" +
                            "Total GST Payable: " + Local.get(8) + "\n" +
                            "Tcs: " + Local.get(9) + "\n" +
                            "GST Payable: " + Local.get(10) + "\n" +
                            "Profit: " + Local.get(11) + "\n" +
                            "Profit Percentage: " + Local.get(12) + "\n\n" +
                    "Regional" + "\n" +
                            "Referral Fees: " + Regional.get(0) + "\n" +
                            "Closing Fees: " + Regional.get(1) + "\n" +
                            "Shipping Fees: " + Regional.get(2) + "\n" +
                            "Referral + Closing + Shipping Fees: " + Regional.get(3) + "\n" +
                            "GST On RCS: " + Regional.get(4) + "\n" +
                            "Total Charges: " + Regional.get(5) + "\n" +
                            "GST Claim: " + Regional.get(6) + "\n" +
                            "Bank Settlement: " + Regional.get(7) + "\n" +
                            "Total GST Payable: " + Regional.get(8) + "\n" +
                            "Tcs: " + Regional.get(9) + "\n" +
                            "GST Payable: " + Regional.get(10) + "\n" +
                            "Profit: " + Regional.get(11) + "\n" +
                            "Profit Percentage: " + Regional.get(12) + "\n\n";
                    String content3 = "";
            if(Double.parseDouble(weight.getText().toString()) <= 12000 || Double.parseDouble(length.getText().toString())*
                    Double.parseDouble(breadth.getText().toString())*Double.parseDouble(height.getText().toString())/5 <= 12000) {
                content3 = "National" + "\n" +
                        "Referral Fees: " + National.get(0) + "\n" +
                        "Closing Fees: " + National.get(1) + "\n" +
                        "Shipping Fees: " + National.get(2) + "\n" +
                        "Referral + Closing + Shipping Fees: " + National.get(3) + "\n" +
                        "GST On RCS: " + National.get(4) + "\n" +
                        "Total Charges: " + National.get(5) + "\n" +
                        "GST Claim: " + National.get(6) + "\n" +
                        "Bank Settlement: " + National.get(7) + "\n" +
                        "Total GST Payable: " + National.get(8) + "\n" +
                        "Tcs: " + National.get(9) + "\n" +
                        "GST Payable: " + National.get(10) + "\n" +
                        "Profit: " + National.get(11) + "\n" +
                        "Profit Percentage: " + National.get(12) + "\n";
            }

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody = content + content1 + content2 +content3;
            String shareSub = "Your Bill";
            intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(intent, "Share using"));
        }

        void ButtonFinished(){
            progressBar.setVisibility(View.GONE);
            textView.setText("Calculate");
        }

        public void subCategories(String categoryFinal){
            Call<subCategory> call;
            call = RetrofitClient.getInstance().getApi().getSubCategoriesAmazon(categoryFinal);
            call.enqueue(new Callback<subCategory>() {
                @Override
                public void onResponse(Call<subCategory> call, Response<subCategory> response) {
                    if(response.isSuccessful()) {
                        subCategory subCategory = response.body();
                        ArrayList<String> list = new ArrayList<>();
                        for (String s : subCategory.getSubCategories()) {
                            list.add(s);
                        }
                        SharedPrefManager.getInstance(getActivity())
                                .saveSubList(list);
                        subList = SharedPrefManager.getInstance(getActivity()).getSubList();
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_activated_1, list);
                        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
                        subCategories.setAdapter(adapter2);
                    }else if(response.code() == 401){
                        Toast.makeText(getActivity(), "Session Expire! Please Login Again", Toast.LENGTH_SHORT).show();
                        SharedPrefManager.getInstance(getActivity()).clear();
                        Intent intent_logout = new Intent(getActivity(), HomeScreen.class);
                        intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent_logout);
                    }
                }

                @Override
                public void onFailure(Call<subCategory> call, Throwable t) {
                    Toast.makeText(getActivity(), "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
}
