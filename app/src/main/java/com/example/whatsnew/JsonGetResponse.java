package com.example.whatsnew;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JsonGetResponse {
    public ArrayList<Article> getData() {
        return mData;
    }

    public void setData(ArrayList<Article> mData) {
        this.mData = mData;
    }

    @SerializedName("articles")
    private ArrayList<Article> mData;
}
