package com.example.rickandmorty;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("character")
    Call<Result> getData();

    @GET("character/{id}")
    Call<Character> getCharacterById(@Path("id") int id);
}
