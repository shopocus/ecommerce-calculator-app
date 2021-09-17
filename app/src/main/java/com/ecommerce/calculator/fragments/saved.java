package com.ecommerce.calculator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.calculator.activities.HomeScreen;
import com.ecommerce.calculator.adapter.TitleAdapter;
import com.ecommerce.calculator.R;

import java.util.List;

import com.ecommerce.calculator.api.RetrofitClient;
import com.ecommerce.calculator.models.Title;
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
    LinearLayout linearLayout;

    private RecyclerView recyclerView;
    private TitleAdapter adapter;
    private List<Title> titleList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.saved_list, container, false);

        linearLayout = view.findViewById(R.id.linearlayout);

        searchBar = view.findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null)
                    adapter.getFilter().filter(newText);
                return false;
            }
        });

        loader = view.findViewById(R.id.loader);

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        text = view.findViewById(R.id.text);
        recyclerView = view.findViewById(R.id.title_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        String company = SharedPrefManager.getInstance(getActivity()).getCompany();

        Call<savedTitleResponse> call = RetrofitClient.getInstance().getApi().getTitles(company);

        call.enqueue(new Callback<savedTitleResponse>() {
            @Override
            public void onResponse(Call<savedTitleResponse> call, Response<savedTitleResponse> response) {

                if (response.isSuccessful()) {
                    savedTitleResponse savedTitleResponse = response.body();

                    if (savedTitleResponse.getMessage().equals("data_found")) {
                        text.setVisibility(View.GONE);
                        titleList = savedTitleResponse.getTitle();
                        recyclerView.setVisibility(View.VISIBLE);
                        loader.setVisibility(View.GONE);
                        adapter = new TitleAdapter(getActivity(), titleList, getFragmentManager());
                        recyclerView.setAdapter(adapter);
                    }
                } else if (response.code() == 401) {
                    Toast.makeText(getContext(), "Session Expire! Please Login Again", Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(getContext()).clear();
                    Intent intent_logout = new Intent(getContext(), HomeScreen.class);
                    intent_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_logout);
                } else if (response.code() == 409) {
                    text.setVisibility(View.VISIBLE);
                    loader.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<savedTitleResponse> call, Throwable t) {
                loader.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}