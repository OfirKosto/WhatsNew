package com.example.whatsnew.database;

import androidx.room.Room;

import com.example.whatsnew.ApplicationContext;
import com.example.whatsnew.Article;
import com.example.whatsnew.ArticleApiUtil;
import com.example.whatsnew.JsonGetResponse;
import com.example.whatsnew.interfaces.IArticlesListUser;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void initializeFavoritesData(){
        updateFavoriteList();
    }

    private void updateFavoriteList()
    {
        mFavoritesArticlesList = SaveFavoritesManager.loadFromFile();
    }

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
