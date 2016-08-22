package com.marcus.wechatnews.ui.recommend;

import com.marcus.wechatnews.bean.WeChatData;
import com.marcus.wechatnews.ui.BasePresenter;
import com.marcus.wechatnews.ui.BaseView;

import java.util.List;

/**
 * Created by marcus on 16/8/15.
 */
public interface RecommendContract {

    interface View extends BaseView {

        void setDataChange(List<WeChatData.ResultBean.ListBean> newsList);

        void setErrorMsg(String errorMsg);

        void setToTop();

        void setFabShow();

        void setFabHide();

        void setRefreshed();

        void setRefreshFailed();

        void setLoaded();

        void setLoadFailed();

        void setNoMore();

        void setDialogShow();

        void setDialogHide();

        boolean isDialogShowing();

    }

    interface Presenter extends BasePresenter<View> {

        void moreData();

        void goToTop();

        void showFab();

        void hideFab();

        void showDialog();

        void hideDialog();

        void resetData();
    }
}
