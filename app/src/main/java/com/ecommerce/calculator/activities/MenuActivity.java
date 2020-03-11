package com.ecommerce.calculator.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.calculator.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.ecommerce.calculator.adapter.HolderAdapter;
import com.ecommerce.calculator.models.menu;
import com.ecommerce.calculator.storage.SharedPrefManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    RecyclerView mrecyclerView;
    HolderAdapter holderAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shopocus");
        toolbar.setSubtitle("Business Calculator");

        String flag = "false";
        SharedPrefManager.getInstance(MenuActivity.this)
                .saveFlag(flag);

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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent = new Intent(MenuActivity.this, profile.class);
                startActivity(intent);
                break;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to Logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPrefManager.getInstance(MenuActivity.this).clear();
                                Intent intent_logout = new Intent(MenuActivity.this, LoginActivity.class);
                                intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent_logout);
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
                        MenuActivity.super.onBackPressed();
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
}