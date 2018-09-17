package com.example.mg.raye7androiddeveloperchallenge.DI.Modules;

import com.example.mg.raye7androiddeveloperchallenge.NewsScreen.DI.NewsModule;
import com.example.mg.raye7androiddeveloperchallenge.NewsScreen.DI.NewsScope;
import com.example.mg.raye7androiddeveloperchallenge.NewsScreen.NewsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @NewsScope
    @ContributesAndroidInjector(modules = NewsModule.class)
    abstract NewsActivity bindLoginActivity();


}