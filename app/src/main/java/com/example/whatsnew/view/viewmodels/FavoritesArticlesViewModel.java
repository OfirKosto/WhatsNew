package com.example.whatsnew.view.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsnew.model.Article;
import com.example.whatsnew.model.database.DataBaseManager;

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
        mFavoritesArticlesList.postValue(DataBaseManager.getInstance().getFavoritesArticlesList());
    }

}