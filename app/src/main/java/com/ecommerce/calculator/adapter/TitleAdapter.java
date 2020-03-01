package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import java.util.List;
import com.ecommerce.calculator.R;
import android.widget.Filter;
import androidx.fragment.app.FragmentManager;
import com.ecommerce.calculator.holder.TitleHolder;
import com.ecommerce.calculator.fragments.Data;
import com.ecommerce.calculator.fragments.saved;
import com.ecommerce.calculator.holder.itemClick;

public class TitleAdapter extends RecyclerView.Adapter<TitleHolder> implements Filterable {

    Context mCtx;
    List<String> titleList;
    List<String> ListFull;
    FragmentManager fragmentManager;

    public TitleAdapter(Context mCtx, List<String> titleList, FragmentManager fragmentManager) {
        this.mCtx = mCtx;
        this.titleList = titleList;
        this.fragmentManager = fragmentManager;
        ListFull = new ArrayList<>(titleList);
    }

    @NonNull
    @Override
    public TitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_rows, null);
        return new TitleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleHolder holder, int position) {
        String title = titleList.get(position);
        holder.textViewTitle.setText(title);

        holder.setItemClickListener(new itemClick() {
            @Override
            public void onItemClickListener(View v, int position) {
                saved obj = new saved();
                obj.FetchData(title);
                Data dialog = new Data();
                dialog.show(fragmentManager, "Data");
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    @Override
    public Filter getFilter() {
        return titleFilter;
    }

    private Filter titleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(ListFull);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for (String item : ListFull) {
                    if (item.toLowerCase().contains(filter)) {
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
            titleList.clear();
            titleList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}