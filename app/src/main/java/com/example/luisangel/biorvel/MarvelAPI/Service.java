package com.example.luisangel.biorvel.MarvelAPI;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET(Constants.REST_ENDPOINT)
    Call<JsonObject> getDataPersonaje(@Query("name")String name,
                                      @Query("apikey")String apikey,
                                      @Query("ts") String ts,
                                      @Query("hash") String hash);
}
