package com.marcus.wechatnews.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by marcus on 16/8/11.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        initInjector();
        initUIAndListener();
    }

    //设置布局
    public abstract int initContentView();

    //使用 Dagger2 实例化
    public abstract void initInjector();

    //实例化 UI 相关组件
    public abstract void initUIAndListener();

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
