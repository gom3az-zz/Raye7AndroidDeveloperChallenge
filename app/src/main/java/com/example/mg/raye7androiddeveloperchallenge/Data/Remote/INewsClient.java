package com.example.mg.raye7androiddeveloperchallenge.Data.Remote;


import com.example.mg.raye7androiddeveloperchallenge.Data.Models.NewsModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface INewsClient {
    String BASE_URL = "https://newsapi.org";
    String PARAM_API_KEY = "81a452c672f74416bbe4d78d9891ca93";
    String PARAM_SORT = "publishedAt";
    String PARAM_SOURCE = "usa-today";
    String PARAM_LANGUAGE = "en";


    @GET("/v2/everything")
    Observable<NewsModel> getNews(@Query("sources") String source,
                                  @Query("language") String language,
                                  @Query("sortBy") String sortBy,
                                  @Query("page") int page,
                                  @Query("apiKey") String apiKey
    );

}
