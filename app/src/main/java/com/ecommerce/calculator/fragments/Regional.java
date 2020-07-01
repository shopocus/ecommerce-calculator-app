package com.ecommerce.calculator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.activities.SavedData;
import com.ecommerce.calculator.adapter.OutputListAdapter;
import com.ecommerce.calculator.models.output;

import java.util.ArrayList;

public class Regional extends Fragment {

    ListView result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_common_fragment, container, false);

        result = view.findViewById(R.id.text_view_result);

        Bundle bundle = this.getArguments();
        ArrayList<String> bundle_regional = bundle.getStringArrayList("Regional");

        output text1 = new output("Commission Fees", bundle_regional.get(0) + " ₹");
        output text2 = new output("Shipping Fees", bundle_regional.get(1) + " ₹");

        ArrayList<output> outputList = new ArrayList<>();
        outputList.add(text1);
        outputList.add(text2);

        if(bundle_regional.size()==12){
            output text3 = new output("Commission Fees + Shipping Fees", bundle_regional.get(2) + " ₹");
            output text4 = new output("GST On Commission Fees + Shipping Fees", bundle_regional.get(3) + " ₹");
            output text5 = new output("Total Charges", bundle_regional.get(4) + " ₹");
            output text6 = new output("Bank Settlement", bundle_regional.get(5) + " ₹");
            output text7 = new output("GST Claim", bundle_regional.get(6) + " ₹");
            output text8 = new output("GST Payable", bundle_regional.get(7) + " ₹");
            output text9 = new output("Total GST Payable", bundle_regional.get(8) + " ₹");
            output text10 = new output("TCS", bundle_regional.get(9) + " ₹");
            output text11 = new output("Profit", bundle_regional.get(10) + " ₹");
            output text12 = new output("Profit Percentage", bundle_regional.get(11) + " %");

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
        }else{
            output text3 = new output("Prepaid Fees", bundle_regional.get(2) + " ₹");
            output text4 = new output("Commission Fees + Shipping Fees + Prepaid Fees", bundle_regional.get(3) + " ₹");
            output text5 = new output("GST On Commission Fees + Shipping Fees + Prepaid Fees", bundle_regional.get(4) + " ₹");
            output text6 = new output("Total Charges", bundle_regional.get(5) + " ₹");
            output text7 = new output("Bank Settlement", bundle_regional.get(6) + " ₹");
            output text8 = new output("GST Claim", bundle_regional.get(7) + " ₹");
            output text9 = new output("GST Payable", bundle_regional.get(8) + " ₹");
            output text10 = new output("Total GST Payable", bundle_regional.get(9) + " ₹");
            output text11 = new output("TCS", bundle_regional.get(10) + " ₹");
            output text12 = new output("Profit", bundle_regional.get(11) + " ₹");
            output text13 = new output("Profit Percentage", bundle_regional.get(12) + " %");

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
        }

        OutputListAdapter adapter = new OutputListAdapter(getActivity(), R.layout.output_row, outputList);
        result.setAdapter(adapter);
        result.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SavedData.setListViewHeightBasedOnChildren(result);
        return view;
    }
}
