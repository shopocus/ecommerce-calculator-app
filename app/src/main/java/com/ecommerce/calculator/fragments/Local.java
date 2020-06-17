package com.ecommerce.calculator.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.activities.SavedData;
import com.ecommerce.calculator.adapter.OutputListAdapter;
import com.ecommerce.calculator.models.output;
import java.util.ArrayList;

public class Local extends Fragment {

    ListView result;
    ScrollView scrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_common_fragment, container, false);

        result = view.findViewById(R.id.text_view_result);
        scrollView = view.findViewById(R.id.scrollView);

        Bundle bundle = this.getArguments();
        ArrayList<String> bundle_local = bundle.getStringArrayList("Local");

        output text1 = new output("Bank Settlement", bundle_local.get(0));
        output text2 = new output("Total Commission", bundle_local.get(1));
        output text4 = new output("Total GST Payable", bundle_local.get(2));
        output text5 = new output("TCS", bundle_local.get(3));
        output text6 = new output("GST Payable", bundle_local.get(4));
        output text7 = new output("GST Claim", bundle_local.get(5));
        output text3 = new output("Profit", bundle_local.get(6));
        output text8 = new output("Profit Percentage", bundle_local.get(7));

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
        result.setAdapter(adapter);
        result.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SavedData.setListViewHeightBasedOnChildren(result);
        return view;
    }
}
