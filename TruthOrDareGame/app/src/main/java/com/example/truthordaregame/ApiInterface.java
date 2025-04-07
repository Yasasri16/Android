package com.example.truthordaregame;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("truth")
    Call<Model> getTruth(@Query("rating") String rating);
}

