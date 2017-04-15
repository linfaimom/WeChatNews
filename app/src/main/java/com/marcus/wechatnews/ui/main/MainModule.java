package com.marcus.wechatnews.ui.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by marcus on 16/8/12.
 */
@Module
class MainModule {

    @Provides
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }
}
