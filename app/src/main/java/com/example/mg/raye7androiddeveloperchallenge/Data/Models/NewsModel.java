package com.example.mg.raye7androiddeveloperchallenge.Data.Models;

import java.util.List;

public class NewsModel {

    private String status;
    private int totalResults;
    private List<ArticlesModel> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticlesModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesModel> articles) {
        this.articles = articles;
    }

}
