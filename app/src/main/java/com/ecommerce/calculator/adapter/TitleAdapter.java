package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.fragments.Data;
import com.ecommerce.calculator.models.DeleteDataResponse;
import com.ecommerce.calculator.models.Title;
import com.ecommerce.calculator.storage.SharedPrefManager;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
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

    String Tag = "index";

    Context mCtx;
    List<Title> titleList;
    List<Title> ListFull;
    FragmentManager fragmentManager;

    public static class TitleHolder extends RecyclerView.ViewHolder {
        public LinearLayout data_layout;
        public TextView textViewTitle,optionMenu,textViewDate;

        public TitleHolder(View itemView) {
            super(itemView);
            data_layout = itemView.findViewById(R.id.data_layout);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewDate = itemView.findViewById(R.id.date);
            optionMenu = itemView.findViewById(R.id.optionMenu);
        }
    }

    public TitleAdapter(Context mCtx, List<Title> titleList, FragmentManager fragmentManager) {
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
        String title = titleList.get(position).getTitle();
        String date = titleList.get(position).getDate();
        holder.textViewTitle.setText(title);
        holder.textViewDate.setText(date);
        final String[] newTitle = {""};

        holder.data_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newTitle[0].equals("")) {
                    SharedPrefManager.getInstance(mCtx).saveTitle(title);
                } else {
                    SharedPrefManager.getInstance(mCtx).saveTitle(newTitle[0]);
                }
                Data dialog = new Data();
                dialog.show(fragmentManager, "Data");
            }
        });

        holder.optionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(mCtx, holder.optionMenu);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.rename:
                                final EditText edittitle = new EditText(mCtx);
                                edittitle.setInputType(InputType.TYPE_CLASS_TEXT);
                                edittitle.setHint("Enter new Title");
                                edittitle.requestFocus();
                                edittitle.setPadding(70, 40, 50, 40);
                                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mCtx);
                                builder.setTitle("New Title")
                                        .setCancelable(false)
                                        .setView(edittitle)
                                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                newTitle[0] = edittitle.getText().toString();
                                                if (newTitle[0].equals("")) {
                                                    Toast.makeText(mCtx, "Invalid Title", Toast.LENGTH_LONG).show();
                                                } else {
                                                    String email = SharedPrefManager.getInstance(mCtx).getUser().getEmail();
                                                    Call<DeleteDataResponse> call = RetrofitClient.getInstance().getApi().update(email, title, newTitle[0]);

                                                    call.enqueue(new Callback<DeleteDataResponse>() {
                                                        @Override
                                                        public void onResponse(Call<DeleteDataResponse> call, Response<DeleteDataResponse> response) {
                                                            DeleteDataResponse deleteDataResponse = response.body();

                                                            if (deleteDataResponse.getMessage().equals("title_updated")) {
                                                                holder.textViewTitle.setText(newTitle[0]);
                                                                Toast.makeText(mCtx, "Updated", Toast.LENGTH_LONG).show();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<DeleteDataResponse> call, Throwable t) {
                                                            Toast.makeText(mCtx, "Internet Disconnected", Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                                }
                                            }
                                        })
                                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                androidx.appcompat.app.AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                break;

                            case R.id.delete:
                                androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(mCtx);
                                builder1.setMessage("Are you sure you want to Delete?")
                                        .setCancelable(false)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                        Toast.makeText(mCtx, "Internet Disconnected", Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                androidx.appcompat.app.AlertDialog alertDialog1 = builder1.create();
                                alertDialog1.show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
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
            List<Title> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(ListFull);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for (Title item : ListFull) {
                    if (item.getTitle().toLowerCase().contains(filter)) {
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

//    void update(String title, String newTitle) {
//        String email = SharedPrefManager.getInstance(mCtx).getUser().getEmail();
//        Call<DeleteDataResponse> call = RetrofitClient.getInstance().getApi().update(email, title, newTitle);
//
//        call.enqueue(new Callback<DeleteDataResponse>() {
//            @Override
//            public void onResponse(Call<DeleteDataResponse> call, Response<DeleteDataResponse> response) {
//                DeleteDataResponse deleteDataResponse = response.body();
//
//                if (deleteDataResponse.getMessage().equals("title_updated")) {
//                    Toast.makeText(mCtx, "Updated", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DeleteDataResponse> call, Throwable t) {
//                Toast.makeText(mCtx, "Internet Disconnected", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}