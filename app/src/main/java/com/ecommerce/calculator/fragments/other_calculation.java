package com.ecommerce.calculator.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.activities.HomeScreen;
import com.ecommerce.calculator.activities.SavedData;
import com.ecommerce.calculator.adapter.AmazonSectionAdapter;
import com.ecommerce.calculator.adapter.OutputListAdapter;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.CommonOutputModel;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.models.output;
import com.ecommerce.calculator.models.progressButton;
import com.ecommerce.calculator.models.subCategory;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.ecommerce.calculator.utils.HeightWrappingViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class other_calculation extends Fragment implements View.OnClickListener {

    private TextView commissionText;
    private ImageButton details, expenses, discounts, save, sendEmail, reset;
    private EditText sellingPrice, purchasePrice, inwardShipping, packagingExpenses, labour, storageFee, otherCharges, discountByPrice,
            discountByPercentage, shipping, paymentGatewayCharge, commission;
    LinearLayout linearLayout, weightBunch, lengthBunch, breadthBunch, heightBunch, customerTypeBunch, courierBunch, payModeBunch, commissionBunch,
            paymentGatewayChargeBunch, shippingBunch, categoryBunch;

    CardView productDetailsCard, expensesCard, discountsCard;
    HeightWrappingViewPager viewPager;
    Spinner gstOnProduct;
    ArrayList<String> list = new ArrayList<>();
    ListView itemList;
    CardView result_card;
    ScrollView scrollView;
    CommonOutputModel commonOutputModel;
    String[] gst_array = {"0", "5", "12", "18", "28"};
    String spinner_ans;
    TabLayout tabLayout;
    private ProgressBar progressBar;
    private TextView textView, line1, line2, line3, line4, line14, line15, line16, line17;
    private String myText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.common_calculation, container, false);

        details = view.findViewById(R.id.details_dropdown);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    shippingBunch.setVisibility(View.VISIBLE);
                    paymentGatewayChargeBunch.setVisibility(View.VISIBLE);
                    commissionBunch.setVisibility(View.VISIBLE);
                    line14.setVisibility(View.VISIBLE);
                    line15.setVisibility(View.VISIBLE);
                    line16.setVisibility(View.VISIBLE);
                    expenses.setRotation(180);
                } else {
                    expensesCard.setVisibility(View.GONE);
                    shippingBunch.setVisibility(View.GONE);
                    paymentGatewayChargeBunch.setVisibility(View.GONE);
                    commissionBunch.setVisibility(View.GONE);
                    line14.setVisibility(View.GONE);
                    line15.setVisibility(View.GONE);
                    line16.setVisibility(View.GONE);
                    expenses.setRotation(0);
                }
            }
        });

        discounts = view.findViewById(R.id.discounts_dropdown);
        discounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressButton progressButton = new progressButton(getActivity(), v);
                progressButton.ButtonActivated();
                InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                View focusedView = getActivity().getCurrentFocus();
                if (focusedView != null) {
                    inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                calculate();
            }
        });

        reset = view.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellingPrice.requestFocus();
                sellingPrice.setText("");
                purchasePrice.setText("");
                gstOnProduct.setSelection(0);
                inwardShipping.setText("");
                packagingExpenses.setText("");
                labour.setText("");
                storageFee.setText("");
                shipping.setText("");
                commission.setText("");
                paymentGatewayCharge.setText("");
                otherCharges.setText("");
                discountByPrice.setText("");
                discountByPercentage.setText("");
                result_card.setVisibility(View.GONE);
            }
        });

        save = view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(getActivity());
                mydialog.setTitle("Title");

                final EditText title = new EditText(getActivity());
                title.setInputType(InputType.TYPE_CLASS_TEXT);
                title.setHint("Enter Title");
                title.requestFocus();
                title.setPadding(70, 40, 50, 40);
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
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemList = view.findViewById(R.id.text_view_result);
        result_card = view.findViewById(R.id.result_card);
        linearLayout = view.findViewById(R.id.linearlayout);
        scrollView = view.findViewById(R.id.scrollView);

        commissionText = view.findViewById(R.id.text_Commission);
        commissionText.setText("Marketing Fees");
        productDetailsCard = view.findViewById(R.id.productDetailsCard);
        expensesCard = view.findViewById(R.id.expensesCard);
        discountsCard = view.findViewById(R.id.discountsCard);
        details = view.findViewById(R.id.details_dropdown);
        expenses = view.findViewById(R.id.expenses_dropdown);
        discounts = view.findViewById(R.id.discounts_dropdown);
        categoryBunch = view.findViewById(R.id.categoryBunch);
        weightBunch = view.findViewById(R.id.weightBunch);
        lengthBunch = view.findViewById(R.id.lengthBunch);
        breadthBunch = view.findViewById(R.id.breadthBunch);
        heightBunch = view.findViewById(R.id.heightBunch);
        customerTypeBunch = view.findViewById(R.id.customerTypeBunch);
        payModeBunch = view.findViewById(R.id.payModeBunch);
        courierBunch = view.findViewById(R.id.courierBunch);
        commissionBunch = view.findViewById(R.id.commissionBunch);
        paymentGatewayChargeBunch = view.findViewById(R.id.paymentGatewayChargeBunch);
        shippingBunch = view.findViewById(R.id.shippingBunch);
        line14 = view.findViewById(R.id.line14);
        line15 = view.findViewById(R.id.line15);
        line16 = view.findViewById(R.id.line16);

        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setVisibility(View.GONE);
        line1 = view.findViewById(R.id.line1);
        line2 = view.findViewById(R.id.line2);
        line3 = view.findViewById(R.id.line3);
        line4 = view.findViewById(R.id.line4);
        line17 = view.findViewById(R.id.line17);
        categoryBunch.setVisibility(View.GONE);
        lengthBunch.setVisibility(View.GONE);
        breadthBunch.setVisibility(View.GONE);
        heightBunch.setVisibility(View.GONE);
        customerTypeBunch.setVisibility(View.GONE);
        weightBunch.setVisibility(View.GONE);
        payModeBunch.setVisibility(View.GONE);
        courierBunch.setVisibility(View.GONE);
        line1.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);
        line3.setVisibility(View.GONE);
        line4.setVisibility(View.GONE);
        line17.setVisibility(View.GONE);

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
        shipping = view.findViewById(R.id.shipping);
        commission = view.findViewById(R.id.commission);
        paymentGatewayCharge = view.findViewById(R.id.paymentGatewayCharge);
        otherCharges = view.findViewById(R.id.otherCharges);
        discountByPrice = view.findViewById(R.id.discountByPrice);
        discountByPercentage = view.findViewById(R.id.discountByPercentage);
        progressBar = view.findViewById(R.id.loader);
        textView = view.findViewById(R.id.calculate_textview);

        if (SharedPrefManager.getInstance(getActivity()).getFlag().equals("true")) {
            Bundle bundle = this.getArguments();
            ArrayList<String> bundle_input = bundle.getStringArrayList("input");
            sellingPrice.setText(bundle_input.get(0));
            purchasePrice.setText(bundle_input.get(1));
            int value = Integer.parseInt(bundle_input.get(2));
            switch (value) {
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
            inwardShipping.setText(bundle_input.get(3));
            packagingExpenses.setText(bundle_input.get(4));
            labour.setText(bundle_input.get(5));
            storageFee.setText(bundle_input.get(6));
            commission.setText(bundle_input.get(7));
            shipping.setText(bundle_input.get(8));
            paymentGatewayCharge.setText(bundle_input.get(9));
            otherCharges.setText(bundle_input.get(10));
            discountByPrice.setText(bundle_input.get(11));
            discountByPercentage.setText(bundle_input.get(12));

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

        if (Double.parseDouble(sellingPrice.getText().toString()) <= 0) {
            sellingPrice.setError("Selling Price not valid");
            sellingPrice.requestFocus();
            ButtonFinished();
            return;
        }

        if (Double.parseDouble(purchasePrice.getText().toString()) <= 0) {
            purchasePrice.setError("Purchase Price is not valid");
            purchasePrice.requestFocus();
            ButtonFinished();
            return;
        }

        if (commission.getText().toString().isEmpty()) {
            expensesCard.setVisibility(View.VISIBLE);
            shippingBunch.setVisibility(View.VISIBLE);
            paymentGatewayChargeBunch.setVisibility(View.VISIBLE);
            commissionBunch.setVisibility(View.VISIBLE);
            line14.setVisibility(View.VISIBLE);
            line15.setVisibility(View.VISIBLE);
            line16.setVisibility(View.VISIBLE);
            expenses.setRotation(180);
            commission.setError("Commission is Required");
            commission.requestFocus();
            ButtonFinished();
            return;
        }

        if (shipping.getText().toString().isEmpty()) {
            expensesCard.setVisibility(View.VISIBLE);
            shippingBunch.setVisibility(View.VISIBLE);
            paymentGatewayChargeBunch.setVisibility(View.VISIBLE);
            commissionBunch.setVisibility(View.VISIBLE);
            line14.setVisibility(View.VISIBLE);
            line15.setVisibility(View.VISIBLE);
            line16.setVisibility(View.VISIBLE);
            expenses.setRotation(180);
            shipping.setError("Shipping is Required");
            shipping.requestFocus();
            ButtonFinished();
            return;
        }

        if (paymentGatewayCharge.getText().toString().isEmpty()) {
            expensesCard.setVisibility(View.VISIBLE);
            shippingBunch.setVisibility(View.VISIBLE);
            paymentGatewayChargeBunch.setVisibility(View.VISIBLE);
            commissionBunch.setVisibility(View.VISIBLE);
            line14.setVisibility(View.VISIBLE);
            line15.setVisibility(View.VISIBLE);
            line16.setVisibility(View.VISIBLE);
            expenses.setRotation(180);
            paymentGatewayCharge.setError("Payment Gateway Charge is Required");
            paymentGatewayCharge.requestFocus();
            ButtonFinished();
            return;
        }

        if (inwardShipping.getText().toString().isEmpty())
            inwardShipping.setText("0");
        if (packagingExpenses.getText().toString().isEmpty())
            packagingExpenses.setText("0");
        if (labour.getText().toString().isEmpty())
            labour.setText("0");
        if (storageFee.getText().toString().isEmpty())
            storageFee.setText("0");
        if (otherCharges.getText().toString().isEmpty())
            otherCharges.setText("0");
        if (discountByPrice.getText().toString().isEmpty())
            discountByPrice.setText("0");
        if (discountByPercentage.getText().toString().isEmpty())
            discountByPercentage.setText("0");

        if (Double.parseDouble(discountByPrice.getText().toString()) > 0 && Double.parseDouble(discountByPercentage.getText().toString()) > 0) {
            discountByPrice.setError("One discount criteria valid at a time");
            discountByPrice.requestFocus();
            ButtonFinished();
            return;
        }

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
        double number11 = Double.parseDouble(commission.getText().toString());
        double number12 = Double.parseDouble(shipping.getText().toString());
        double number13 = Double.parseDouble(paymentGatewayCharge.getText().toString());

        Call<CommonOutputModel> call = RetrofitClient
                .getInstance().getApi().otherCalculation(number1, number3, number2, number4, number5, number6, number7, number8, number10, number9,
                        number11, number12, number13);

        call.enqueue(new Callback<CommonOutputModel>() {
            @Override
            public void onResponse(Call<CommonOutputModel> call, Response<CommonOutputModel> response) {
                if (response.isSuccessful()) {
                    commonOutputModel = response.body();
                    ButtonFinished();

                    result_card.setVisibility(View.VISIBLE);
                    itemList.setVisibility(View.VISIBLE);

                    output text1 = new output("Marketing Fees", String.valueOf(commonOutputModel.getMarketingFees()) + " ₹");
                    output text2 = new output("Shipping Fees", String.valueOf(commonOutputModel.getShippingFees()));
                    output text3 = new output("Payment Gateway Fees", String.valueOf(commonOutputModel.getPaymentGatewayFees()) + " ₹");
                    output text4 = new output("Marketing Fees + Shipping Fees + Payment Gateway Fees", String.valueOf(commonOutputModel.getCSP()) + " ₹");
                    output text5 = new output("GST On Marketing Fees + Shipping Fees + Payment Gateway Fees", String.valueOf(commonOutputModel.getGstOnCSP()) + " ₹");
                    output text6 = new output("Total Charges", String.valueOf(commonOutputModel.getTotalCharges()) + " ₹");
                    output text7 = new output("Bank Settlement", String.valueOf(commonOutputModel.getBankSettlement()) + " ₹");
                    output text8 = new output("GST Claim", String.valueOf(commonOutputModel.getGstClaim()) + " ₹");
                    output text9 = new output("GST Payable", String.valueOf(commonOutputModel.getGstPayable()) + " ₹");
                    output text10 = new output("Total GST Payable", String.valueOf(commonOutputModel.getTotalGstPayable()) + " ₹");
                    output text12 = new output("Profit", String.valueOf(commonOutputModel.getProfit()) + " ₹");
                    output text13 = new output("Profit Percentage", String.valueOf(commonOutputModel.getProfitPercentage()) + " %");

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
                    outputList.add(text12);
                    outputList.add(text13);

                    OutputListAdapter adapter = new OutputListAdapter(getActivity(), R.layout.output_row, outputList);
                    itemList.setAdapter(adapter);
                    SavedData.setListViewHeightBasedOnChildren(itemList);

                    save.setImageResource(R.drawable.ic_bookmark_border);
                    save.setEnabled(true);

                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });

                } else if (response.code() == 501) {
                    Toast.makeText(getContext(), "Session Expire! Please Login Again", Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(getContext()).clear();
                    Intent intent_logout = new Intent(getContext(), HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_logout);
                }
            }

            @Override
            public void onFailure(Call<CommonOutputModel> call, Throwable t) {
                ButtonFinished();
                Toast.makeText(getContext(), "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void save() {
        String title = String.valueOf(myText);
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
        String commission = this.commission.getText().toString().trim();
        String shipping = this.shipping.getText().toString().trim();
        String paymentGatewayCharge = this.paymentGatewayCharge.getText().toString().trim();

        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .savedOther(title, sellingPrice, gstOnProduct, productPriceWithoutGst, inwardShipping, packagingExpense, labour, storageFee,
                        other, discountAmount, discountPercent, commission, shipping, paymentGatewayCharge);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    MessageResponse dr = response.body();
                    if (dr.getMessage().equals("data_saved")) {
                        save.setImageResource(R.drawable.ic_bookmark);
                        save.setEnabled(false);
                        new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Successfully Saved")
                                .setConfirmText("Ok")
                                .setConfirmButtonBackgroundColor(getResources().getColor(R.color.colorPrimaryDark))
                                .show();
                        SharedPrefManager.getInstance(getActivity()).saveTempVar("true");
                    } else {
                        Toast.makeText(getContext(), "Title Name Already Exists", Toast.LENGTH_SHORT).show();
                    }
                } else if (response.code() == 401) {
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

    public void share() {
        String content = "EBAY" + "\n\n" +
                "INPUT" + "\n" +
                "Selling Price: " + " ₹" + sellingPrice.getText().toString().trim() + "\n" +
                "Product Price Without GST: " + " ₹" + purchasePrice.getText().toString().trim() + "\n" +
                "GST On Product: " + spinner_ans + " %" + "\n" +
                "Inward Shipping: " + " ₹" + inwardShipping.getText().toString().trim() + "\n" +
                "Packaging Expense: " + " ₹" + packagingExpenses.getText().toString().trim() + "\n" +
                "Labour: " + " ₹" + labour.getText().toString().trim() + "\n" +
                "Storage Fee: " + " ₹" + storageFee.getText().toString().trim() + "\n" +
                "Marketing Fees: " + commission.getText().toString().trim() + " %" + "\n" +
                "Shipping: " + " ₹" + shipping.getText().toString().trim() + "\n" +
                "Payment Gateway Charge: " + paymentGatewayCharge.getText().toString().trim() + " %" + "\n" +
                "Other: " + " ₹" + otherCharges.getText().toString().trim() + "\n" +
                "Discount Amount: " + " ₹" + discountByPrice.getText().toString().trim() + "\n" +
                "Discount Percent: " + discountByPercentage.getText().toString().trim() + " %" + "\n\n" +
                "OUTPUT" + "\n" +
                "Marketing Fees: " + " ₹" + commonOutputModel.getMarketingFees() + "\n" +
                "Shipping Fees: " + " ₹" + commonOutputModel.getShippingFees() + "\n" +
                "Payment Gateway Fees: " + " ₹" + commonOutputModel.getPaymentGatewayFees() + "\n" +
                "Marketing Fees + Shipping Fees + Payment Fees: " + " ₹" + commonOutputModel.getCSP() + "\n" +
                "GST On Marketing Fees + Shipping Fees + Payment Fees: " + " ₹" + commonOutputModel.getGstOnCSP() + "\n" +
                "Total Charges: " + " ₹" + commonOutputModel.getTotalCharges() + "\n" +
                "Bank Settlement: " + " ₹" + commonOutputModel.getBankSettlement() + "\n" +
                "GST Claim: " + " ₹" + commonOutputModel.getGstClaim() + "\n" +
                "GST Payable: " + " ₹" + commonOutputModel.getGstPayable() + "\n" +
                "Total GST Payable: " + " ₹" + commonOutputModel.getTotalGstPayable() + "\n" +
                "Tcs: " + " ₹" + commonOutputModel.getTcs() + "\n" +
                "Profit: " + " ₹" + commonOutputModel.getProfit() + "\n" +
                "Profit Percentage: " + commonOutputModel.getProfitPercentage() + " %";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = content;
        String shareSub = "Your Bill";
        intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(intent, "Share using"));
    }

    void ButtonFinished() {
        progressBar.setVisibility(View.GONE);
        textView.setText("Calculate");
    }

    @Override
    public void onClick(View v) {

    }
}