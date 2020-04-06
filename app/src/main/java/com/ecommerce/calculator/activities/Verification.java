package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.storage.SharedPrefManager;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verification extends AppCompatActivity {

    private TextView editTextEmail, counttime, verification_button;
    private EditText otp_digit_1, otp_digit_2, otp_digit_3, otp_digit_4, otp_digit_5, otp_digit_6;
    private String name, email, mobile_no, password, OTP;
    //private Button verification_button;
    ///private static final long START_TIME_IN_MILLIS = 100000;

    private boolean mTimerRunning;
    private long mTimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification);

        editTextEmail = findViewById(R.id.user_mail);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        mobile_no = intent.getStringExtra("mobile_no");
        password = intent.getStringExtra("password");
        editTextEmail.setText(email);

        otp_digit_1 = findViewById(R.id.otp_digit_1);
        otp_digit_1.requestFocus();
        otp_digit_2 = findViewById(R.id.otp_digit_2);
        otp_digit_3 = findViewById(R.id.otp_digit_3);
        otp_digit_4 = findViewById(R.id.otp_digit_4);
        otp_digit_5 = findViewById(R.id.otp_digit_5);
        otp_digit_6 = findViewById(R.id.otp_digit_6);

        otp_digit_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    otp_digit_1.clearFocus();
                    otp_digit_2.requestFocus();
                    otp_digit_2.setCursorVisible(true);
                }
            }
        });

        otp_digit_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    otp_digit_2.clearFocus();
                    otp_digit_3.requestFocus();
                    otp_digit_3.setCursorVisible(true);
                }
            }
        });

        otp_digit_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    otp_digit_3.clearFocus();
                    otp_digit_4.requestFocus();
                    otp_digit_4.setCursorVisible(true);
                }
            }
        });

        otp_digit_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    otp_digit_4.clearFocus();
                    otp_digit_5.requestFocus();
                    otp_digit_5.setCursorVisible(true);
                }
            }
        });

        otp_digit_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    otp_digit_5.clearFocus();
                    otp_digit_6.requestFocus();
                    otp_digit_6.setCursorVisible(true);
                }
            }
        });

        otp_digit_6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    otp_digit_6.clearFocus();
                }
            }
        });

        counttime=findViewById(R.id.timer);

        verification_button = findViewById(R.id.verification_button);
        verification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Verification();
            }
        });

        counttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resend();
            }
        });

        Timer();
    }

    private void Timer(){
        new CountDownTimer(120000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTimeLeftInMillis = 100000;
                mTimeLeftInMillis = millisUntilFinished;
                int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

                counttime.setText(timeLeftFormatted);
                //counttime.setText(String.valueOf(counter));
                //counter++;
            }
            @Override
            public void onFinish() {
                //counttime.setVisibility(View.GONE);
                //resend_otp.setVisibility(View.VISIBLE);
                counttime.setText("Resend OTP");
                counttime.setTextColor(getResources().getColor(R.color.black));
                counttime.setEnabled(true);
                otp_digit_1.setEnabled(false);
                otp_digit_2.setEnabled(false);
                otp_digit_3.setEnabled(false);
                otp_digit_4.setEnabled(false);
                otp_digit_5.setEnabled(false);
                otp_digit_6.setEnabled(false);
                verification_button.setBackground(getResources().getDrawable(R.drawable.disabled_button_background));
                verification_button.setText("Verify");
                verification_button.setEnabled(false);
                mTimerRunning = false;
                //counttime.setText("Finished");
            }
        }.start();
        mTimerRunning = true;
    }

    private void Verification(){

        String otp_1 = otp_digit_1.getText().toString().trim();
        String otp_2 = otp_digit_2.getText().toString().trim();
        String otp_3 = otp_digit_3.getText().toString().trim();
        String otp_4 = otp_digit_4.getText().toString().trim();
        String otp_5 = otp_digit_5.getText().toString().trim();
        String otp_6 = otp_digit_6.getText().toString().trim();

        if (otp_1.isEmpty() || otp_2.isEmpty() || otp_3.isEmpty() || otp_4.isEmpty() || otp_5.isEmpty() || otp_6.isEmpty()) {
            otp_digit_1.setError("OTP Required");
            otp_digit_1.requestFocus();
            return;
        }

        OTP = otp_1+otp_2+otp_3+otp_4+otp_5+otp_6;

        Toast.makeText(Verification.this, OTP, Toast.LENGTH_LONG).show();

        //verification_button = (Button)findViewById(R.id.buttonSignUp);
        verification_button.setBackground(getResources().getDrawable(R.drawable.disabled_button_background));
        verification_button.setText("Verifying");
        verification_button.setEnabled(false);

        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .otpVerification(email,Integer.valueOf(OTP));

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse dr = response.body();
                if (dr.getMessage().equals("matched")) {
                    Registration();
                }
                else{
                    verification_button.setBackground(getResources().getDrawable(R.drawable.button_background));
                    verification_button.setText("Verify");
                    verification_button.setTextColor(getResources().getColor(R.color.white));
                    verification_button.setEnabled(true);
                    Toast.makeText(Verification.this, dr.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                verification_button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                verification_button.setText("Verify");
                verification_button.setTextColor(getResources().getColor(R.color.white));
                verification_button.setEnabled(true);
                Toast.makeText(Verification.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Registration(){
        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .signup(name, email, mobile_no, password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse dr = response.body();
                if (dr.getMessage().equals("yes")) {
                    Toast.makeText(Verification.this, "Account Created Successfully", Toast.LENGTH_LONG).show();
                    SharedPrefManager.getInstance(Verification.this)
                            .saveUser(dr.getUser());
                    Intent intent = new Intent(Verification.this, MenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Verification.this, "Already Exits", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(Verification.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Resend(){
        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .otpSend(email);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse dr = response.body();
                if (dr.getMessage().equals("yes")) {
                    counttime.setEnabled(false);
                    verification_button.setBackground(getResources().getDrawable(R.drawable.button_background));
                    verification_button.setText("Verify");
                    verification_button.setEnabled(true);
                    otp_digit_1.setText("");
                    otp_digit_2.setText("");
                    otp_digit_3.setText("");
                    otp_digit_4.setText("");
                    otp_digit_5.setText("");
                    otp_digit_6.setText("");
                    otp_digit_1.setEnabled(true);
                    otp_digit_2.setEnabled(true);
                    otp_digit_3.setEnabled(true);
                    otp_digit_4.setEnabled(true);
                    otp_digit_5.setEnabled(true);
                    otp_digit_6.setEnabled(true);
                    Timer();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(Verification.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            }
        });
    }

}
