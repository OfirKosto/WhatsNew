package com.example.whatsnew.viewmodels;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsnew.Article;
import com.example.whatsnew.ArticleDataBase;
import com.example.whatsnew.MainActivity;
import com.example.whatsnew.R;
import com.example.whatsnew.fragments.ArticlesDataFragment;
import com.example.whatsnew.interfaces.IArticlesListUser;

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
        ArticleDataBase.getInstance().getArticlesByCategory(iCategory, new IArticlesListUser() {
            @Override
            public void getList(ArrayList<Article> articles, boolean isSuccessful) {
                if(isSuccessful)
                {
                    mArticlesList.postValue(articles);
                }
                else
                {
                    mErrorLiveData.postValue(MainActivity.getAppContext().getResources().getString(R.string.error_get_data_msg));
                }
            }
        });
    }

}