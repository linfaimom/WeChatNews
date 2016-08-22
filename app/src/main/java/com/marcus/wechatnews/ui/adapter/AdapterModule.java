package com.marcus.wechatnews.ui.adapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by marcus on 16/8/12.
 */
@Module
public class AdapterModule {

    @Provides
    NewsListAdapter provideNewsRecyclerViewList() {
        return new NewsListAdapter();
    }
}
