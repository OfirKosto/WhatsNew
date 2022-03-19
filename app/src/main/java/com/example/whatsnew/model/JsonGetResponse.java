package com.example.whatsnew.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JsonGetResponse {
    public ArrayList<Article> getData() {
        return mData;
    }

    @SerializedName("articles")
    private ArrayList<Article> mData;
}
