package com.example.rickandmorty;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit retrofit =  new Retrofit.Builder().baseUrl(Utils.URL).addConverterFactory(GsonConverterFactory.create()).build();

    public static Retrofit getInstace(){
        return retrofit;
    }

}
