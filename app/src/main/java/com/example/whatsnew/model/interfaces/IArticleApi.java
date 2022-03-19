package com.example.whatsnew.model.interfaces;

import com.example.whatsnew.model.JsonGetResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IArticleApi {
    //category={category}&apiKey={API_KEY}
    @GET("/v2/top-headlines")
    Call<JsonGetResponse> getArticlesByCategory(@Query("category") String category,
                                                @Query("country") String country,
                                                @Query("apiKey") String apiKey);

}
