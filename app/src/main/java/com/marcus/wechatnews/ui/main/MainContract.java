package com.marcus.wechatnews.ui.main;

import android.app.Fragment;

import com.marcus.wechatnews.ui.BasePresenter;
import com.marcus.wechatnews.ui.BaseView;

/**
 * Created by marcus on 16/8/11.
 */
interface MainContract {

    interface View extends BaseView {

        void setBarTitle(CharSequence title);

        void setToLeave();

        void setFragment(Fragment fragment);
    }

    interface Presenter extends BasePresenter<View> {

        void goSetBarTitle(CharSequence title);

        void leave();

        void loadFragment();
    }
}
