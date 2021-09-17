package com.ecommerce.calculator.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;

import com.ecommerce.calculator.models.CommonOutputModel;
import com.ecommerce.calculator.models.subCategory;
import com.ecommerce.calculator.utils.HeightWrappingViewPager;
import com.ecommerce.calculator.activities.HomeScreen;
import com.ecommerce.calculator.activities.SavedData;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.ecommerce.calculator.adapter.OutputListAdapter;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.output;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View.OnClickListener;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class meesho_calculation extends Fragment implements View.OnClickListener {

    private ImageButton details, expenses, discounts, save, sendEmail, reset;
    private EditText sellingPrice, purchasePrice, inwardShipping, packagingExpenses, labour, storageFee, otherCharges, discountByPrice, discountByPercentage;
    LinearLayout linearLayout, weightBunch, lengthBunch, breadthBunch, heightBunch, customerTypeBunch, courierBunch, payModeBunch, subCategoryBunch;

    CardView productDetailsCard, expensesCard, discountsCard;
    HeightWrappingViewPager viewPager;
    Spinner gstOnProduct, categories, subCategories;
    String categoryFinal, subCategoryFinal;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> subList = new ArrayList<>();
    ListView itemList;
    CardView result_card;
    CommonOutputModel commonOutputModel;
    String[] gst_array = {"0", "5", "12", "18", "28"};
    String spinner_ans;
    ScrollView scrollView;
    TabLayout tabLayout;
    private ProgressBar progressBar;
    private TextView textView, line1, line2, line3, line4;
    private String myText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.common_calculation, container, false);

        details = view.findViewById(R.id.details_dropdown);
        details.setOnClickListener(new OnClickListener() {
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
        discounts.setOnClickListener(new OnClickListener() {
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
        calculate.setOnClickListener(new OnClickListener() {
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
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sellingPrice.requestFocus();
                sellingPrice.setText("");
                categories.setSelection(0);
                subCategories.setSelection(0);
                purchasePrice.setText("");
                gstOnProduct.setSelection(0);
                inwardShipping.setText("");
                packagingExpenses.setText("");
                labour.setText("");
                storageFee.setText("");
                otherCharges.setText("");
                discountByPrice.setText("");
                discountByPercentage.setText("");
                result_card.setVisibility(View.GONE);
            }
        });

        save = view.findViewById(R.id.save);
        save.setOnClickListener(new OnClickListener() {
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
        sendEmail.setOnClickListener(new OnClickListener() {
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

        categories = view.findViewById(R.id.category);

        list = SharedPrefManager.getInstance(getActivity()).getList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_activated_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        categories.setAdapter(adapter);

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryFinal = list.get(position);
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
        scrollView = view.findViewById(R.id.scrollView);

        productDetailsCard = view.findViewById(R.id.productDetailsCard);
        expensesCard = view.findViewById(R.id.expensesCard);
        discountsCard = view.findViewById(R.id.discountsCard);
        details = view.findViewById(R.id.details_dropdown);
        expenses = view.findViewById(R.id.expenses_dropdown);
        discounts = view.findViewById(R.id.discounts_dropdown);
        weightBunch = view.findViewById(R.id.weightBunch);
        lengthBunch = view.findViewById(R.id.lengthBunch);
        breadthBunch = view.findViewById(R.id.breadthBunch);
        heightBunch = view.findViewById(R.id.heightBunch);
        subCategoryBunch = view.findViewById(R.id.subCategoryBunch);
        subCategoryBunch.setVisibility(View.VISIBLE);
        customerTypeBunch = view.findViewById(R.id.customerTypeBunch);
        payModeBunch = view.findViewById(R.id.payModeBunch);
        courierBunch = view.findViewById(R.id.courierBunch);

        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setVisibility(View.GONE);
        line1 = view.findViewById(R.id.line1);
        line2 = view.findViewById(R.id.line2);
        line3 = view.findViewById(R.id.line3);
        line4 = view.findViewById(R.id.line4);
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
        progressBar = view.findViewById(R.id.loader);
        textView = view.findViewById(R.id.calculate_textview);

        if (SharedPrefManager.getInstance(getActivity()).getFlag().equals("true")) {
            Bundle bundle = this.getArguments();
            ArrayList<String> bundle_input = bundle.getStringArrayList("input");
            int selection = adapter.getPosition(bundle_input.get(0));
            categories.setSelection(selection);

            selection = adapter.getPosition(bundle_input.get(1));
            subCategories.setSelection(selection);
            sellingPrice.setText(bundle_input.get(2));
            purchasePrice.setText(bundle_input.get(3));
            int value = Integer.parseInt(bundle_input.get(4));
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
            inwardShipping.setText(bundle_input.get(5));
            packagingExpenses.setText(bundle_input.get(6));
            labour.setText(bundle_input.get(7));
            storageFee.setText(bundle_input.get(8));
            otherCharges.setText(bundle_input.get(9));
            discountByPrice.setText(bundle_input.get(10));
            discountByPercentage.setText(bundle_input.get(11));

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

        Call<CommonOutputModel> call = RetrofitClient
                .getInstance().getApi().calculate(category, subCategory, number1, number3, number2, number4, number5, number6, number7, number8, number10, number9);

        call.enqueue(new Callback<CommonOutputModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonOutputModel> call, @NotNull Response<CommonOutputModel> response) {
                if (response.isSuccessful()) {
                    commonOutputModel = response.body();
                    ButtonFinished();

                    result_card.setVisibility(View.VISIBLE);
                    itemList.setVisibility(View.VISIBLE);

                    output text1 = new output("Commission Fees", commonOutputModel.getCommissionFees() + " ₹");
                    output text2 = new output("Shipping Fees", commonOutputModel.getShippingFees() + " ₹");
                    output text3 = new output("Commission Fees + Shipping Fees", commonOutputModel.getCS() + " ₹");
                    output text4 = new output("GST On Commission Fees + Shipping Fees", commonOutputModel.getGstOnCS() + " ₹");
                    output text5 = new output("Total Charges", commonOutputModel.getTotalCharges() + " ₹");
                    output text6 = new output("Bank Settlement", commonOutputModel.getBankSettlement() + " ₹");
                    output text7 = new output("GST Claim", commonOutputModel.getGstClaim() + " ₹");
                    output text8 = new output("GST Payable", commonOutputModel.getGstPayable() + " ₹");
                    output text9 = new output("Total GST Payable", commonOutputModel.getTotalGstPayable() + " ₹");
                    output text10 = new output("TCS", commonOutputModel.getTcs() + " ₹");
                    output text11 = new output("Profit", commonOutputModel.getProfit() + " ₹");
                    output text12 = new output("Profit Percentage", commonOutputModel.getProfitPercentage() + " %");

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

        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .saved(title, category, subCategory, sellingPrice, gstOnProduct, productPriceWithoutGst, inwardShipping, packagingExpense, labour, storageFee,
                        other, discountPercent, discountAmount);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(@NotNull Call<MessageResponse> call, @NotNull Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    MessageResponse dr = response.body();
                    assert dr != null;
                    if (dr.getMessage().equals("data_saved")) {
                        save.setImageResource(R.drawable.ic_bookmark);
                        save.setEnabled(false);
                        new SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE)
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
        String content = "MEESHO" + "\n\n" +
                "INPUT" + "\n" +
                "Category: " + categoryFinal + "\n" +
                "Sub Category: " + subCategoryFinal + "\n" +
                "Selling Price: " + " ₹" + sellingPrice.getText().toString().trim() + "\n" +
                "Product Price Without GST: " + " ₹" + purchasePrice.getText().toString().trim() + "\n" +
                "GST On Product: " + spinner_ans + " %" + "\n" +
                "Inward Shipping: " + " ₹" + inwardShipping.getText().toString().trim() + "\n" +
                "Packaging Expense: " + " ₹" + packagingExpenses.getText().toString().trim() + "\n" +
                "Labour: " + " ₹" + labour.getText().toString().trim() + "\n" +
                "Storage Fee: " + " ₹" + storageFee.getText().toString().trim() + "\n" +
                "Other: " + " ₹" + otherCharges.getText().toString().trim() + "\n" +
                "Discount Amount: " + " ₹" + discountByPrice.getText().toString().trim() + "\n" +
                "Discount Percent: " + discountByPercentage.getText().toString().trim() + " %" + "\n\n" +
                "OUTPUT" + "\n" +
                "Commission Fees: " + " ₹" + commonOutputModel.getCommissionFees() + "\n" +
                "Shipping Fees: " + " ₹" + commonOutputModel.getShippingFees() + "\n" +
                "Commission Fees + Shipping Fees: " + " ₹" + commonOutputModel.getCS() + "\n" +
                "GST On Commission Fees + Shipping Fees: " + " ₹" + commonOutputModel.getGstOnCS() + "\n" +
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

    public void subCategories(String categoryFinal) {
        Call<subCategory> call;
        call = RetrofitClient.getInstance().getApi().getSubCategoriesMeesho(categoryFinal);
        call.enqueue(new Callback<subCategory>() {
            @Override
            public void onResponse(Call<subCategory> call, Response<subCategory> response) {
                if (response.isSuccessful()) {
                    subCategory subCategory = response.body();
                    ArrayList<String> list = new ArrayList<>();
                    for (String s : subCategory.getSubCategories()) {
                        list.add(s);
                    }
                    SharedPrefManager.getInstance(getActivity())
                            .saveSubList(list);
                    subList = SharedPrefManager.getInstance(getActivity()).getSubList();
                    try {
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_activated_1, list);
                        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
                        subCategories.setAdapter(adapter2);
                    }catch (Exception e){

                    }
                } else if (response.code() == 401) {
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