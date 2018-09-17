package com.example.mg.raye7androiddeveloperchallenge.Data.Local;


import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ArticlesModel;

import java.util.List;

public interface PreferencesHelper {

    List<ArticlesModel> getFavourites();

    void setFavouritesUserModel(ArticlesModel favourites);

}
