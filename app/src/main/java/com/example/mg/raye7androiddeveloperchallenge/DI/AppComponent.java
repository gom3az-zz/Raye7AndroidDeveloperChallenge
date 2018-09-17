package com.example.mg.raye7androiddeveloperchallenge.DI;

import android.app.Application;

import com.example.mg.raye7androiddeveloperchallenge.App;
import com.example.mg.raye7androiddeveloperchallenge.DI.Modules.AppModule;
import com.example.mg.raye7androiddeveloperchallenge.DI.Modules.BuildersModule;
import com.example.mg.raye7androiddeveloperchallenge.DI.Modules.NetworkModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@AppScope
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        AndroidSupportInjectionModule.class,
        BuildersModule.class})

public interface AppComponent extends AndroidInjector<DaggerApplication> {
    void inject(App app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
