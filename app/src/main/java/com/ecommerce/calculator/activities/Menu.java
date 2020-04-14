package com.ecommerce.calculator.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.calculator.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import com.ecommerce.calculator.adapter.HolderAdapter;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.models.menu;
import com.ecommerce.calculator.storage.SharedPrefManager;

import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu extends AppCompatActivity {

    RecyclerView mrecyclerView;
    HolderAdapter holderAdapter;
    SearchView searchView;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shopocus");
        toolbar.setSubtitle("Business Calculator");

        String flag = "false";
        SharedPrefManager.getInstance(Menu.this)
                .saveFlag(flag);

//        String token = SharedPrefManager.getInstance(this).getToken();
//        Log.d("input", token);

        mrecyclerView = findViewById(R.id.menu);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        holderAdapter = new HolderAdapter(this, getMyList());
        mrecyclerView.setAdapter(holderAdapter);

        searchView = (SearchView)findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                holderAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private ArrayList<menu> getMyList(){
        ArrayList<menu> menu = new ArrayList<>();

        menu m = new menu();
        m.setTitle("Meesho");
        m.setImg(R.drawable.meesho);
        menu.add(m);

        m = new menu();
        m.setTitle("Club Factory");
        m.setImg(R.drawable.clubfactory);
        menu.add(m);

//        m = new menu();
//        m.setTitle("Flipkart");
//        m.setImg(R.drawable.flipkart);
//        menu.add(m);
//
//        m = new menu();
//        m.setTitle("Amazon");
//        m.setImg(R.drawable.amazon);
//        menu.add(m);

        return menu;
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent = new Intent(Menu.this, Profile.class);
                startActivity(intent);
                break;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to Logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logout();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Menu.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void logout(){

        Call<MessageResponse> call = RetrofitClient
                .getInstance().getApi().logout();

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();

                if (messageResponse.getMessage().equals("logged_out")) {
                    Toast.makeText(Menu.this, "log out", Toast.LENGTH_LONG).show();
                    SharedPrefManager.getInstance(Menu.this).clear();
                    Intent intent_logout = new Intent(Menu.this, HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_logout);
                } else {
                    Toast.makeText(Menu.this, "Invalid  Details", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(Menu.this, "Internet  Disconnected", Toast.LENGTH_LONG).show();
            }
        });
    }
}