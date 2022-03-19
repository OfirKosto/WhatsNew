package com.example.whatsnew.view.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsnew.model.ApplicationContext;
import com.example.whatsnew.model.Article;
import com.example.whatsnew.model.database.DataBaseManager;
import com.example.whatsnew.R;
import com.example.whatsnew.model.interfaces.IArticlesListUser;

import java.util.ArrayList;

public class ArticlesDataViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Article>> mArticlesList;
    private MutableLiveData<String> mErrorLiveData;

    public ArticlesDataViewModel(){
        mArticlesList = new MutableLiveData<>();
        mErrorLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Article>> getArticlesList() {
        return mArticlesList;
    }

    public MutableLiveData<String> getErrorLiveData() {
        return mErrorLiveData;
    }

    public void getArticlesByCategory(String iCategory){
        DataBaseManager.getInstance().getArticlesByCategory(iCategory, new IArticlesListUser() {
            @Override
            public void getList(ArrayList<Article> articles, boolean isSuccessful) {
                if(isSuccessful)
                {
                    mArticlesList.postValue(articles);
                }
                else
                {
                    mErrorLiveData.postValue(ApplicationContext.getContext().getResources().getString(R.string.error_get_data_msg));
                }
            }
        });
    }

    public boolean addArticleToFavorites(Article iArticle){
        return DataBaseManager.getInstance().addArticleToFavorite(iArticle);
    }
}