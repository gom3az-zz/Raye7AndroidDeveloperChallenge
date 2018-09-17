package com.example.mg.raye7androiddeveloperchallenge.NewsScreen;

import com.example.mg.raye7androiddeveloperchallenge.Data.Interactor;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ArticlesModel;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.NewsModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;


@RunWith(MockitoJUnitRunner.class)
public class NewsActivityPresenterTest {
    private NewsActivity activity;
    private NewsActivityPresenter presenter;
    private Interactor interactor;
    private NewsModel mockNews = new NewsModel();
    private ArticlesModel articlesModel = new ArticlesModel();
    private List<ArticlesModel> favorites = new ArrayList<>();

    @Before
    public void setUp() {
        activity = Mockito.mock(NewsActivity.class);
        interactor = Mockito.mock(Interactor.class);
        presenter = new NewsActivityPresenter(activity, interactor);
        Mockito.when(interactor.getFavourites()).thenReturn(Collections.singletonList(articlesModel));
    }

    @Test
    public void onStart() {
        Mockito.when(interactor.getNews(1)).thenReturn(Observable.just(mockNews));
        presenter.onStart(1);
        Mockito.verify(activity, Mockito.times(1)).showLoading();
        Mockito.verify(activity, Mockito.times(1)).initRecyclerData(mockNews.getArticles());
        Mockito.verify(activity, Mockito.times(1)).hideLoading();
    }

    @Test
    public void onArticleLongClicked() {
        presenter.onArticleLongClicked(articlesModel);
        Mockito.verify(activity, Mockito.times(1)).showAdded();
    }


    @Test
    public void onFavouritesClicked() {
        Mockito.when(interactor.getFavourites()).thenReturn(favorites);
        presenter.onFavouritesClicked();
        Mockito.verify(activity, Mockito.times(1)).initRecyclerFavourites(favorites);
    }
}