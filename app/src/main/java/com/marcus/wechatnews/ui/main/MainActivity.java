package com.marcus.wechatnews.ui.main;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.marcus.wechatnews.R;
import com.marcus.wechatnews.ui.BaseActivity;
import com.marcus.wechatnews.utils.FragmentUtil;
import com.marcus.wechatnews.utils.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainPresenter mainPresenter;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.m_toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.fab)
    FloatingActionButton faBtn;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }

    //完成注入操作
    @Override
    public void initInjector() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }

    @Override
    public void initUIAndListener() {
        //ButterKnife
        ButterKnife.bind(this);
        //ToolBar
        toolbar.setTitle("热门推荐");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.navigation);
        //点击 NavigationIcon 打开 DrawerLayout
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        //绑定View
        mainPresenter.attachView(this);
        //根据网络加载首个Fragment
        FragmentUtil.manage(this);
        mainPresenter.loadFragment();
    }

    @Override
    public void setBarTitle(CharSequence title) {
        toolbar.setTitle(title);
    }

    @Override
    public void setToLeave() {
        System.exit(0);
    }

    @Override
    public void setFragment(Fragment fragment) {
        FragmentUtil.replace(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // TODO: 16/8/15 needs to be done,notification.
            case R.id.notify:
                ToastUtil.showShort("目前还不会做这块功能");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        mainPresenter.leave();
    }

    public FloatingActionButton getFaBtn() {
        return faBtn;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

}
