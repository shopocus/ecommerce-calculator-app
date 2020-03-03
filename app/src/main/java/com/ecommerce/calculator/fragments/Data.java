package com.ecommerce.calculator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.activities.FragmentSelection;
import com.ecommerce.calculator.storage.SharedPrefManager;
import android.widget.Button;

public class Data extends DialogFragment {

    private TextView textViewTitle, textViewSellingPrice, textViewPurchasePrice, textViewGst, textViewInwardShipping, textViewPackagingExpenses, textViewLabour,
            textViewStorageFees, textViewOther, textViewByPrice, textViewByPercentage, textViewBankSettlement, textViewTotalMeeshoCommision, textViewProfit,
            textViewTotalGstPayable, textViewTcs, textViewGstPayable, textViewGstClaim, textViewProfitPercentage;

    Button edit,close;

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.data_popup, container, false);

        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewSellingPrice = view.findViewById(R.id.textViewSellingPrice);
        textViewPurchasePrice = view.findViewById(R.id.textViewPurchasePrice);
        textViewGst = view.findViewById(R.id.textViewGst);
        textViewInwardShipping = view.findViewById(R.id.textViewInwardShipping);
        textViewPackagingExpenses = view.findViewById(R.id.textViewPackagingExpense);
        textViewLabour = view.findViewById(R.id.textViewLabour);
        textViewStorageFees = view.findViewById(R.id.textViewStorageFees);
        textViewOther = view.findViewById(R.id.textViewOther);
        textViewByPrice = view.findViewById(R.id.textViewByPrice);
        textViewByPercentage = view.findViewById(R.id.textViewByPercentage);
        textViewBankSettlement = view.findViewById(R.id.textViewBankSettlement);
        textViewTotalMeeshoCommision = view.findViewById(R.id.textViewMeeshoCommision);
        textViewProfit = view.findViewById(R.id.textViewProfit);
        textViewTotalGstPayable = view.findViewById(R.id.textViewTotalGstPayable);
        textViewTcs = view.findViewById(R.id.textViewTcs);
        textViewGstPayable = view.findViewById(R.id.textViewGstPayable);
        textViewGstClaim = view.findViewById(R.id.textViewGstClaim);
        textViewProfitPercentage = view.findViewById(R.id.textViewProfitPercentage);

        textViewTitle.setText(SharedPrefManager.getInstance(getActivity()).getData().getTitle());
        textViewSellingPrice.setText(SharedPrefManager.getInstance(getActivity()).getData().getSellingPrice());
        textViewPurchasePrice.setText(SharedPrefManager.getInstance(getActivity()).getData().getProductPriceWithoutGst());
        textViewGst.setText(SharedPrefManager.getInstance(getActivity()).getData().getGstOnProduct());
        textViewInwardShipping.setText(SharedPrefManager.getInstance(getActivity()).getData().getInwardShipping());
        textViewPackagingExpenses.setText(SharedPrefManager.getInstance(getActivity()).getData().getPackagingExpense());
        textViewLabour.setText(SharedPrefManager.getInstance(getActivity()).getData().getLabour());
        textViewStorageFees.setText(SharedPrefManager.getInstance(getActivity()).getData().getStorageFee());
        textViewOther.setText(SharedPrefManager.getInstance(getActivity()).getData().getOther());
        textViewByPrice.setText(SharedPrefManager.getInstance(getActivity()).getData().getDiscountAmount());
        textViewByPercentage.setText(SharedPrefManager.getInstance(getActivity()).getData().getDiscountPercent());
        textViewBankSettlement.setText(SharedPrefManager.getInstance(getActivity()).getData().getBankSettlement());
        textViewTotalMeeshoCommision.setText(SharedPrefManager.getInstance(getActivity()).getData().getTotalMeeshoCommision());
        textViewProfit.setText(SharedPrefManager.getInstance(getActivity()).getData().getProfit());
        textViewTotalGstPayable.setText(SharedPrefManager.getInstance(getActivity()).getData().getTotalGstPayable());
        textViewTcs.setText(SharedPrefManager.getInstance(getActivity()).getData().getTcs());
        textViewGstPayable.setText(SharedPrefManager.getInstance(getActivity()).getData().getGstPayable());
        textViewGstClaim.setText(SharedPrefManager.getInstance(getActivity()).getData().getGstClaim());
        textViewProfitPercentage.setText(SharedPrefManager.getInstance(getActivity()).getData().getProfitPercentage());

        close = view.findViewById(R.id.action_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        edit = view.findViewById(R.id.action_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag = "true";
                SharedPrefManager.getInstance(getActivity())
                        .saveFlag(flag);
                Intent intent = new Intent(getActivity(), FragmentSelection.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        return view;
    }
}