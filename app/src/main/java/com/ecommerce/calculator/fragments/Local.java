package com.ecommerce.calculator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.adapter.OutputListAdapter;
import com.ecommerce.calculator.models.output;

import java.util.ArrayList;

public class Local extends Fragment {

    ListView itemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local, container, false);
        itemList = view.findViewById(R.id.text_view_result);

        output text1 = new output("Bank Settlement", String.valueOf(0));
        output text2 = new output("Total Meesho Commision", String.valueOf(0));
        output text3 = new output("Profit", String.valueOf(0));
        output text4 = new output("Total GST Payable", String.valueOf(0));
        output text5 = new output("TCS", String.valueOf(0));
        output text6 = new output("GST Payable", String.valueOf(0));
        output text7 = new output("GST Claim", String.valueOf(0));
        output text8 = new output("Profit Percentage", String.valueOf(0));

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
        return view;
    }
}
