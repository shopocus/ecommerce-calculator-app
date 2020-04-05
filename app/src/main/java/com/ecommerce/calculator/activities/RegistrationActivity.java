package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.models.MessageResponse;
import  com.ecommerce.calculator.storage.SharedPrefManager;
import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.api.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextMobile, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, MenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userSignUp() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String mobile_no = editTextMobile.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be atleast 6 character long");
            editTextPassword.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }

        if (mobile_no.isEmpty()) {
            editTextMobile.setError("Mobile Number required");
            editTextMobile.requestFocus();
            return;
        }

        if (mobile_no.length() != 10) {
            editTextMobile.setError("Not a Valid Mobile Number");
            editTextMobile.requestFocus();
            return;
        }

        TextView signup = (TextView) findViewById(R.id.buttonSignUp);
        signup.setBackground(getResources().getDrawable(R.drawable.disabled_button_background));
        signup.setText("Loading");
        signup.setEnabled(false);

        Call<MessageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .otpSend(email);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse dr = response.body();
                if (dr.getMessage().equals("yes")) {
                    Intent intent = new Intent(RegistrationActivity.this, Verification.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("mobile_no", mobile_no);
                    intent.putExtra("password", password);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                signup.setBackground(getResources().getDrawable(R.drawable.button_background));
                signup.setText("Sign Up");
                signup.setTextColor(getResources().getColor(R.color.white));
                signup.setEnabled(true);
                Toast.makeText(RegistrationActivity.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            }
        });
        /*Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .signup(name, email, mobile_no, password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse dr = response.body();
                if (dr.getMessage().equals("yes")) {
                    Toast.makeText(RegistrationActivity.this, "Account Created Successfully", Toast.LENGTH_LONG).show();
                    SharedPrefManager.getInstance(RegistrationActivity.this)
                            .saveUser(dr.getUser());
                    Intent intent = new Intent(RegistrationActivity.this, MenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if(dr.getMessage().equals("user_already_exist")){
                    signup.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    signup.setText("Sign Up");
                    signup.setTextColor(getResources().getColor(R.color.colorBase));
                    signup.setEnabled(true);
                    Toast.makeText(RegistrationActivity.this, "Account already exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                signup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                signup.setText("Sign Up");
                signup.setTextColor(getResources().getColor(R.color.colorBase));
                signup.setEnabled(true);
                Toast.makeText(RegistrationActivity.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                    userSignUp();
                    break;
//            case R.id.textViewLogin:
//                startActivity(new Intent(this, LoginActivity.class));
//                break;
        }
    }
}