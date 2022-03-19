package com.example.whatsnew.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsnew.Article;
import com.example.whatsnew.database.DataBaseManager;

import java.util.ArrayList;

public class FavoritesArticlesViewModel extends ViewModel {

    MutableLiveData<ArrayList<Article>> mFavoritesArticlesList;

    public FavoritesArticlesViewModel()
    {
        mFavoritesArticlesList = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Article>> getFavoritesArticlesListLiveData(){
        return mFavoritesArticlesList;
    }

    public void getFavoritesArticlesList(){
        mFavoritesArticlesList.postValue(DataBaseManager.getInstance().getFavoritesArticlesList());
    }

    public void removeFromFavoritesArticlesList(Article iArticle){
        DataBaseManager.getInstance().removeArticleFromFavorite(iArticle);
    }

}