package com.example.whatsnew;

import com.example.whatsnew.Article;

import java.util.ArrayList;

public interface IArticlesListUser {

    public void getList(ArrayList<Article> articles, boolean isSuccessful);
}
