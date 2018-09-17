package com.example.mg.raye7androiddeveloperchallenge.Data.Models;

import android.support.annotation.NonNull;


public class NewsItem extends ListItem {
    @NonNull
    private ArticlesModel mArticles;

    public NewsItem(@NonNull ArticlesModel articlesModel) {
        this.mArticles = articlesModel;
    }

    @NonNull
    public ArticlesModel getmArticles() {
        return mArticles;
    }

    // here getters and setters
    // for title and so on, built
    // using mArticles

    @Override
    public int getType() {
        return TYPE_EVENT;
    }
}
