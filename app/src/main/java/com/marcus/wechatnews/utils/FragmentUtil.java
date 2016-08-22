package com.marcus.wechatnews.utils;

import android.app.Activity;
import android.app.Fragment;

import com.marcus.wechatnews.R;

/**
 * Created by marcus on 16/8/22.
 */
public class FragmentUtil {

    static Activity activity;

    public static void manage(Activity fromActivity) {
        activity = fromActivity;
    }

    public static void add(Fragment fragment) {
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.content, fragment)
                .commit();
    }

    public static void show(Fragment fragment) {
        activity.getFragmentManager()
                .beginTransaction()
                .show(fragment)
                .commit();
    }

    public static void hide(Fragment fragment) {
        activity.getFragmentManager()
                .beginTransaction()
                .hide(fragment)
                .commit();
    }

    public static void replace(Fragment fragment) {
        activity.getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
}
