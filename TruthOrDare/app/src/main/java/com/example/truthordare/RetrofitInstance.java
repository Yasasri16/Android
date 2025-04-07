package com.example.truthordare;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static RetrofitInstance retrofitInstance;
    private ApiInterface apiInterface;

    private RetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.truthordarebot.xyz/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static synchronized RetrofitInstance getInstance() {
        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitInstance();
        }
        return retrofitInstance;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }
}
