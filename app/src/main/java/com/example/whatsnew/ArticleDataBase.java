package com.example.whatsnew;

import java.util.ArrayList;
import java.util.Hashtable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDataBase {

    private static ArticleDataBase mArticleDataBaseInstance = null;

    private Hashtable<String, ArrayList<Article>> mArticlesHashtable;

    private ArticleDataBase()
    {

    }

    public static ArticleDataBase getInstance()
    {
        if(mArticleDataBaseInstance == null)
        {
            mArticleDataBaseInstance = new ArticleDataBase();
        }

        return mArticleDataBaseInstance;
    }

    public void getArticlesByCategory(String iCategory, IArticlesListUser iArticlesListUser)
    {
        if (mArticlesHashtable.containsKey(iCategory))
        {
            iArticlesListUser.getList(mArticlesHashtable.get(iCategory), true);
        }
        else
        {
            ArticleApiUtil.getInstance().getArticlesByCategory(iCategory, new Callback<ArrayList<Article>>() {
                @Override
                public void onResponse(Call<ArrayList<Article>> call, Response<ArrayList<Article>> response) {
                    if(response.isSuccessful())
                    {
                        mArticlesHashtable.put(iCategory, response.body());
                        iArticlesListUser.getList(response.body(), true);
                    }
                    else
                    {
                        iArticlesListUser.getList(null, false);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Article>> call, Throwable t) {
                    iArticlesListUser.getList(null, false);
                }
            });
        }
    }
}
