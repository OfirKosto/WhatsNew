package com.example.whatsnew;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Article implements Serializable {

    @SerializedName("url")
    private String mUrl;
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("content")
    private String mContent;
    @SerializedName("urlToImage")
    private String mUrlToImage;
    @SerializedName("description")
    private String mDescription;


    public Article(String iUrl, String iId, String iName, String iAuthor, String iTitle,
                   String iContent, String iUrlToImage, String iDescription) {
        this.mUrl = iUrl;
        this.mId = iId;
        this.mName = iName;
        this.mAuthor = iAuthor;
        this.mTitle = iTitle;
        this.mContent = iContent;
        this.mUrlToImage = iUrlToImage;
        this.mDescription = iDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String iUrl) {
        this.mUrl = iUrl;
    }

    public String getId() {
        return mId;
    }

    public void setId(String iId) {
        this.mId = iId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String iName) {
        this.mName = iName;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String iAuthor) {
        this.mAuthor = iAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String iTitle) {
        this.mTitle = iTitle;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String iContent) {
        this.mContent = iContent;
    }

    public String getUrlToImage() {
        return mUrlToImage;
    }

    public void setUrlToImage(String iUrlToImage) {
        this.mUrlToImage = iUrlToImage;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String iDescription) {
        this.mDescription = iDescription;
    }
}
