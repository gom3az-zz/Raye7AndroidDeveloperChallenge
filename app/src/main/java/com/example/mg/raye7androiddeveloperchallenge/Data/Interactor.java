package com.example.mg.raye7androiddeveloperchallenge.Data;

import com.example.mg.raye7androiddeveloperchallenge.Data.Local.AppPreferencesHelper;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ArticlesModel;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.NewsModel;
import com.example.mg.raye7androiddeveloperchallenge.Data.Remote.INewsClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Interactor implements IDataInteractor {

    private final INewsClient retrofitFoodClient;
    private final AppPreferencesHelper mPreferencesHelper;

    @Inject
    Interactor(INewsClient client, AppPreferencesHelper preferencesHelper) {
        retrofitFoodClient = client;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public Observable<NewsModel> getNews(int pageNumber) {
        return retrofitFoodClient.getNews(INewsClient.PARAM_SOURCE,
                INewsClient.PARAM_LANGUAGE,
                INewsClient.PARAM_SORT,
                pageNumber,
                INewsClient.PARAM_API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public List<ArticlesModel> getFavourites() {
        return mPreferencesHelper.getFavourites();
    }

    @Override
    public void setFavouritesUserModel(ArticlesModel favourites) {
        mPreferencesHelper.setFavouritesUserModel(favourites);
    }
}
