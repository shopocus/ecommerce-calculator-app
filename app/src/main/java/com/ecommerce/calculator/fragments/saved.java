package com.ecommerce.calculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.calculator.adapter.TitleAdapter;
import com.ecommerce.calculator.R;
import java.util.List;
import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.TitleDataResponse;
import com.ecommerce.calculator.models.savedTitleResponse;
import com.ecommerce.calculator.storage.SharedPrefManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.agrawalsuneet.dotsloader.loaders.TrailingCircularDotsLoader;

public class saved extends Fragment {

    SearchView searchBar;
    TextView text;
    TrailingCircularDotsLoader loader;

    private RecyclerView recyclerView;
    private TitleAdapter adapter;
    private List<String> titleList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.saved_list, container, false);

        searchBar = (SearchView)view.findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        loader = view.findViewById(R.id.loader);

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.title_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String email = SharedPrefManager.getInstance(getActivity()).getUser().getEmail();

        Call<savedTitleResponse> call = RetrofitClient.getInstance().getApi().getTitles(email);

        call.enqueue(new Callback<savedTitleResponse>() {
            @Override
            public void onResponse(Call<savedTitleResponse> call, Response<savedTitleResponse> response) {
                savedTitleResponse savedTitleResponse = response.body();

                if(savedTitleResponse.getMessage().equals("title_found")) {
                    titleList = savedTitleResponse.getTitle();
                    recyclerView.setVisibility(view.VISIBLE);
                    loader.setVisibility(view.GONE);
                    adapter = new TitleAdapter(getActivity(), titleList, getFragmentManager());
                    recyclerView.setAdapter(adapter);
                }
                else if(savedTitleResponse.getMessage().equals("no_title_exists")){
                    text = view.findViewById(R.id.text);
                    text.setVisibility(view.VISIBLE);
                    loader.setVisibility(view.GONE);
                }
            }

            @Override
            public void onFailure(Call<savedTitleResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void FetchData(String title){
        String email = SharedPrefManager.getInstance(getActivity()).getUser().getEmail();
        Call<TitleDataResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getData(email, title);

        call.enqueue(new Callback<TitleDataResponse>() {
            @Override
            public void onResponse(Call<TitleDataResponse> call, Response<TitleDataResponse> response) {
                TitleDataResponse td = response.body();
                if (td.getTitle().equals(title)) {
                    SharedPrefManager.getInstance(getActivity())
                            .saveData(td);
                }
            }
            @Override
            public void onFailure(Call<TitleDataResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Internet Disconnected", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        onViewCreated(getView(),null);
    }
}