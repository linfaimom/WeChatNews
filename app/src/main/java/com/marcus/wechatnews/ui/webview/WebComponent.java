package com.marcus.wechatnews.ui.webview;

import dagger.Component;

/**
 * Created by marcus on 16/8/18.
 */
@Component(modules = WebModule.class)
public interface WebComponent {
    void inject(WebActivity webActivity);
}
