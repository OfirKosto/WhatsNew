package com.example.whatsnew.enums;

public enum eCategories {

    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology");

    public final String mCategory;
    private eCategories(String iCategory)
    {
        mCategory = iCategory;
    }
}
