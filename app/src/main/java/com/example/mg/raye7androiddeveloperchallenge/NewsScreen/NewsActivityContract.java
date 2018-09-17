package com.example.mg.raye7androiddeveloperchallenge.NewsScreen;

import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ArticlesModel;

import java.util.List;

public interface NewsActivityContract {
    interface IView {
        void showLoading();

        void hideLoading();

        void onErrorLoading(Throwable throwable);

        void showAdded();

        void initRecyclerData(List<ArticlesModel> articles);

        void initRecyclerFavourites(List<ArticlesModel> articles);
    }

    interface IPresenter {
        void onStart(int currentPage);

        void onStop();

        void onArticleClicked(ArticlesModel article);

        void onFavouritesClicked();

        void onArticleLongClicked(ArticlesModel article);
    }
}
