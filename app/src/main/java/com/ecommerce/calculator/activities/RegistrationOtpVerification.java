package com.ecommerce.calculator.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationOtpVerification extends AppCompatActivity {

    private TextView editTextEmail, counttime, verification_button;
    private EditText otp_digit_1, otp_digit_2, otp_digit_3, otp_digit_4, otp_digit_5, otp_digit_6;
    private String name, email, mobile_no, password, OTP;
    ConstraintLayout constraintLayout;

    private boolean mTimerRunning;
    private long mTimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification);

        editTextEmail = findViewById(R.id.user_mail);
        constraintLayout = findViewById(R.id.constraintLayout);

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
                if (s.length() > 0) {
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
                if (s.length() > 0) {
                    otp_digit_2.clearFocus();
                    otp_digit_3.requestFocus();
                    otp_digit_3.setCursorVisible(true);
                } else if (s.length() == 0) {
                    otp_digit_2.clearFocus();
                    otp_digit_1.requestFocus();
                    otp_digit_1.setCursorVisible(true);
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
                if (s.length() > 0) {
                    otp_digit_3.clearFocus();
                    otp_digit_4.requestFocus();
                    otp_digit_4.setCursorVisible(true);
                } else if (s.length() == 0) {
                    otp_digit_3.clearFocus();
                    otp_digit_2.requestFocus();
                    otp_digit_2.setCursorVisible(true);
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
                if (s.length() > 0) {
                    otp_digit_4.clearFocus();
                    otp_digit_5.requestFocus();
                    otp_digit_5.setCursorVisible(true);
                } else if (s.length() == 0) {
                    otp_digit_4.clearFocus();
                    otp_digit_3.requestFocus();
                    otp_digit_3.setCursorVisible(true);
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
                if (s.length() > 0) {
                    otp_digit_5.clearFocus();
                    otp_digit_6.requestFocus();
                    otp_digit_6.setCursorVisible(true);
                } else if (s.length() == 0) {
                    otp_digit_5.clearFocus();
                    otp_digit_4.requestFocus();
                    otp_digit_4.setCursorVisible(true);
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
                if (s.length() > 0) {
                    otp_digit_6.clearFocus();
                } else if (s.length() == 0) {
                    otp_digit_6.clearFocus();
                    otp_digit_5.requestFocus();
                    otp_digit_5.setCursorVisible(true);
                }
            }
        });

        counttime = findViewById(R.id.timer);

        verification_button = findViewById(R.id.verification_button);
        verification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                View focusedView = getCurrentFocus();
                if (focusedView != null) {
                    inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                otp_digit_1.clearFocus();
                otp_digit_2.clearFocus();
                otp_digit_3.clearFocus();
                otp_digit_4.clearFocus();
                otp_digit_5.clearFocus();
                otp_digit_6.clearFocus();
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

    private void Timer() {
        new CountDownTimer(61000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTimeLeftInMillis = 60000;
                mTimeLeftInMillis = millisUntilFinished;
                int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

                counttime.setText(timeLeftFormatted);
                counttime.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }

            @Override
            public void onFinish() {
                counttime.setText("Resend OTP");
                counttime.setTextColor(getResources().getColor(R.color.link));
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
            }
        }.start();
        mTimerRunning = true;
    }

    private void Verification() {

        String otp_1 = otp_digit_1.getText().toString().trim();
        String otp_2 = otp_digit_2.getText().toString().trim();
        String otp_3 = otp_digit_3.getText().toString().trim();
        String otp_4 = otp_digit_4.getText().toString().trim();
        String otp_5 = otp_digit_5.getText().toString().trim();
        String otp_6 = otp_digit_6.getText().toString().trim();

        if (otp_1.isEmpty() || otp_2.isEmpty() || otp_3.isEmpty() || otp_4.isEmpty() || otp_5.isEmpty() || otp_6.isEmpty()) {
            Snackbar snackbar = Snackbar.make(constraintLayout, "Enter Valid OTP", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        OTP = otp_1 + otp_2 + otp_3 + otp_4 + otp_5 + otp_6;

        verification_button.setBackground(getResources().getDrawable(R.drawable.disabled_button_background));
        verification_button.setText("Verifying");
        verification_button.setEnabled(false);

        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .otpVerification(email, OTP);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse dr = response.body();

                if (dr.getMessage().equals("matched")) {
                    Registration();
                } else {
                    verification_button.setBackground(getResources().getDrawable(R.drawable.button_background));
                    verification_button.setText("Verify");
                    verification_button.setTextColor(getResources().getColor(R.color.white));
                    verification_button.setEnabled(true);
                    Snackbar snackbar = Snackbar.make(constraintLayout, "Wrong OTP", Snackbar.LENGTH_SHORT);
                    View snackView = snackbar.getView();
                    TextView textView = snackView.findViewById(R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(15);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                verification_button.setBackground(getResources().getDrawable(R.drawable.button_background));
                verification_button.setText("Verify");
                verification_button.setTextColor(getResources().getColor(R.color.white));
                verification_button.setEnabled(true);
                Snackbar snackbar = Snackbar.make(constraintLayout, "Please Connect to the Internet", Snackbar.LENGTH_SHORT);
                View snackView = snackbar.getView();
                TextView textView = snackView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                snackbar.show();
            }
        });
    }

    private void Registration() {

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .signup(name, email, mobile_no, password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse dr = response.body();
                verification_button.setBackground(getResources().getDrawable(R.drawable.button_background));
                verification_button.setText("Verify");
                verification_button.setTextColor(getResources().getColor(R.color.white));
                verification_button.setEnabled(true);
                if (dr.getMessage().equals("signed_up")) {
                    SharedPrefManager.getInstance(RegistrationOtpVerification.this)
                            .saveUser(dr.getUser());
                    new SweetAlertDialog(RegistrationOtpVerification.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Account Created Successfully")
                            .setConfirmText("Continue")
                            .setConfirmButtonBackgroundColor(getResources().getColor(R.color.colorPrimaryDark))
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent = new Intent(RegistrationOtpVerification.this, Menu.class);
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
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                verification_button.setBackground(getResources().getDrawable(R.drawable.button_background));
                verification_button.setText("Verify");
                verification_button.setTextColor(getResources().getColor(R.color.white));
                verification_button.setEnabled(true);
                Snackbar snackbar = Snackbar.make(constraintLayout, "Please Connect to the Internet", Snackbar.LENGTH_SHORT);
                View snackView = snackbar.getView();
                TextView textView = snackView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                snackbar.show();
            }
        });
    }

    private void Resend() {
        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .otpSend(email, mobile_no);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse dr = response.body();
                if (dr.getMessage().equals("otp_sent")) {
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
                Snackbar snackbar = Snackbar.make(constraintLayout, "Please Connect to the Internet", Snackbar.LENGTH_SHORT);
                View snackView = snackbar.getView();
                TextView textView = snackView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                snackbar.show();
            }
        });
    }

}
