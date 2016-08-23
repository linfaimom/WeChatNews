package com.marcus.wechatnews.ui.recommend;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marcus.wechatnews.R;
import com.marcus.wechatnews.bean.WeChatData;
import com.marcus.wechatnews.ui.BaseFragment;
import com.marcus.wechatnews.ui.adapter.NewsListAdapter;
import com.marcus.wechatnews.utils.NetUtil;
import com.marcus.wechatnews.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marcus on 16/8/15.
 */
public class RecommendFragment extends BaseFragment implements RecommendContract.View,
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    int pastVisiblePosition, visibleItemCount, totalItemCount;
    @BindView(R.id.recommend_list)
    RecyclerView recommendList;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    private NewsListAdapter newsListAdapter;
    private RecommendPresenter recommendPresenter;
    private LinearLayoutManager mLayoutManager;
    private ProgressDialog progressDialog;
    private List<WeChatData.ResultBean.ListBean> result = new ArrayList<>();
    private boolean loading = true;

    @Override
    public int initContentView() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initUI() {
        ButterKnife.bind(this, view);
        recommendList.setHasFixedSize(true);
        //设置布局，同时可根据布局判断 item 位置
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recommendList.setLayoutManager(mLayoutManager);
        recommendList.setAdapter(newsListAdapter);
        recommendPresenter.attachView(this);
        //ProgressDialog
        progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMessage("正在加载中~~");
        progressDialog.setCancelable(false);
        //首次加载数据
        recommendPresenter.showDialog();
        recommendPresenter.resetData();
        //浮动按钮，点击回到顶部
        faBtn.setOnClickListener(this);
        //根据滑动来判断是否隐藏 fab
        recommendList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //记录当前页面所能见到的 item 的 position，可以理解为这是第几个 item
                pastVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();
                //记录当前页面共有几个 item
                visibleItemCount = mLayoutManager.getChildCount();
                //记录共有多少个 item
                totalItemCount = mLayoutManager.getItemCount();

                if (dy > 5) {
                    recommendPresenter.hideFab();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblePosition) >= totalItemCount) {
                            loading = false;
                            recommendPresenter.showDialog();
                            recommendPresenter.moreData();
                        }
                    }
                } else if (dy < -5) {
                    recommendPresenter.showFab();
                }
            }
        });
        //刷新
        refreshLayout.setOnRefreshListener(this);
//        refreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorPrimary);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.yellow), getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void iniInjector() {
        recommendPresenter = new RecommendPresenter();
        newsListAdapter = new NewsListAdapter();
    }


    @Override
    public void setDataChange(List<WeChatData.ResultBean.ListBean> newsList) {
        result.addAll(newsList);
        newsListAdapter.setData(result);
        newsListAdapter.notifyDataSetChanged();
        loading = true;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        refreshLayout.setRefreshing(false);
        ToastUtil.showShort(errorMsg);
    }

    @Override
    public void setToTop() {
        //少于20个条目时，缓慢回到顶部
        if (mLayoutManager.findLastVisibleItemPosition() + mLayoutManager.getChildCount() <= 20) {
            recommendList.smoothScrollToPosition(0);
        } else {
            recommendList.scrollToPosition(0);
        }
    }

    @Override
    public void setFabShow() {
        faBtn.show();
    }

    @Override
    public void setFabHide() {
        faBtn.hide();
    }

    @Override
    public void setRefreshed() {
        refreshLayout.setRefreshing(false);
        ToastUtil.showShort("刷新成功~~");
    }

    @Override
    public void setRefreshFailed() {
        refreshLayout.setRefreshing(false);
        ToastUtil.showShort("刷新失败~~");
    }

    @Override
    public void setLoaded() {
        ToastUtil.showShort("加载更多~~");
    }

    @Override
    public void setLoadFailed() {
        ToastUtil.showShort("加载失败~~");
        loading = true;
    }

    @Override
    public void setNoMore() {
        ToastUtil.showShort("没有更多数据啦~~");
    }

    @Override
    public void setDialogShow() {
        progressDialog.show();
    }

    @Override
    public void setDialogHide() {
        progressDialog.hide();
    }

    @Override
    public boolean isDialogShowing() {
        return progressDialog.isShowing();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                recommendPresenter.goToTop();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (NetUtil.isConnected()) {
            result.clear();
            recommendPresenter.resetData();
        } else {
            setRefreshFailed();
        }
    }

}
