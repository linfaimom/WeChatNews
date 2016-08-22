package com.marcus.wechatnews.api;

import com.marcus.wechatnews.bean.WeChatData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by marcus on 16/8/11.
 */
public interface WeChatService {

    @GET("query")
    Observable<WeChatData> getData(@Query("key") String key);

    @GET("query")
    Observable<WeChatData> getData(@Query("key") String key,
                                   @Query("pno") int pno);
}
