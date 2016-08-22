package com.marcus.wechatnews.ui.web;

import dagger.Module;
import dagger.Provides;

/**
 * Created by marcus on 16/8/18.
 */
@Module
public class WebModule {
    @Provides
    WebPresenter provideWebPresenter() {
        return new WebPresenter();
    }
}
