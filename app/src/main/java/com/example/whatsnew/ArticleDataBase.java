package com.example.whatsnew;

import com.example.whatsnew.interfaces.IArticlesListUser;

import org.jetbrains.annotations.NotNull;

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
        mArticlesHashtable = new Hashtable<>();
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
            ArticleApiUtil.getInstance().getArticlesByCategory(iCategory, new Callback<JsonGetResponse>() {
                @Override
                public void onResponse(Call<JsonGetResponse> call,@NotNull Response<JsonGetResponse> response) {
                    if(response.isSuccessful())
                    {
                        if(response.body() !=null)
                        {
                            mArticlesHashtable.put(iCategory, response.body().getData());
                            iArticlesListUser.getList(response.body().getData(), true);
                        }
                    }
                    else
                    {
                        iArticlesListUser.getList(null, false);
                    }
                }

                @Override
                public void onFailure(Call<JsonGetResponse> call, Throwable t) {
                    iArticlesListUser.getList(null, false);
                }
            });
        }
    }
}
