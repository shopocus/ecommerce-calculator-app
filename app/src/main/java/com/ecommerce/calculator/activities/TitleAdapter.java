package com.ecommerce.calculator.activities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import java.util.List;
import com.ecommerce.calculator.R;
import android.widget.Filter;
import android.widget.Toast;

import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.fragments.saved;
import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TitleAdapter extends RecyclerView.Adapter<TitleHolder> implements Filterable {
    private static final String TAG = "input";
    public static final String KEY = "title";

    Context mCtx;
    List<String> titleList;
    List<String> ListFull;

    public TitleAdapter(Context mCtx, List<String> titleList) {
        this.mCtx = mCtx;
        this.titleList = titleList;
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
             //   Log.d(TAG, title);
                saved obj = new saved();
                obj.FetchData(title);
                Intent intent = new Intent(mCtx, Data.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mCtx.startActivity(intent);

//                    Intent intent = new Intent(mCtx , FragmentSelection.class);
//                    //intent.putExtra(KEY, 1);
//                    //intent.putExtra(KEY,"1");
//                    mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

//    public static class TitleHolder extends RecyclerView.ViewHolder {
//
//        TextView textViewTitle;
//
//        public TitleHolder(View itemView) {
//            super(itemView);
//
//            textViewTitle = itemView.findViewById(R.id.title);
//            //textViewEmail = itemView.findViewById(R.id.textViewEmail);
//            //textViewMobile = itemView.findViewById(R.id.textViewMobile);
//        }
//    }

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


//    public static Filter getFilter(){
//        return titleFilter;
//    }
//    public static Filter titleFilter = new Filter() {
//        @Override
//        public FilterResults performFiltering(CharSequence constraint) {
//            ArrayList<String> filteredList = new ArrayList<>();
//
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(ListFull);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (String item : ListFull) {
//                    if (item.toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                }
//            }
//
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//
//            return results;
//        }



//        @Override
//        public void publishResults(CharSequence constraint, FilterResults results) {
//            titleList.clear();
//            titleList.addAll((ArrayList)results.values);
//            notifyDataSetChanged();
//        }
//    };


/*import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.calculator.R;
import com.ecommerce.calculator.models.Title;

import java.util.ArrayList;

public class TitleAdapter extends RecyclerView.Adapter<TitleHolder> implements Filterable {

    Context c;
    public ArrayList<String> title;
    public ArrayList<String> ListFull;

    public TitleAdapter(Context c, ArrayList<String> title){
        this.c = c;
        this.title = title;
        ListFull = new ArrayList<>(title);
    }

    @NonNull
    @Override
    public TitleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.title_rows, null);
        return new TitleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleHolder titleHolder, int position) {
        //titleHolder.mTitle.setText(title.get(position).getTitle());
        String titles = title.get(position);
        titleHolder.mTitle.setText(titles);
//
//        myHolder.setItemClickListener(new itemClick() {
//            @Override
//            public void onItemClickListener(View v, int position) {
//                String gtitle = menu.get(position).getTitle();
//                BitmapDrawable bitmapDrawable = (BitmapDrawable)myHolder.mImageView.getDrawable();
//
//                Bitmap bitmap = bitmapDrawable.getBitmap();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//
//                byte[] bytes = stream.toByteArray();
//                Intent intent = new Intent(c, FragmentSelection.class);
//                intent.putExtra("iTitle", gtitle);
//                intent.putExtra("iImage", bytes);
//                c.startActivity(intent);
//            }
//        });

//        titleHolder.setItemClickListener(new itemClick() {
//            @Override
//            public void onItemClickListener(View v, int position) {
//                if (title.get(position).getTitle().equals("Meesho")){
//                    Intent intent = new Intent(c , FragmentSelection.class);
//                    c.startActivity(intent);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public Filter getFilter(){
        return titleFilter;
    }
    public Filter titleFilter = new Filter() {
        @Override
        public FilterResults performFiltering(CharSequence constraint) {
            ArrayList<String> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(ListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (String item : ListFull) {
                    if (item.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }



        @Override
        public void publishResults(CharSequence constraint, FilterResults results) {
            title.clear();
            title.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };
}*/
