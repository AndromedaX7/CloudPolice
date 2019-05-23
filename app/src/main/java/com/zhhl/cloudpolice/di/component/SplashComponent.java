package com.zhhl.cloudpolice.di.component;

import com.zhhl.cloudpolice.common.di.ActivityScope;
import com.zhhl.cloudpolice.di.module.SplashModule;
import com.zhhl.cloudpolice.mvp.view.activity.SplashActivity;

import dagger.Component;

/**
 * Created by miao on 2018/9/7.
 */

@ActivityScope
@Component(modules = SplashModule.class, dependencies = AppComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}