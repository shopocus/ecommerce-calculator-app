package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.MessageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordGetEmail extends AppCompatActivity {

    EditText email,otp,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        email = findViewById(R.id.email);
      //  otp = findViewById(R.id.otp);
       // password = findViewById(R.id.password);

        TextView submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestOtp();
            }
        });

//        Button verify = findViewById(R.id.verify);
//        verify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                verifyOtp();
//            }
//        });

//        Button change = findViewById(R.id.change);
//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updatePassword();
//            }
//        });
    }

    void requestOtp(){
        String emailSend = email.getText().toString().trim();
        Call<MessageResponse> call = RetrofitClient
                .getInstance().getApi().forgetPasswordOtpSend(emailSend);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();

                if (messageResponse.getMessage().equals("otp_sent")) {
                    Toast.makeText(ForgotPasswordGetEmail.this, "otp send", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPasswordGetEmail.this, ForgotPasswordVerifyEmail.class);
                    intent.putExtra("email", emailSend);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgotPasswordGetEmail.this, "Invalid  Details", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(ForgotPasswordGetEmail.this, "Internet  Disconnected", Toast.LENGTH_LONG).show();
            }
        });
    }

    /*void verifyOtp(){
        String emailSend = email.getText().toString().trim();
        String otpSend = otp.getText().toString().trim();
        Toast.makeText(ForgetPassword.this, otpSend, Toast.LENGTH_LONG).show();
        Call<MessageResponse> call = RetrofitClient
                .getInstance().getApi().forgetPasswordOtpVerification(emailSend, otpSend);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();

                if (messageResponse.getMessage().equals("matched")) {
                    Toast.makeText(ForgetPassword.this, "matched", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ForgetPassword.this, messageResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(ForgetPassword.this, "Internet  Disconnected", Toast.LENGTH_LONG).show();
            }
        });

    }

    void updatePassword(){
        String emailSend = email.getText().toString().trim();
        String passwordSend = password.getText().toString().trim();
        Call<MessageResponse> call = RetrofitClient
                .getInstance().getApi().updatePassword(emailSend, passwordSend);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();

                if (messageResponse.getMessage().equals("password_changed")) {
                    Toast.makeText(ForgetPassword.this, "changed", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ForgetPassword.this, "Invalid  Details", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(ForgetPassword.this, "Internet  Disconnected", Toast.LENGTH_LONG).show();
            }
        });
    }*/
}
