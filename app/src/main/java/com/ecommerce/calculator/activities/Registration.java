package com.ecommerce.calculator.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 7958cce5fecb1ce2efe325ca7f44614e7e551e6c
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ecommerce.calculator.R;
<<<<<<< HEAD
import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.storage.SharedPrefManager;
=======
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.api.RetrofitClient;
>>>>>>> 7958cce5fecb1ce2efe325ca7f44614e7e551e6c
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

<<<<<<< HEAD
import cn.pedant.SweetAlert.SweetAlertDialog;
=======
>>>>>>> 7958cce5fecb1ce2efe325ca7f44614e7e551e6c
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextMobile, editTextPassword;
    ConstraintLayout constraintLayout;
    LoadingDialog loadingDialog;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" + "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{8,}" + "$");

    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.toolbar);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_registration);

        loadingDialog = new LoadingDialog(Registration.this);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextPassword = findViewById(R.id.editTextPassword);
        constraintLayout = findViewById(R.id.constraintLayout);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.backGo).setOnClickListener(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void userSignUp() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String mobile_no = editTextMobile.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || mobile_no.isEmpty() || password.isEmpty()) {
            loadingDialog.dismissDialog();
            Snackbar snackbar = Snackbar.make(constraintLayout, "Please Enter All The Details", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        if (name.length() < 2) {
            loadingDialog.dismissDialog();
            Snackbar snackbar = Snackbar.make(constraintLayout, "Name is too Short", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || mobile_no.length() != 10) {
            loadingDialog.dismissDialog();
            Snackbar snackbar = Snackbar.make(constraintLayout, "Invalid Details", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            loadingDialog.dismissDialog();
            Snackbar snackbar = Snackbar.make(constraintLayout, "Password Consists Atleast One Upper Case Character, One Number, One Special Symbol ", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
            return;
        }

        ImageButton signup = findViewById(R.id.buttonSignUp);
        signup.setBackground(getResources().getDrawable(R.drawable.disabled_button_background));
        signup.setEnabled(false);

<<<<<<< HEAD
        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .signup(name, email, mobile_no, password, "app");

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse dr = response.body();
                loadingDialog.dismissDialog();
                signup.setBackground(getResources().getDrawable(R.drawable.button_background));
                signup.setEnabled(true);
                if (dr.getMessage().equals("signed_up")) {
                    SharedPrefManager.getInstance(Registration.this)
                            .saveUser(dr.getUser());
                    new SweetAlertDialog(Registration.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Account Created Successfully")
                            .setConfirmText("Continue")
                            .setConfirmButtonBackgroundColor(getResources().getColor(R.color.colorPrimaryDark))
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent = new Intent(Registration.this, Menu.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            })
                            .show();
=======
        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .otpSend(email, mobile_no);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse dr = response.body();
                signup.setBackground(getResources().getDrawable(R.drawable.button_background));
                signup.setEnabled(true);
                loadingDialog.dismissDialog();
                if (dr.getMessage().equals("otp_sent")) {
                    Intent intent = new Intent(Registration.this, RegistrationOtpVerification.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("mobile_no", mobile_no);
                    intent.putExtra("password", password);
                    startActivity(intent);
>>>>>>> 7958cce5fecb1ce2efe325ca7f44614e7e551e6c
                } else if (dr.getMessage().equals("user_already_exist")) {
                    Snackbar snackbar = Snackbar.make(constraintLayout, "Email Already Exist", Snackbar.LENGTH_SHORT);
                    View snackView = snackbar.getView();
                    TextView textView = snackView.findViewById(R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(15);
                    snackbar.show();
                } else if (dr.getMessage().equals("mobileNo_already_exists")) {
                    Snackbar snackbar = Snackbar.make(constraintLayout, "Mobile Number Already Exist", Snackbar.LENGTH_SHORT);
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
<<<<<<< HEAD
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
=======
            public void onFailure(Call<MessageResponse> call, Throwable t) {
>>>>>>> 7958cce5fecb1ce2efe325ca7f44614e7e551e6c
                loadingDialog.dismissDialog();
                signup.setBackground(getResources().getDrawable(R.drawable.button_background));
                signup.setEnabled(true);
                Snackbar snackbar = Snackbar.make(constraintLayout, "Please Connect to the Internet", Snackbar.LENGTH_SHORT);
                View snackView = snackbar.getView();
                TextView textView = snackView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                snackbar.show();
            }
        });
<<<<<<< HEAD

//        Call<MessageResponse> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .otpSend(email, mobile_no);
//
//        call.enqueue(new Callback<MessageResponse>() {
//            @Override
//            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
//                MessageResponse dr = response.body();
//                signup.setBackground(getResources().getDrawable(R.drawable.button_background));
//                signup.setEnabled(true);
//                loadingDialog.dismissDialog();
//                if (dr.getMessage().equals("otp_sent")) {
//                    Intent intent = new Intent(Registration.this, RegistrationOtpVerification.class);
//                    intent.putExtra("name", name);
//                    intent.putExtra("email", email);
//                    intent.putExtra("mobile_no", mobile_no);
//                    intent.putExtra("password", password);
//                    startActivity(intent);
//                } else if (dr.getMessage().equals("user_already_exist")) {
//                    Snackbar snackbar = Snackbar.make(constraintLayout, "Email Already Exist", Snackbar.LENGTH_SHORT);
//                    View snackView = snackbar.getView();
//                    TextView textView = snackView.findViewById(R.id.snackbar_text);
//                    textView.setTextColor(Color.WHITE);
//                    textView.setTextSize(15);
//                    snackbar.show();
//                } else if (dr.getMessage().equals("mobileNo_already_exists")) {
//                    Snackbar snackbar = Snackbar.make(constraintLayout, "Mobile Number Already Exist", Snackbar.LENGTH_SHORT);
//                    View snackView = snackbar.getView();
//                    TextView textView = snackView.findViewById(R.id.snackbar_text);
//                    textView.setTextColor(Color.WHITE);
//                    textView.setTextSize(15);
//                    snackbar.show();
//                } else {
//                    Snackbar snackbar = Snackbar.make(constraintLayout, "Server Error!", Snackbar.LENGTH_SHORT);
//                    View snackView = snackbar.getView();
//                    TextView textView = snackView.findViewById(R.id.snackbar_text);
//                    textView.setTextColor(Color.WHITE);
//                    textView.setTextSize(15);
//                    snackbar.show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MessageResponse> call, Throwable t) {
//                loadingDialog.dismissDialog();
//                signup.setBackground(getResources().getDrawable(R.drawable.button_background));
//                signup.setEnabled(true);
//                Snackbar snackbar = Snackbar.make(constraintLayout, "Please Connect to the Internet", Snackbar.LENGTH_SHORT);
//                View snackView = snackbar.getView();
//                TextView textView = snackView.findViewById(R.id.snackbar_text);
//                textView.setTextColor(Color.WHITE);
//                textView.setTextSize(15);
//                snackbar.show();
//            }
//        });
=======
>>>>>>> 7958cce5fecb1ce2efe325ca7f44614e7e551e6c
    }

    @Override
    public void onClick(View v) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusedView = getCurrentFocus();
        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        switch (v.getId()) {
            case R.id.buttonSignUp:
                loadingDialog.startLoadingDialog();
                userSignUp();
                break;
            case R.id.backGo:
                finish();
                break;
        }
    }
}