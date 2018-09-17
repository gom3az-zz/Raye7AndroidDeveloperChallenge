package com.example.mg.raye7androiddeveloperchallenge.Data.Local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mg.raye7androiddeveloperchallenge.DI.AppContext;
import com.example.mg.raye7androiddeveloperchallenge.DI.AppScope;
import com.example.mg.raye7androiddeveloperchallenge.Data.Models.ArticlesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@AppScope
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_FAVOURITES = "PREF_FAVOURITES";

    private final SharedPreferences mPrefs;
    private Gson gson = new Gson();
    private String json;

    @Inject
    AppPreferencesHelper(@AppContext Context context) {
        mPrefs = context.getSharedPreferences("Raye7_pref", Context.MODE_PRIVATE);
    }


    @Override
    public List<ArticlesModel> getFavourites() {
        Type type = new TypeToken<List<ArticlesModel>>() {
        }.getType();
        json = mPrefs.getString(PREF_FAVOURITES, "");
        return gson.fromJson(json, type);
    }

    @Override
    public void setFavouritesUserModel(ArticlesModel model) {
        List<ArticlesModel> favourites = getFavourites();
        if (favourites == null)
            favourites = new ArrayList<>();
        favourites.add(model);
        json = gson.toJson(favourites);
        mPrefs.edit().putString(PREF_FAVOURITES, json).apply();
    }
}
