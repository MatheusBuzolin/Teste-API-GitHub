package com.mbappsoftware.mobiledevtest.data.client;

import com.mbappsoftware.mobiledevtest.data.api.RetrofitApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance;
    public RetrofitApi retrofitApi;

    private RetrofitClient() {
        retrofitApi = initRetrofit().create(RetrofitApi.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    Retrofit initRetrofit() {
        return new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    }
}

