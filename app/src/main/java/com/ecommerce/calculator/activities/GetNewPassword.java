package com.ecommerce.calculator.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.TextView;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.MessageResponse;
import com.google.android.material.snackbar.Snackbar;
import java.util.regex.Pattern;

public class GetNewPassword extends AppCompatActivity {

    EditText newPassword, confirmPassword;
    String email;
    TextView update;
    ConstraintLayout constraintLayout;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" + "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{8,}" + "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password);

        newPassword = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        constraintLayout = findViewById(R.id.constraintLayout);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                View focusedView = getCurrentFocus();
                if (focusedView != null) {
                    inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                updatePassword();
            }
        });
    }

    void updatePassword(){

        String passwordSend = newPassword.getText().toString().trim();
        String confirmPasswordSend = confirmPassword.getText().toString().trim();

        if(passwordSend.isEmpty() || confirmPasswordSend.isEmpty()){
            Snackbar snackbar = Snackbar.make(constraintLayout, "Please Enter The Required Fields", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        if(!passwordSend.equals(confirmPasswordSend)){
            Snackbar snackbar = Snackbar.make(constraintLayout, "Please Enter Same Password", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        if (!PASSWORD_PATTERN.matcher(passwordSend).matches()) {
            Snackbar snackbar = Snackbar.make(constraintLayout, "Too Weak Password", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        update.setBackground(getResources().getDrawable(R.drawable.disabled_button_background));
        update.setText("Updating");
        update.setEnabled(false);

        Call<MessageResponse> call = RetrofitClient
                .getInstance().getApi().updatePassword(email, passwordSend);

        call.enqueue(new Callback<MessageResponse>() {

            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();
                update.setBackground(getResources().getDrawable(R.drawable.button_background));
                update.setText("Update");
                update.setEnabled(true);
                if (messageResponse.getMessage().equals("password_changed")) {
                    new SweetAlertDialog(GetNewPassword.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Password Updated Successfully")
                            .setConfirmText("Continue")
                            .setConfirmButtonBackgroundColor(getResources().getColor(R.color.colorPrimaryDark))
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent = new Intent(GetNewPassword.this, HomeScreen.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            })
                            .show();
                } else {
                    Snackbar snackbar = Snackbar.make(constraintLayout, "Server Error!", Snackbar.LENGTH_SHORT);
                    View snackView = snackbar.getView();
                    TextView textView = snackView.findViewById(R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(15);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Snackbar snackbar = Snackbar.make(constraintLayout, "Server Error!", Snackbar.LENGTH_SHORT);
                View snackView = snackbar.getView();
                TextView textView = snackView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                snackbar.show();
                update.setBackground(getResources().getDrawable(R.drawable.button_background));
                update.setText("Update");
                update.setEnabled(true);
            }
        });
    }
}
