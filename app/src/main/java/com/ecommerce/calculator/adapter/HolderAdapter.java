package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.calculator.R;
import java.util.ArrayList;
import com.ecommerce.calculator.activities.FragmentSelection;
import com.ecommerce.calculator.holder.MyHolder;
import com.ecommerce.calculator.holder.itemClick;
import com.ecommerce.calculator.models.menu;

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
                    Intent intent = new Intent(c , FragmentSelection.class);
                    intent.putExtra(KEY,0);
                    c.startActivity(intent);
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
}