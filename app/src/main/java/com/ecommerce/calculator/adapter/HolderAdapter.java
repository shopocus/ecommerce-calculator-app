package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.calculator.R;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.calculator.activities.FragmentSelection;
import com.ecommerce.calculator.activities.HomeScreen;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.holder.MyHolder;
import com.ecommerce.calculator.holder.itemClick;
import com.ecommerce.calculator.models.category;
import com.ecommerce.calculator.models.menu;
import com.ecommerce.calculator.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolderAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable {

    public static final String KEY = "title";

    Context c;
    ArrayList<menu> menu;
    ArrayList<menu> ListFull;

    public HolderAdapter(Context c, ArrayList<menu> menu){
        this.c = c;
        this.menu = menu;
        ListFull = new ArrayList<>(menu);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        myHolder.mTitle.setText(menu.get(position).getTitle());
        myHolder.mImageView.setImageResource(menu.get(position).getImg());
        myHolder.setItemClickListener(new itemClick() {
            @Override
            public void onItemClickListener(View v, int position) {
                if (menu.get(position).getTitle().equals("Meesho")){
                    SharedPrefManager.getInstance(c)
                            .saveCompany("meesho");
                    categories();
                }
                else {
                    Toast.makeText(c, "Coming Soon", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public Filter getFilter(){
        return menuFilter;
    }
    private Filter menuFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<menu> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(ListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (menu item : ListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            menu.clear();
            menu.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    protected void categories() {

        Call<category> call = RetrofitClient
                .getInstance().getApi().getCategories();

        call.enqueue(new Callback<category>() {
            @Override
            public void onResponse(Call<category> call, Response<category> response) {
                if(response.isSuccessful()) {
                    category category = response.body();
                    ArrayList<String> list = new ArrayList<>();
                    list.add("Select Category");
                    for (String s : category.getCategory()) {
                        list.add(s);
                    }
                    SharedPrefManager.getInstance(c)
                            .saveList(list);
                    Intent intent = new Intent(c , FragmentSelection.class);
                    intent.putExtra(KEY,0);
                   // intent.putStringArrayListExtra("list", list);
                    c.startActivity(intent);
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_activated_1, list);
//                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
//                    categories.setAdapter(adapter);

//                    categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            categoryFinal = list.get(position);
//                        }

//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//                        }
//                    });
                }else if(response.code() == 401){
                    Toast.makeText(c, "Session Expire", Toast.LENGTH_LONG).show();
                    // Toast.makeText(Menu.this, "log out", Toast.LENGTH_LONG).show();
                    SharedPrefManager.getInstance(c).clear();
                    Intent intent_logout = new Intent(c, HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    c.startActivity(intent_logout);
                }
            }

            @Override
            public void onFailure(Call<category> call, Throwable t) {

            }
        });
    }
}