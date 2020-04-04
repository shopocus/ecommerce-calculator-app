package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.ecommerce.calculator.models.LoginResponse;
import com.ecommerce.calculator.api.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewRegister).setOnClickListener(this);
        findViewById(R.id.forgetPassword).setOnClickListener(this);

//        SharedPrefManager.getInstance(this)
//                .saveToken("false");
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
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

        TextView login = (TextView) findViewById(R.id.buttonLogin);
        login.setBackground(getResources().getDrawable(R.drawable.disabled_button_background));
        login.setText("Loading");
        login.setEnabled(false);

        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().login(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (loginResponse.getMessage().equals("loggedIn")) {
                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(loginResponse.getUser());
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    login.setBackground(getResources().getDrawable(R.drawable.button_background));
                    login.setText("Login");
                    login.setTextColor(getResources().getColor(R.color.white));
                    login.setEnabled(true);
                    Toast.makeText(LoginActivity.this, "Invalid  Details", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                login.setBackground(getResources().getDrawable(R.drawable.button_background));
                login.setText("Login");
                login.setTextColor(getResources().getColor(R.color.white));
                login.setEnabled(true);
                Toast.makeText(LoginActivity.this, "Internet  Disconnected", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                    userLogin();
                    break;
            case R.id.textViewRegister:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            case R.id.forgetPassword:
                startActivity(new Intent(this, ForgetPassword.class));
                break;
        }
    }
}