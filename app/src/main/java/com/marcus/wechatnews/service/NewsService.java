package com.marcus.wechatnews.service;

import com.marcus.wechatnews.model.NewsModel;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by marcus on 16/8/11.
 */
public class NewsService {

    private static final String BASE_URL = "http://v.juhe.cn/weixin/";
    private static final String KEY = "5381b9495f9b35a26adf7670d77e3606";

    private NewsServiceMethods newsServiceMethods;

    public NewsService() {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        newsServiceMethods = retrofit.create(NewsServiceMethods.class);
    }

    public Observable<NewsModel> getData() {
        return newsServiceMethods.getData(KEY).subscribeOn(Schedulers.io());
    }

    public Observable<NewsModel> getData(int page) {
        return newsServiceMethods.getData(KEY, page).subscribeOn(Schedulers.io());
    }
}
