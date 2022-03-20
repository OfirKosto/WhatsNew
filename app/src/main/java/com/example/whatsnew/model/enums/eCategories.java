package com.example.whatsnew.model.enums;

import com.example.whatsnew.R;
import com.example.whatsnew.model.ApplicationContext;

public enum eCategories {

    BUSINESS(ApplicationContext.getContext().getResources().getString(R.string.business)),
    ENTERTAINMENT(ApplicationContext.getContext().getResources().getString(R.string.entertainment)),
    GENERAL(ApplicationContext.getContext().getResources().getString(R.string.general)),
    HEALTH(ApplicationContext.getContext().getResources().getString(R.string.health)),
    SCIENCE(ApplicationContext.getContext().getResources().getString(R.string.science)),
    SPORTS(ApplicationContext.getContext().getResources().getString(R.string.sports)),
    TECHNOLOGY(ApplicationContext.getContext().getResources().getString(R.string.technology));

    public final String mCategory;
    private eCategories(String iCategory)
    {
        mCategory = iCategory;
    }
}
