package com.ecommerce.calculator.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.animation.Animation;
import android.os.Bundle;
import android.text.InputType;

import com.ecommerce.calculator.models.progressButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.ecommerce.calculator.models.SaveResponse;
import com.ecommerce.calculator.activities.OutputListAdapter;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.CalculateResponse;
import com.ecommerce.calculator.models.output;
import com.ecommerce.calculator.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View.OnClickListener;

import java.util.ArrayList;

public class input extends Fragment implements View.OnClickListener {

    private static final String TAG = "input";

    private ImageButton details, expenses, discounts, save, sendEmail;
    private TextView pp, gst, transport, packaging, labour, storage, other, price, percentage, line1, line2, line3, line4, line5, line6,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9;
    private EditText num1, num2, num4, num5, num6, num7, num8, num9, num10;

    Spinner num3;
    ListView itemList;
    CardView result_card;
    Double[] items = new Double[8] ;
    private String myText;
    String gst_array[] = {"0", "5", "12", "18", "28"};
    String spinner_ans;
    //progressButton progressButton;
    private ProgressBar progressBar;
    private TextView textView;
    Animation fade_in;
    //ProgressBar progressBar;
   // output text1,text2,text3,text4,text5,text6,text7,text8;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu, container, false);

        itemList = (ListView) view.findViewById(R.id.text_view_result);
        result_card = (CardView) view.findViewById(R.id.result_card);

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
                num2.setText("");
                num3.setSelection(0);
                num4.setText("");
                num5.setText("");
                num6.setText("");
                num7.setText("");
                num8.setText("");
                num9.setText("");
                num10.setText("");
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
                mydialog.setView(title);

                mydialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myText = title.getText().toString();
                        //Toast.makeText(getActivity(), myText, Toast.LENGTH_LONG).show();
                        save();
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

            details = view.findViewById(R.id.details_dropdown);
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
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_activated_1, gst_array);
// Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
            num3.setAdapter(adapter);
            num3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            spinner_ans = gst_array[position];
                          //  Log.d(TAG,spinner_ans);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
