package com.example.mg.raye7androiddeveloperchallenge.DI.Modules;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.mg.raye7androiddeveloperchallenge.DI.AppContext;
import com.example.mg.raye7androiddeveloperchallenge.DI.AppScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Provides
    @AppScope
    static RequestManager providesGlide(@AppContext Context app) {
        return Glide.with(app);
    }

    @Binds
    @AppScope
    @AppContext
    public abstract Context providesAppContext(Application app);


}
