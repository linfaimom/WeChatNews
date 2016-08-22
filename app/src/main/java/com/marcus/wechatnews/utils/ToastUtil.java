package com.marcus.wechatnews.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by marcus on 16/8/12.
 */
public class ToastUtil {

    public static Context mContext;

    private ToastUtil() {
    }

    //获取全局 Context
    public static void register(Context context) {
        mContext = context.getApplicationContext();
    }

    public static void showShort(int resId) {
        Toast.makeText(mContext, mContext.getString(resId), Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        Toast.makeText(mContext, mContext.getString(resId), Toast.LENGTH_LONG).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}
