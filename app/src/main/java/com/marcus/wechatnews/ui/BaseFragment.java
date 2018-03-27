package com.marcus.wechatnews.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcus.wechatnews.ui.main.MainActivity;

/**
 * Created by marcus on 16/8/15.
 */
public abstract class BaseFragment extends Fragment {

    protected View view;
    protected FloatingActionButton faBtn;
    protected Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(initContentView(), null);
        initInjector();
        getFab();
        getToolBar();
        initUI();
        return view;
    }

    public abstract int initContentView();

    public abstract void initUI();

    public abstract void initInjector();

    public void getFab() {
        this.faBtn = ((MainActivity) getActivity()).getFaBtn();
    }

    public void getToolBar() {
        this.toolbar = ((MainActivity) getActivity()).getToolbar();
    }

}
