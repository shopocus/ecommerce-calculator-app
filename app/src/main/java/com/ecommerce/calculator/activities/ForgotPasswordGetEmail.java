package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.MessageResponse;
import com.google.android.material.snackbar.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordGetEmail extends AppCompatActivity {

    EditText email;
    TextView submit;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        email = findViewById(R.id.email);
        constraintLayout = findViewById(R.id.constraintLayout);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit.setBackground(getResources().getDrawable(R.drawable.disabled_button_background));
                submit.setText("Loading");
                submit.setEnabled(false);
                requestOtp();
            }
        });
    }

    void requestOtp(){

        String emailSend = email.getText().toString().trim().toLowerCase();

        if (!Patterns.EMAIL_ADDRESS.matcher(emailSend).matches() || emailSend.isEmpty()) {
            submit.setBackground(getResources().getDrawable(R.drawable.button_background));
            submit.setText("Send OTP");
            submit.setEnabled(true);
            Snackbar snackbar = Snackbar.make(constraintLayout, "Invalid Email Address", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        Call<MessageResponse> call = RetrofitClient
                .getInstance().getApi().forgetPasswordOtpSend(emailSend);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();
                submit.setBackground(getResources().getDrawable(R.drawable.button_background));
                submit.setText("Send OTP");
                submit.setEnabled(true);
                if (messageResponse.getMessage().equals("otp_sent")) {
                    Intent intent = new Intent(ForgotPasswordGetEmail.this, ForgotPasswordVerifyEmail.class);
                    intent.putExtra("email", emailSend);
                    startActivity(intent);
                } else if(messageResponse.getMessage().equals("Not_valid_user")) {
                    Snackbar snackbar = Snackbar.make(constraintLayout, "Account Does Not Exist", Snackbar.LENGTH_SHORT);
                    View snackView = snackbar.getView();
                    TextView textView = snackView.findViewById(R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(15);
                    snackbar.show();
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
                submit.setBackground(getResources().getDrawable(R.drawable.button_background));
                submit.setText("Send OTP");
                submit.setEnabled(true);
                Snackbar snackbar = Snackbar.make(constraintLayout, "Server Error!", Snackbar.LENGTH_SHORT);
                View snackView = snackbar.getView();
                TextView textView = snackView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                snackbar.show();
            }
        });
    }

}
