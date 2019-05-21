package com.example.rickandmorty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rview;
    RVAdapter adapter;
    List<Character> characters;

    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadJSON();
    }

    private void init(){
        btnGet = findViewById(R.id.btn_get);
        rview = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rview.setLayoutManager(llm);
        rview.setHasFixedSize(true);

        characters = new ArrayList<>();

        adapter = new RVAdapter(MainActivity.this, characters);
        rview.setAdapter(adapter);
    }

    private void loadJSON(){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit =  RetrofitInstance.getInstace();

        final RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Result> listCall = retrofitService.getData();
                listCall.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                       if(response.isSuccessful()){
                           Result result = response.body();
                           characters.clear();
                           characters.addAll(getCharacters(result.getResults(), 6));
                           adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.e("ERROR", t.getMessage());
                    }
                });
            }
        });
    }

    private List<Character> getCharacters(List<Character> list, int elements) {

        List<Character> newList = new ArrayList<>();
        Collections.shuffle(list);

        for (int i = 0; i < elements; i++) {
            newList.add(list.get(i));
        }

        return newList;
    }
}
