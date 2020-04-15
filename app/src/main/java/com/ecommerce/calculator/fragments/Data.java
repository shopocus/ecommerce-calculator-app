package com.ecommerce.calculator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.activities.FragmentSelection;
import com.ecommerce.calculator.activities.HomeScreen;
import com.ecommerce.calculator.activities.Menu;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.storage.SharedPrefManager;
import android.widget.Button;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Data extends DialogFragment {

    private TextView textViewTitle, textViewCategory, textViewSellingPrice, textViewPurchasePrice, textViewGst, textViewInwardShipping, textViewPackagingExpenses, textViewLabour,
            textViewStorageFees, textViewOther, textViewByPrice, textViewByPercentage, textViewBankSettlement, textViewTotalMeeshoCommision, textViewProfit,
            textViewTotalGstPayable, textViewTcs, textViewGstPayable, textViewGstClaim, textViewProfitPercentage;

    Button edit,close;
    CardView input_card,output_card;
    LinearLayout buttons;

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.data_popup, container, false);

        input_card = view.findViewById(R.id.input_card);
        output_card = view.findViewById(R.id.output_card);
        buttons = view.findViewById(R.id.buttons);
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewCategory = view.findViewById(R.id.textViewCategory);
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
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FetchData();
    }

    public void FetchData(){
        String email = SharedPrefManager.getInstance(getActivity()).getUser().getEmail();
        String title = SharedPrefManager.getInstance(getActivity()).getTitle();
        Call<TitleDataResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getData("meesho", title);

        call.enqueue(new Callback<TitleDataResponse>() {
            @Override
            public void onResponse(Call<TitleDataResponse> call, Response<TitleDataResponse> response) {
                if (response.isSuccessful()) {
                    TitleDataResponse td = response.body();
                    if (td.getTitle().equals(title)) {
                        SharedPrefManager.getInstance(getActivity())
                                .saveData(td);
                        input_card.setVisibility(getView().VISIBLE);
                        output_card.setVisibility(getView().VISIBLE);
                        buttons.setVisibility(getView().VISIBLE);
                        textViewTitle.setVisibility(getView().VISIBLE);
                        textViewTitle.setText(td.getTitle());
                        textViewCategory.setText(td.getCategory());
                        textViewSellingPrice.setText(td.getSellingPrice());
                        textViewPurchasePrice.setText(td.getProductPriceWithoutGst());
                        textViewGst.setText(td.getGstOnProduct());
                        textViewInwardShipping.setText(td.getInwardShipping());
                        textViewPackagingExpenses.setText(td.getPackagingExpense());
                        textViewLabour.setText(td.getLabour());
                        textViewStorageFees.setText(td.getStorageFee());
                        textViewOther.setText(td.getOther());
                        textViewByPrice.setText(td.getDiscountAmount());
                        textViewByPercentage.setText(td.getDiscountPercent());
                        textViewBankSettlement.setText(td.getBankSettlement());
                        textViewTotalMeeshoCommision.setText(td.getTotalMeeshoCommision());
                        textViewProfit.setText(td.getProfit());
                        textViewTotalGstPayable.setText(td.getTotalGstPayable());
                        textViewTcs.setText(td.getTcs());
                        textViewGstPayable.setText(td.getGstPayable());
                        textViewGstClaim.setText(td.getGstClaim());
                        textViewProfitPercentage.setText(td.getProfitPercentage());
                    }
                }else if(response.code() == 401){
                    Toast.makeText(getContext(), "Session Expire", Toast.LENGTH_LONG).show();
                    // Toast.makeText(Menu.this, "log out", Toast.LENGTH_LONG).show();
                    SharedPrefManager.getInstance(getContext()).clear();
                    Intent intent_logout = new Intent(getContext(), HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_logout);
                }
            }
            @Override
            public void onFailure(Call<TitleDataResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Server Error!", Toast.LENGTH_LONG).show();
            }
        });
    }

}