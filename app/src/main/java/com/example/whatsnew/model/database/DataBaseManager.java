package com.example.whatsnew.model.database;

import com.example.whatsnew.model.Article;
import com.example.whatsnew.model.util.ArticleApiUtil;
import com.example.whatsnew.model.JsonGetResponse;
import com.example.whatsnew.model.interfaces.IArticlesListUser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Hashtable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This Singleton class is managing the database of the application.
 */

public class DataBaseManager {

    private static DataBaseManager mDataBaseManagerInstance = null;

    private Hashtable<String, ArrayList<Article>> mArticlesHashtable;
    private ArrayList<Article> mFavoritesArticlesList;

    private DataBaseManager()
    {
        mArticlesHashtable = new Hashtable<>();
        mFavoritesArticlesList = new ArrayList<>();
    }

    public static DataBaseManager getInstance()
    {
        if(mDataBaseManagerInstance == null)
        {
            mDataBaseManagerInstance = new DataBaseManager();
        }

        return mDataBaseManagerInstance;
    }

    // Using initializeFavoritesData in MainActivity onCreate only.
    public void initializeFavoritesData(){
        updateFavoriteList();
    }

    private void updateFavoriteList()
    {
        mFavoritesArticlesList = SaveFavoritesManager.loadFromFile();
    }

    // returns false if article already in favorite list and true if is not.
    public boolean addArticleToFavorite(Article iArticle)
    {
        if(SaveFavoritesManager.isContainArticleInFavorites(iArticle))
        {
            return false;
        }
        SaveFavoritesManager.addArticleToFavoritesFile(iArticle);
        updateFavoriteList();
        return true;
    }

    public void removeArticleFromFavorite(Article iArticle)
    {
        SaveFavoritesManager.removeArticleFromFavoritesFile(iArticle);
        updateFavoriteList();
    }

    public ArrayList<Article> getFavoritesArticlesList(){
        return mFavoritesArticlesList;
    }


    public void getArticlesByCategory(String iCategory, IArticlesListUser iArticlesListUser)
    {
        //if already got this category articles
        if (mArticlesHashtable.containsKey(iCategory))
        {
            iArticlesListUser.getList(mArticlesHashtable.get(iCategory), true);
        }
        else
        {
            // get request using retrofit and handle the result
            ArticleApiUtil.getInstance().getArticlesByCategory(iCategory, new Callback<JsonGetResponse>() {
                @Override
                public void onResponse(Call<JsonGetResponse> call,@NotNull Response<JsonGetResponse> response) {
                    if(response.isSuccessful())
                    {
                        if(response.body() != null)
                        {
                            if(response.body().getData() != null)
                            {
                                mArticlesHashtable.put(iCategory, response.body().getData());
                                iArticlesListUser.getList(response.body().getData(), true);
                            }
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
