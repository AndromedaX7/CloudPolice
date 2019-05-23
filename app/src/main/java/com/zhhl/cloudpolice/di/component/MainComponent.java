package com.zhhl.cloudpolice.di.component;

import com.zhhl.cloudpolice.common.di.ActivityScope;
import com.zhhl.cloudpolice.di.module.MainModule;
import com.zhhl.cloudpolice.mvp.view.activity.MainActivity;

import dagger.Component;

/**
 *
 * Created by miao on 2018/9/19.
 */

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}