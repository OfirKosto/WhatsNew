package com.example.whatsnew;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IArticleApi {

    @GET("/v2/top-headlines?category={category}&apiKey={API_KEY}")
    Call<ArrayList<Article>> getArticlesByCategory(@Path("category") String category, @Path("API_KEY") String apiKey);

}
