package com.example.whatsnew.model.interfaces;

import com.example.whatsnew.model.Article;

import java.util.ArrayList;

public interface IArticlesListUser {

    public void getList(ArrayList<Article> articles, boolean isSuccessful);
}
