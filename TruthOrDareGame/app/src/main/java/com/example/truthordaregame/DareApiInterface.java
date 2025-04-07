package com.example.truthordaregame;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DareApiInterface {
    @GET("dare")
    Call<Model> getDare(@Query("rating") String rating);
}