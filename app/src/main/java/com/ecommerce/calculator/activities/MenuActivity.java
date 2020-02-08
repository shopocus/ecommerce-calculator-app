package com.ecommerce.calculator.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.calculator.R;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.ecommerce.calculator.models.menu;
import com.ecommerce.calculator.storage.SharedPrefManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import  android.widget.GridView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    RecyclerView mrecyclerView;
    HolderAdapter holderAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shopocus");
        toolbar.setSubtitle("Business Calculator");

        mrecyclerView = findViewById(R.id.menu);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        holderAdapter = new HolderAdapter(this, getMyList());
        mrecyclerView.setAdapter(holderAdapter);
    }

    private ArrayList<menu> getMyList(){
        ArrayList<menu> menu = new ArrayList<>();

        menu m = new menu();
        m.setTitle("Meesho");
        m.setImg(R.drawable.meesho);
        menu.add(m);

        m = new menu();
        m.setTitle("Club Factory");
        m.setImg(R.drawable.myntra);
        menu.add(m);

        m = new menu();
        m.setTitle("Flipkart");
        m.setImg(R.drawable.flipkart);
        menu.add(m);

        m = new menu();
        m.setTitle("Amazon");
        m.setImg(R.drawable.amazon);
        menu.add(m);

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
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(MenuActivity.this).clear();
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /*ListView listView;
    String nTitle[] = {"Meesho","Flipkart","Amazon","Myntra","Paytm"};
    //String nDescription[] = {"subtitle","subtitle","subtitle","subtitle","subtitle"};
    //int images[] = {R.drawable.meesho,R.drawable.flipkart,R.drawable.amazon,R.drawable.myntra,R.drawable.paytm};


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listView = findViewById(R.id.listview);
        MyAdapter adapter = new MyAdapter(this, nTitle);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(MenuActivity.this, FragmentSelection.class);
                    startActivity(intent);
                }
                else {

                }
            }
        });
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setSupportActionBar(toolbar);
    }

    class MyAdapter extends ArrayAdapter<String>{

        Context context;
        String rTitle[];
        //String rDescription[];
        //int rImgs[];

        MyAdapter(Context c,String title[]){
            super(c, R.layout.row, R.id.textview1, title);
            this.context = c;
            this.rTitle = title;
            //this.rDescription = description;
            //this.rImgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row,parent,false);
            //ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textview1);
            //TextView myDescription = row.findViewById(R.id.textview2);
            //images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            //myDescription.setText(rDescription[position]);
            return row;
        }
    }*/
}
