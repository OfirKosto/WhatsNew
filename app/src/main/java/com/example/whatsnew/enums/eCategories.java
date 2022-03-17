package com.example.whatsnew.enums;

public enum eCategories {

    BUSINESS("Business"),
    ENTERTAINMENT("Entertainment"),
    GENERAL("General"),
    HEALTH("Health"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    TECHNOLOGY("Technology");

    public final String mCategory;
    private eCategories(String iCategory)
    {
        mCategory = iCategory;
    }
}
