package com.marcus.wechatnews.ui.main;

import com.marcus.wechatnews.ui.adapter.AdapterModule;

import dagger.Component;

/**
 * Created by marcus on 16/8/12.
 */
@Component(modules = {MainModule.class, AdapterModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
