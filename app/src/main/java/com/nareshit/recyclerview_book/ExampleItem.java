package com.nareshit.recyclerview_book;

public class ExampleItem {
    private String mImageUrl;
    private String mTitle;
    private String mAuthors;

    public ExampleItem(String mImageUrl, String mTitle, String mAuthors) {
        this.mImageUrl = mImageUrl;
        this.mTitle = mTitle;
        this.mAuthors = mAuthors;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthors() {
        return mAuthors;
    }

}
