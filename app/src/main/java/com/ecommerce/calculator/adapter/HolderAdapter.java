package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.calculator.R;
import java.util.ArrayList;
import com.ecommerce.calculator.activities.FragmentSelection;
import com.ecommerce.calculator.activities.HomeScreen;
import com.ecommerce.calculator.activities.LoadingDialog;
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
    LoadingDialog loadingDialog;

    public HolderAdapter(Context c, ArrayList<menu> menu, LoadingDialog loadingDialog){
        this.c = c;
        this.menu = menu;
        ListFull = new ArrayList<>(menu);
        this.loadingDialog = loadingDialog;
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
                loadingDialog.startLoadingDialog();
                if (menu.get(position).getTitle().equals("Meesho")){
                    SharedPrefManager.getInstance(c)
                            .saveCompany("meesho");
                    categories();
                }
                else if (menu.get(position).getTitle().equals("Club Factory")){
                    SharedPrefManager.getInstance(c)
                            .saveCompany("clubFactory");
                    categories();
                }
                else if (menu.get(position).getTitle().equals("Flipkart")){
                    SharedPrefManager.getInstance(c)
                            .saveCompany("flipkart");
                    categories();
                }
                else {
                    loadingDialog.dismissDialog();
                    Toast.makeText(c, "Coming Soon", Toast.LENGTH_SHORT).show();
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
        Call<category> call;
        switch (SharedPrefManager.getInstance(c).getCompany()){
            case "meesho":
                call = RetrofitClient.getInstance().getApi().getCategories();
                break;
            case "clubFactory":
                call = RetrofitClient.getInstance().getApi().getCategoriesClubFactory();
                break;
            case "flipkart":
                call = RetrofitClient.getInstance().getApi().getCategoriesClubFactory();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + SharedPrefManager.getInstance(c).getCompany());
        }

        call.enqueue(new Callback<category>() {
            @Override
            public void onResponse(Call<category> call, Response<category> response) {
                loadingDialog.dismissDialog();
                if(response.isSuccessful()) {
                    category category = response.body();
                    ArrayList<String> list = new ArrayList<>();
                    for (String s : category.getCategory()) {
                        list.add(s);
                    }
                    SharedPrefManager.getInstance(c)
                            .saveList(list);
                    Intent intent = new Intent(c , FragmentSelection.class);
                    intent.putExtra(KEY,0);
                    c.startActivity(intent);
                }else if(response.code() == 401){
                    loadingDialog.dismissDialog();
                    Toast.makeText(c, "Session Expire! Please Login Again", Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(c).clear();
                    Intent intent_logout = new Intent(c, HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    c.startActivity(intent_logout);
                }
            }

            @Override
            public void onFailure(Call<category> call, Throwable t) {
                loadingDialog.dismissDialog();
                Toast.makeText(c, "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}