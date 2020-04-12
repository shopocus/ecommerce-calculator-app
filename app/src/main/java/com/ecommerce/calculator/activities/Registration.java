package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.models.MessageResponse;
import  com.ecommerce.calculator.storage.SharedPrefManager;
import com.ecommerce.calculator.api.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextMobile, editTextPassword;
    ConstraintLayout constraintLayout;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" + "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{8,}" + "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextPassword = findViewById(R.id.editTextPassword);
        constraintLayout = findViewById(R.id.constraintLayout);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
    }

    private void userSignUp() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String mobile_no = editTextMobile.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty()  || mobile_no.isEmpty() || password.isEmpty()) {
            Snackbar snackbar = Snackbar.make(constraintLayout, "Please Enter All The Details", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || mobile_no.length() != 10) {
            Snackbar snackbar = Snackbar.make(constraintLayout, "Invalid Details", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        if(!PASSWORD_PATTERN.matcher(password).matches()){
            Snackbar snackbar = Snackbar.make(constraintLayout, "Too Weak Password", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        ImageButton signup = (ImageButton) findViewById(R.id.buttonSignUp);
        signup.setBackground(getResources().getDrawable(R.drawable.disabled_button_background));
        signup.setEnabled(false);

        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .otpSend(email);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse dr = response.body();
                signup.setBackground(getResources().getDrawable(R.drawable.button_background));
                signup.setEnabled(true);
                if (dr.getMessage().equals("otp_sent")) {
                    Intent intent = new Intent(Registration.this, RegistrationOtpVerification.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("mobile_no", mobile_no);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
                else if(dr.getMessage().equals("user_already_exist")) {
                    Snackbar snackbar = Snackbar.make(constraintLayout, "User Already Exist", Snackbar.LENGTH_SHORT);
                    View snackView = snackbar.getView();
                    TextView textView = snackView.findViewById(R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(15);
                    snackbar.show();
                }
                else{
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
                signup.setBackground(getResources().getDrawable(R.drawable.button_background));
                signup.setEnabled(true);
                Snackbar snackbar = Snackbar.make(constraintLayout, "Server Error!", Snackbar.LENGTH_SHORT);
                View snackView = snackbar.getView();
                TextView textView = snackView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                snackbar.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                    userSignUp();
                    break;
        }
    }
}