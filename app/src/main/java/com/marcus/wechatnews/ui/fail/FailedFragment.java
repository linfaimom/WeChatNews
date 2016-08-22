package com.marcus.wechatnews.ui.fail;


import android.view.View;

import com.marcus.wechatnews.R;
import com.marcus.wechatnews.ui.BaseFragment;
import com.marcus.wechatnews.ui.recommend.RecommendFragment;
import com.marcus.wechatnews.utils.FragmentUtil;
import com.marcus.wechatnews.utils.NetUtil;
import com.marcus.wechatnews.utils.ToastUtil;

/**
 * Created by marcus on 16/8/21.
 */
public class FailedFragment extends BaseFragment {

    @Override
    public int initContentView() {
        return R.layout.fragment_failed;
    }

    @Override
    public void initUI() {
        view.findViewById(R.id.reload_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetUtil.isConnected()) {
                    FragmentUtil.replace(new RecommendFragment());
                } else {
                    ToastUtil.showShort("刷新失败~~");
                }
            }
        });
    }

    @Override
    public void iniInjector() {

    }
}
