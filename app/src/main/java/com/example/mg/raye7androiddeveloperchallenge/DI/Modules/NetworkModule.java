package com.example.mg.raye7androiddeveloperchallenge.DI.Modules;

import android.content.Context;

import com.example.mg.raye7androiddeveloperchallenge.DI.AppContext;
import com.example.mg.raye7androiddeveloperchallenge.DI.AppScope;
import com.example.mg.raye7androiddeveloperchallenge.Data.Remote.INewsClient;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {


    @Provides
    @AppScope
    Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1024);
    }

    @Provides
    @AppScope
    File provideCacheFile(@AppContext Context context) {
        return new File(context.getCacheDir(), "okHttp_cache");

    }

    @Provides
    @AppScope
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }


    @Provides
    @AppScope
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(INewsClient.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    INewsClient provideRetrofitClient(Retrofit retrofit) {
        return retrofit
                .create(INewsClient.class);
    }


}
