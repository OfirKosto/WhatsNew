package com.example.whatsnew.model;

public class Category {

    private String mName;
    private String mLocalizedName;

    public Category(String iName, String iLocalizedName) {
        this.mName = iName;
        this.mLocalizedName = iLocalizedName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String iName) {
        this.mName = iName;
    }

    public String getLocalizedName() {
        return mLocalizedName;
    }

    public void setLocalizedName(String iLocalizedName) {
        this.mLocalizedName = iLocalizedName;
    }
}
