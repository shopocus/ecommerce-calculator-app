package com.ecommerce.calculator.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.calculator.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import com.ecommerce.calculator.adapter.HolderAdapter;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.MessageResponse;
import com.ecommerce.calculator.models.menu;
import com.ecommerce.calculator.storage.SharedPrefManager;
import com.ecommerce.calculator.utils.NetworkHelper;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu extends AppCompatActivity {

    RecyclerView mrecyclerView;
    HolderAdapter holderAdapter;
    LinearLayout linearLayout;
    Boolean isNetworkOk;
    LoadingDialog loadingDialog;

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
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_menu);
        linearLayout = findViewById(R.id.linearlayout);

        isNetworkOk = NetworkHelper.isNetworkAvailable(this);
        if (!isNetworkOk) {
            Snackbar snackbar = Snackbar.make(linearLayout, "Please Connect to the Internet", Snackbar.LENGTH_SHORT);
            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            snackbar.show();
        }

        loadingDialog = new LoadingDialog(Menu.this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String flag = "false";
        SharedPrefManager.getInstance(Menu.this)
                .saveFlag(flag);

        mrecyclerView = findViewById(R.id.menu);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        mrecyclerView.setLayoutManager(layoutManager);

        holderAdapter = new HolderAdapter(this, getMyList(), loadingDialog);
        mrecyclerView.setAdapter(holderAdapter);
    }

    private ArrayList<menu> getMyList() {
        ArrayList<menu> menu = new ArrayList<>();

        menu m = new menu();
        m.setTitle("Meesho");
        m.setImg(R.drawable.meesho);
        menu.add(m);

//        m = new menu();
//        m.setTitle("Club Factory");
//        m.setImg(R.drawable.clubfactory);
//        menu.add(m);

        m = new menu();
        m.setTitle("Flipkart");
        m.setImg(R.drawable.flipkart);
        menu.add(m);

        m = new menu();
        m.setTitle("Amazon");
        m.setImg(R.drawable.amazon);
        menu.add(m);

        m = new menu();
        m.setTitle("Amazon Fba");
        m.setImg(R.drawable.amazon);
        menu.add(m);

        m = new menu();
        m.setTitle("ebay");
        m.setImg(R.drawable.ebay);
        menu.add(m);

        return menu;
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
                                loadingDialog.startLoadingDialog();
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

    void logout() {

        Call<MessageResponse> call = RetrofitClient
                .getInstance().getApi().logout();

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                loadingDialog.dismissDialog();
                if (response.isSuccessful()) {
                    MessageResponse messageResponse = response.body();
                    SharedPrefManager.getInstance(Menu.this).clear();
                    Intent intent_logout = new Intent(Menu.this, HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_logout);
                } else if (response.code() == 401) {
                    Snackbar snackbar = Snackbar.make(linearLayout, "Session Expire! Please Login Again", Snackbar.LENGTH_SHORT);
                    View snackView = snackbar.getView();
                    TextView textView = snackView.findViewById(R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(15);
                    snackbar.show();
                    SharedPrefManager.getInstance(Menu.this).clear();
                    Intent intent_logout = new Intent(Menu.this, HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_logout);
                }
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                loadingDialog.dismissDialog();
                Snackbar snackbar = Snackbar.make(linearLayout, "Please Connect to the Internet", Snackbar.LENGTH_SHORT);
                View snackView = snackbar.getView();
                TextView textView = snackView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                snackbar.show();
            }
        });
    }
}