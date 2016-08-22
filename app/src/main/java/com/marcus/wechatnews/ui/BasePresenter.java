package com.marcus.wechatnews.ui;

/**
 * Created by marcus on 16/8/11.
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
