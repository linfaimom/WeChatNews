package com.marcus.wechatnews.ui.main;

import com.marcus.wechatnews.ui.fail.FailedFragment;
import com.marcus.wechatnews.ui.recommend.RecommendFragment;
import com.marcus.wechatnews.utils.NetUtil;
import com.marcus.wechatnews.utils.ToastUtil;

import javax.inject.Inject;

/**
 * Created by marcus on 16/8/12.
 */
class MainPresenter implements MainContract.Presenter {

    private long exitTime = 0;
    private MainContract.View view;

    @Inject
    MainPresenter() {
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void goSetBarTitle(CharSequence title) {
        view.setBarTitle(title);
    }

    private boolean canLeave() {
        if (System.currentTimeMillis() - exitTime > 1500) {
            ToastUtil.showShort("再按一次退出程序");
            exitTime = System.currentTimeMillis();
            return false;
        }
        return true;
    }

    @Override
    public void leave() {
        if (canLeave()) {
            view.setToLeave();
        }
    }

    @Override
    public void loadFragment() {
        if (NetUtil.isConnected()) {
            view.setFragment(new RecommendFragment());
        } else {
            view.setFragment(new FailedFragment());
        }
    }

}
