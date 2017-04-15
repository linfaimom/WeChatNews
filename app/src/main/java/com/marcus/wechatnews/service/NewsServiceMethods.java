package com.marcus.wechatnews.service;

import com.marcus.wechatnews.model.NewsModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by marcus on 16/8/11.
 */
public interface NewsServiceMethods {

    @GET("query")
    Observable<NewsModel> getData(@Query("key") String key);

    @GET("query")
    Observable<NewsModel> getData(@Query("key") String key,
                                  @Query("pno") int pno);
}
