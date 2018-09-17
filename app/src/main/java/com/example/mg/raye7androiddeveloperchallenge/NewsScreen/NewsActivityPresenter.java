package com.example.mg.raye7androiddeveloperchallenge.NewsScreen;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.mg.raye7androiddeveloperchallenge.Data.Interactor;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ArticlesModel;
import com.example.mg.raye7androiddeveloperchallenge.NewsScreen.DI.NewsScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@NewsScope
public class NewsActivityPresenter implements NewsActivityContract.IPresenter {
    private final NewsActivity mView;
    private final Interactor mInteractor;
    private final CompositeDisposable disposable;

    @Inject
    NewsActivityPresenter(Activity view,
                          Interactor interactor) {

        mView = (NewsActivity) view;
        mInteractor = interactor;
        disposable = new CompositeDisposable();
    }

    @Override
    public void onStart(int currentPage) {
        disposable.add(mInteractor.getNews(currentPage)
                .doOnSubscribe(disposable1 -> mView.showLoading())
                .doOnTerminate(mView::hideLoading)
                .subscribe(
                        newsModel -> mView.initRecyclerData(newsModel.getArticles()),
                        mView::onErrorLoading));
    }

    @Override
    public void onStop() {
        disposable.clear();
    }

    @Override
    public void onArticleClicked(ArticlesModel article) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
        mView.startActivity(browserIntent);
    }

    @Override
    public void onArticleLongClicked(ArticlesModel article) {
        mInteractor.setFavouritesUserModel(article);
        mView.showAdded();
    }

    @Override
    public void onFavouritesClicked() {
        List<ArticlesModel> favourites = mInteractor.getFavourites();
        if (favourites == null)
            favourites = new ArrayList<>();
        mView.initRecyclerFavourites(favourites);
    }

}
