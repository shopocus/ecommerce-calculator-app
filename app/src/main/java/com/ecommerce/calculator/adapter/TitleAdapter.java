package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.fragments.Data;
import com.ecommerce.calculator.models.DeleteDataResponse;
import com.ecommerce.calculator.storage.SharedPrefManager;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleHolder> implements Filterable {

    Context mCtx;
    List<String> titleList;
    List<String> ListFull;
    FragmentManager fragmentManager;

    public static class TitleHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public ImageButton delete;

        public TitleHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public TitleAdapter(Context mCtx, List<String> titleList, FragmentManager fragmentManager) {
        this.mCtx = mCtx;
        this.titleList = titleList;
        this.fragmentManager = fragmentManager;
        ListFull = new ArrayList<>(titleList);
    }

    @NonNull
    @Override
    public TitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_rows, parent, false);
        return new TitleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleHolder holder, int position) {
        String title = titleList.get(position);
        holder.textViewTitle.setText(title);
        holder.delete.setImageResource(R.drawable.ic_delete);

        holder.textViewTitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(mCtx).saveTitle(title);
                Data dialog = new Data();
                dialog.show(fragmentManager, "Data");
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = SharedPrefManager.getInstance(mCtx).getUser().getEmail();
                Call<DeleteDataResponse> call = RetrofitClient.getInstance().getApi().DeleteData(email, title);

                call.enqueue(new Callback<DeleteDataResponse>() {
                    @Override
                    public void onResponse(Call<DeleteDataResponse> call, Response<DeleteDataResponse> response) {
                        DeleteDataResponse deleteDataResponse = response.body();

                        if (deleteDataResponse.getMessage().equals("deleted")) {
                            titleList.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(mCtx, "Deleted", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteDataResponse> call, Throwable t) {
                        Toast.makeText(mCtx, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
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