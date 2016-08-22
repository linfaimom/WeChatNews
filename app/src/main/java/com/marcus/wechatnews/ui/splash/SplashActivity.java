package com.marcus.wechatnews.ui.splash;

import android.os.Handler;

import com.marcus.wechatnews.R;
import com.marcus.wechatnews.ui.BaseActivity;
import com.marcus.wechatnews.ui.main.MainActivity;

/**
 * Created by marcus on 16/8/17.
 */
public class SplashActivity extends BaseActivity {

    @Override
    public int initContentView() {
        return R.layout.splash_view;
    }

    @Override
    public void initInjector() {
    }

    @Override
    public void initUIAndListener() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.startActivity(SplashActivity.this);
                finish();
            }
        }, 1500);
    }
}
