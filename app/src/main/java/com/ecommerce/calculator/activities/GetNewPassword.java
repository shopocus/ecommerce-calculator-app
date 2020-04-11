package com.ecommerce.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.MessageResponse;

public class GetNewPassword extends AppCompatActivity {

    EditText newPassword, confirmPassword;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password);

        newPassword = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        Button update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });
    }

    void updatePassword(){
       // String emailSend = email.getText().toString().trim();
        String passwordSend = newPassword.getText().toString().trim();
        String confirmPasswordSend = confirmPassword.getText().toString().trim();
        Call<MessageResponse> call = RetrofitClient
                .getInstance().getApi().updatePassword(email, passwordSend);

        call.enqueue(new Callback<MessageResponse>() {

            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();

                if (messageResponse.getMessage().equals("password_changed")) {
                    Toast.makeText(GetNewPassword.this, "changed", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GetNewPassword.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(GetNewPassword.this, "Invalid  Details", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(GetNewPassword.this, "Internet  Disconnected", Toast.LENGTH_LONG).show();
            }
        });
    }
}
