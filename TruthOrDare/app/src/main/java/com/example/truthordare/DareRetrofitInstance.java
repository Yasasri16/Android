package com.example.truthordare;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DareRetrofitInstance {
    private static DareRetrofitInstance dareRetrofitInstance;
    private DareApiInterface dareApiInterface;

    private DareRetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.truthordarebot.xyz/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dareApiInterface = retrofit.create(DareApiInterface.class);
    }

    public static synchronized DareRetrofitInstance getDareRetrofitInstance() {
        if (dareRetrofitInstance == null) {
            dareRetrofitInstance = new DareRetrofitInstance();
        }
        return dareRetrofitInstance;
    }

    public DareApiInterface getDareApiInterface() {
        return dareApiInterface;
    }
}
