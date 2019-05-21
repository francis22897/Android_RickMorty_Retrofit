package com.example.rickandmorty;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("character")
    Call<Result> getData();
}
