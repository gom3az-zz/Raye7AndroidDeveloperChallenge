package com.example.mg.raye7androiddeveloperchallenge.NewsScreen.DI;

import android.app.Activity;

import com.example.mg.raye7androiddeveloperchallenge.NewsScreen.NewsActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NewsModule {


    @Binds
    @NewsScope
    abstract Activity providesLoginActivity(NewsActivity activity);


}

