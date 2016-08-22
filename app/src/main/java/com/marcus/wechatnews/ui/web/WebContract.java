package com.marcus.wechatnews.ui.web;

import com.marcus.wechatnews.ui.BasePresenter;
import com.marcus.wechatnews.ui.BaseView;

/**
 * Created by marcus on 16/8/18.
 */
public interface WebContract {

    interface View extends BaseView {

        void setShare();
    }

    interface Presenter extends BasePresenter<View> {

        void shareToTimeline();
    }
}
