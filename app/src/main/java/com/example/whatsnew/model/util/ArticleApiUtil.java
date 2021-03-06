package com.example.whatsnew.model.util;

import com.example.whatsnew.model.JsonGetResponse;
import com.example.whatsnew.model.interfaces.IArticleApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton class that use retrofit for get requests from api.
 */

public class ArticleApiUtil {

    private static ArticleApiUtil mArticleApiUtilInstance = null;

    private final String API_KEY = "09722cf46e2e480fa14d7e356f40fd91";
    private final String BASE_URL = "https://newsapi.org";
    private IArticleApi mCallService;

    private ArticleApiUtil()
    {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mCallService = mRetrofit.create(IArticleApi.class);
    }

    public static ArticleApiUtil getInstance()
    {
        if(mArticleApiUtilInstance == null)
        {
            mArticleApiUtilInstance = new ArticleApiUtil();
        }

        return mArticleApiUtilInstance;
    }

    public void getArticlesByCategory( String iCategory, Callback<JsonGetResponse> iCallback)
    {
        Call<JsonGetResponse> articlesCall = mCallService.getArticlesByCategory(iCategory,"us", API_KEY);
        articlesCall.enqueue(iCallback);
    }


}
