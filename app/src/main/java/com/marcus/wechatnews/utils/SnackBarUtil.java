package com.marcus.wechatnews.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by marcus on 16/8/12.
 */
public class SnackBarUtil {

    private SnackBarUtil() {
        throw new UnsupportedOperationException("can't use constructor to init this");
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
    }

    public static void showLong(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
    }

    public static void showIndefinite(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
    }
}
