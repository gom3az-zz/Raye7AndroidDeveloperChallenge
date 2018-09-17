package com.example.mg.raye7androiddeveloperchallenge.Data;

import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ArticlesModel;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.NewsModel;

import java.util.List;

import io.reactivex.Observable;

public interface IDataInteractor {
    Observable<NewsModel> getNews(int pageNumber);


    List<ArticlesModel> getFavourites();

    void setFavouritesUserModel(ArticlesModel favourites);
}
