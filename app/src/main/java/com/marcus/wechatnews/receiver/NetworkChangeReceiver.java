package com.marcus.wechatnews.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.marcus.wechatnews.utils.NetUtil;
import com.marcus.wechatnews.utils.TaskUtil;
import com.marcus.wechatnews.utils.ToastUtil;

/**
 * Created by marcus on 16/8/21.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!TaskUtil.isBackground(context)) {
            if (!NetUtil.isConnected()) {
                ToastUtil.showShort("网络断开，请检查网络连接~~");
            } else if (NetUtil.isWifi()) {
                ToastUtil.showShort("当前为 WIFI 环境，任性使用吧~~");
            } else {
                ToastUtil.showShort("当前为非 WIFI 环境，注意流量变化~~");
            }
        }
    }
}