// Apply the adapter to the spinner
           // num3.setAdapter(adapter);
            num4 = view.findViewById(R.id.number4);
            num5 = view.findViewById(R.id.number5);
            num6 = view.findViewById(R.id.number6);
            num7 = view.findViewById(R.id.number7);
            num8 = view.findViewById(R.id.number8);
            num9 = view.findViewById(R.id.number9);
            num10 = view.findViewById(R.id.number10);
            progressBar = view.findViewById(R.id.loader);
            textView = view.findViewById(R.id.calculate_textview);

            if(SharedPrefManager.getInstance(getActivity()).getFlag().equals("true")){
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
               // num3.setSelection(Integer.parseInt());
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
                //calculate();
//                rs1.setText(SharedPrefManager.getInstance(Data.this).getData().getBankSettlement());
//                rs2.setText(SharedPrefManager.getInstance(Data.this).getData().getTotalMeeshoCommision());
//                rs3.setText(SharedPrefManager.getInstance(Data.this).getData().getProfit());
//                rs4.setText(SharedPrefManager.getInstance(Data.this).getData().getTotalGstPayable());
//                rs5.setText(SharedPrefManager.getInstance(Data.this).getData().getTcs());
//                textViewGstPayable.setText(SharedPrefManager.getInstance(Data.this).getData().getGstPayable());
//                textViewGstClaim.setText(SharedPrefManager.getInstance(Data.this).getData().getGstClaim());
//                textViewProfitPercentage.setText(SharedPrefManager.getInstance(Data.this).getData().getProfitPercentage());
            }
           // progressBar = (ProgressBar)view.findViewById(R.id.spin_kit);
           // textViewResult = view.findViewById(R.id.text_view_resulta);

           // view.findViewById(R.id.reset).setOnClickListener(this);
           // view.findViewById(R.id.calculate).setOnClickListener(this);
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

        Call<CalculateResponse> call = RetrofitClient
                .getInstance().getApi().calculate(number1, number3, number2, number4, number5, number6, number7, number8, number10, number9);

        call.enqueue(new Callback<CalculateResponse>() {
            @Override
            public void onResponse(Call<CalculateResponse> call, Response<CalculateResponse> response) {
                CalculateResponse CalculateResponse = response.body();
                Toast.makeText(getActivity(), "OUTPUT", Toast.LENGTH_LONG).show();

                ButtonFinished();

                result_card.setVisibility(View.VISIBLE);

                items[0] = CalculateResponse.getBankSettlement();
                items[1] = CalculateResponse.getTotalMeeshoCommision();
                items[2] = CalculateResponse.getProfit();
                items[3] = CalculateResponse.getTotalGstPayable();
                items[4] = CalculateResponse.getTcs();
                items[5] = CalculateResponse.getGstPayable();
                items[6] = CalculateResponse.getGstClaim();
                items[7] = CalculateResponse.getProfitPercentage();

                output text1 = new output("Bank Settlement", String.valueOf(CalculateResponse.getBankSettlement()));
                output text2 = new output("Total Meesho Commision", String.valueOf(CalculateResponse.getTotalMeeshoCommision()));
                output text3 = new output("Profit", String.valueOf(CalculateResponse.getProfit()));
                output text4 = new output("Total GST Payable", String.valueOf(CalculateResponse.getTotalGstPayable()));
                output text5 = new output("TCS", String.valueOf(CalculateResponse.getTcs()));
                output text6 = new output("GST Payable", String.valueOf(CalculateResponse.getGstPayable()));
                output text7 = new output("GST Claim", String.valueOf(CalculateResponse.getGstClaim()));
                output text8 = new output("Profit Percentage", String.valueOf(CalculateResponse.getProfitPercentage()));

                ArrayList<output> outputList = new ArrayList<>();
                outputList.add(text1);
                outputList.add(text2);
                outputList.add(text3);
                outputList.add(text4);
                outputList.add(text5);
                outputList.add(text6);
                outputList.add(text7);
                outputList.add(text8);

                OutputListAdapter adapter = new OutputListAdapter(getActivity(), R.layout.output_row, outputList);
                itemList.setAdapter(adapter);

                //progressButton.ButtonFinished();

                save.setImageResource(R.drawable.ic_bookmark_border);
                save.setEnabled(true);
//
//                SharedPrefManager.getInstance(getActivity())
//                        .saveResult(text1.getAnswer(),text2.getAnswer(),text3.getAnswer(),text4.getAnswer(),text5.getAnswer(),text6.getAnswer(),text7.getAnswer(),text8.getAnswer());
                //OutputListAdapter adapter = new OutputListAdapter(this, R.layout.output_row, itemList);
                //ListView.setAdapter(adapter);
                //textViewResult.setText(SharedPrefManager.getInstance(getApplication()).getResult());
                // if(CalculateResponse.getMsg().equals("")) {
//                String content = "";
//                content += "bankSettlement: " + CalculateResponse.getBankSettlement() + "\n";
//                content += "totalMeeshoCommision: " + CalculateResponse.getTotalMeeshoCommision() + "\n";
//                content += "profit: " + CalculateResponse.getProfit() + "\n";
//                content += "totalGstPayable: " + CalculateResponse.getTotalGstPayable() + "\n";
//                content += "tcs: " + CalculateResponse.getTcs() + "\n";
//                content += "gstPayable: " + CalculateResponse.getGstPayable() + "\n";
//                content += "gstClaim: " + CalculateResponse.getGstClaim() + "\n";
//                content += "profitPercentage: " + CalculateResponse.getProfitPercentage() + "\n";
             //   textViewResult.append(content);
                //}else {
                //    Toast.makeText(calculation.this, CalculateResponse.getMsg(), Toast.LENGTH_LONG).show();
                //}
            }

            @Override
            public void onFailure(Call<CalculateResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Internet Disconnected", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void save(){

        String email = SharedPrefManager.getInstance(getActivity()).getUser().getEmail();
        String title = String.valueOf(myText);
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

        Call<SaveResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .saved(email, title, sellingPrice, gstOnProduct, productPriceWithoutGst, inwardShipping, packagingExpense, labour, storageFee, other, discountPercent, discountAmount, bankSettlement, totalMeeshoCommision, profit, totalGstPayable, tcs, gstPayable, gstClaim, profitPercentage);


        call.enqueue(new Callback<SaveResponse>() {
            @Override
            public void onResponse(Call<SaveResponse> call, Response<SaveResponse> response) {
                SaveResponse dr = response.body();
                if (dr.getMessage().equals("saved")) {
                    save.setImageResource(R.drawable.ic_bookmark);
                    save.setEnabled(false);
                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Title  Already  Exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SaveResponse> call, Throwable t) {

                Toast.makeText(getActivity(), "Internet  Disconnected", Toast.LENGTH_LONG).show();

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
        "Bank Settlement: " + String.valueOf(items[0]) + "\n" +
        "Total Meesho Commision: " + String.valueOf(items[1]) + "\n" +
        "Profit: " + String.valueOf(items[2]) + "\n" +
                "Total GST Payable: " + String.valueOf(items[3]) + "\n" +
        "Tcs: " + String.valueOf(items[4]) + "\n" +
        "GST Payable: " + String.valueOf(items[5]) + "\n" +
        "GST Claim: " + String.valueOf(items[6]) + "\n" +
        "Profit Percentage: " + String.valueOf(items[7]);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = content;
        String shareSub = "Your Bill";
        intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        intent.putExtra(intent.EXTRA_TEXT, shareBody);
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
