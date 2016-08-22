package com.marcus.wechatnews.api;

import com.marcus.wechatnews.bean.WeChatData;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by marcus on 16/8/11.
 */
public class WeChatApi {

    static final String BASE_URL = "http://v.juhe.cn/weixin/";
    static final String KEY = "5381b9495f9b35a26adf7670d77e3606";

    private WeChatService weChatService;

    public WeChatApi() {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        weChatService = retrofit.create(WeChatService.class);
    }

    public Observable<WeChatData> getData() {
        return weChatService.getData(KEY).subscribeOn(Schedulers.io());
    }

    public Observable<WeChatData> getData(int page) {
        return weChatService.getData(KEY, page).subscribeOn(Schedulers.io());
    }
}
