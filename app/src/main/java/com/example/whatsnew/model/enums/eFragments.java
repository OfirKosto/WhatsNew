package com.example.whatsnew.model.enums;

public enum eFragments {
    MAIN_SCREEN_FRAGMENT("MainScreenFragment"),
    FAVORITES_ARTICLES_FRAGMENT("FavoritesArticlesFragment"),
    ARTICLES_DATA_FRAGMENT("ArticlesDataFragment");

    public final String mFragment;
    private eFragments(String iFragment)
    {
        mFragment = iFragment;
    }
}
