package com.ecommerce.calculator.models;

import android.view.View;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ecommerce.calculator.R;

public class progressButton {

    private CardView cardView;
    private ConstraintLayout layout;
    private ProgressBar progressBar;
    private TextView textView;

    public progressButton(Context ct, View view) {

        cardView = view.findViewById(R.id.cardView);
        layout = view.findViewById(R.id.constraint_layout);
        progressBar = view.findViewById(R.id.loader);
        textView = view.findViewById(R.id.calculate_textview);
    }

    public void ButtonActivated() {
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("");
    }

}
