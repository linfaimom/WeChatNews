package com.marcus.wechatnews.ui.recommend;

import android.os.Handler;

import com.marcus.wechatnews.api.WeChatApi;
import com.marcus.wechatnews.bean.WeChatData;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by marcus on 16/8/15.
 */
public class RecommendPresenter implements RecommendContract.Presenter {

    int totalPage;
    int current = 1;
    private RecommendContract.View view;

    public RecommendPresenter() {
    }

    @Override
    public void moreData() {
        totalPage = totalPage == 0 ? 2 : totalPage;
        if (current < totalPage) {
            new WeChatApi().getData(++current)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<WeChatData>() {
                        @Override
                        public void onCompleted() {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.setDialogHide();
                                    view.setLoaded();
                                }
                            }, 1200);

                        }

                        @Override
                        public void onError(Throwable e) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.setDialogHide();
                                    view.setLoadFailed();
                                }
                            }, 1200);
                        }

                        @Override
                        public void onNext(WeChatData weChatData) {
                            totalPage = weChatData.getResult().getTotalPage();
                            view.setDataChange(weChatData.getResult().getList());
                        }
                    });
        } else {
            view.setDialogHide();
            view.setNoMore();
        }
    }

    @Override
    public void goToTop() {
        view.setToTop();
    }

    @Override
    public void showFab() {
        view.setFabShow();
    }

    @Override
    public void hideFab() {
        view.setFabHide();
    }

    @Override
    public void showDialog() {
        view.setDialogShow();
    }

    @Override
    public void hideDialog() {
        view.setDialogHide();
    }

    @Override
    public void resetData() {
        //加载第一页
        current = 1;
        new WeChatApi().getData(current)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChatData>() {
                    @Override
                    public void onCompleted() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view.setDialogHide();
                                view.setRefreshed();
                            }
                        }, 1200);
                    }

                    @Override
                    public void onError(Throwable e) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view.setDialogHide();
                                view.setRefreshFailed();
                            }
                        }, 1200);
                    }

                    @Override
                    public void onNext(WeChatData weChatData) {
                        view.setDataChange(weChatData.getResult().getList());
                    }
                });
    }

    @Override
    public void attachView(RecommendContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
