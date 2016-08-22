package com.marcus.wechatnews.ui.web;

import javax.inject.Inject;

/**
 * Created by marcus on 16/8/18.
 */
public class WebPresenter implements WebContract.Presenter {

    WebContract.View view;

    @Inject
    public WebPresenter() {
    }

    @Override
    public void attachView(WebContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void shareToTimeline() {
        view.setShare();
    }
}
