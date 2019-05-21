package com.example.rickandmorty;

import android.content.Intent;
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

import java.io.Serializable;
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
    Retrofit retrofit;
    Gson gson;
    RetrofitService retrofitService;

    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadJSON();
        init();
    }

    private void init(){
        btnGet = findViewById(R.id.btn_get);
        rview = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rview.setLayoutManager(llm);
        rview.setHasFixedSize(true);

        characters = new ArrayList<>();

        adapter = new RVAdapter(MainActivity.this, characters, new CustomItemclick() {
            @Override
            public void onClick(View view, int id) {
                Call<Character> call = retrofitService.getCharacterById(id);
                call.enqueue(new Callback<Character>(){

                    @Override
                    public void onResponse(Call<Character> call, Response<Character> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putExtra("IMAGE", response.body().getImage());
                            intent.putExtra("NAME", response.body().getName());
                            intent.putExtra("ID", response.body().getId());
                            intent.putExtra("STATUS", response.body().getStatus());
                            intent.putExtra("SPECIES", response.body().getSpecies());
                            intent.putExtra("GENDER", response.body().getGender());
                            intent.putExtra("ORIGIN", response.body().getOrigin().getName());
                            intent.putExtra("LOCATION", response.body().getLocation().getName());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Character> call, Throwable t) {
                        Log.e("ERROR", t.getMessage());
                    }
                });
            }
        });
        rview.setAdapter(adapter);

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

    private void loadJSON(){

        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        retrofit =  RetrofitInstance.getInstace();

        retrofitService = retrofit.create(RetrofitService.class);
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
