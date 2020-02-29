package com.ecommerce.calculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.calculator.activities.TitleAdapter;
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

import static java.util.Locale.filter;

public class saved extends Fragment {

//    RecyclerView mrecyclerView;
//    TitleAdapter titleAdapter;
    SearchView searchBar;
//    ImageButton edit;

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

//        edit = view.findViewById(R.id.edit);
//        edit.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                //String title =
//            }
//        });


        //mrecyclerView = view.findViewById(R.id.title_list);
        //mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
              //  Toast.makeText(getActivity(), "ho gya", Toast.LENGTH_LONG).show();
//            @Override
//            public void onResponse(Call<savedTitleResponse> call, Response<savedTitleResponse> response) {

                titleList = savedTitleResponse.getTitle();
                adapter = new TitleAdapter(getActivity(), titleList,getFragmentManager());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<savedTitleResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void FetchData(String title){
        String email = SharedPrefManager.getInstance(getActivity()).getUser().getEmail();
        //String email = editTextEmail.getText().toString().trim();
        //String mobile_no = editTextMobile.getText().toString().trim();
        //String password = editTextPassword.getText().toString().trim();
        Call<TitleDataResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getData(email, title);

        call.enqueue(new Callback<TitleDataResponse>() {
            @Override
            public void onResponse(Call<TitleDataResponse> call, Response<TitleDataResponse> response) {
                TitleDataResponse td = response.body();
                if (td.getTitle().equals(title)) {
                    //Toast.makeText(getActivity(), "ho gya", Toast.LENGTH_LONG).show();
                    SharedPrefManager.getInstance(getActivity())
                            .saveData(td);
                }
//                else if(dr.getMessage().equals("user_already_exist")){
//                    signup.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//                    signup.setText("Sign Up");
//                    signup.setTextColor(getResources().getColor(R.color.colorBase));
//                    signup.setEnabled(true);
//                    Toast.makeText(MainActivity.this, "Account already exist", Toast.LENGTH_LONG).show();
                //}
            }

            @Override
            public void onFailure(Call<TitleDataResponse> call, Throwable t) {
//                signup.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//                signup.setText("Sign Up");
//                signup.setTextColor(getResources().getColor(R.color.colorBase));
//                signup.setEnabled(true);
                Toast.makeText(getActivity(), "Internet Disconnected", Toast.LENGTH_LONG).show();

            }
        });
    }
}


/*        titleAdapter = new TitleAdapter(getActivity(), getMyList());
        mrecyclerView.setAdapter(titleAdapter);

        searchBar = view.findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TitleAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }
    private ArrayList<String> getMyList() {

        ArrayList<String> title = new ArrayList<>();
        String email = SharedPrefManager.getInstance(getActivity()).getUser().getEmail();

        Call<savedTitleResponse> call = RetrofitClient
                .getInstance().getApi().getTitles(email);

        call.enqueue(new Callback<savedTitleResponse>() {
            @Override
            public void onResponse(Call<savedTitleResponse> call, Response<savedTitleResponse> response) {
                savedTitleResponse savedTitleResponse = response.body();
                Toast.makeText(getActivity(), "OUTPUT", Toast.LENGTH_LONG).show();
                for (String s : savedTitleResponse.getTitle()) {
                    title.add(s);
                    //System.out.println(s);
                }
            }

            @Override
            public void onFailure(Call<savedTitleResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Internet Disconnected", Toast.LENGTH_LONG).show();
            }
        });
        return title;
    }
 */