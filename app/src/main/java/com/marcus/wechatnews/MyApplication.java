package com.marcus.wechatnews;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.marcus.wechatnews.receiver.NetworkChangeReceiver;
import com.marcus.wechatnews.utils.NetUtil;
import com.marcus.wechatnews.utils.ToastUtil;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by marcus on 16/8/12.
 */
public class MyApplication extends Application {

    //WeChat
    private static final String APP_ID = "wx53e4a61a2f06f182";
    private IWXAPI api;
    //Receiver
    private NetworkChangeReceiver receiver = new NetworkChangeReceiver();

    @Override
    public void onCreate() {
        super.onCreate();
        NetUtil.register(this);
        ToastUtil.register(this);
        registerToWX();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, mFilter);
    }

    private void registerToWX() {
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
    }

    public IWXAPI getApi() {
        return api;
    }

}
