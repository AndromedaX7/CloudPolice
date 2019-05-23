package com.zhhl.cloudpolice.di.component;

import com.zhhl.cloudpolice.common.di.FragmentScope;
import com.zhhl.cloudpolice.di.module.StatisticsModule;
import com.zhhl.cloudpolice.mvp.view.fragment.StatisticsFragment;

import dagger.Component;

/**
 * Created by miao on 2018/9/19.
 */

@FragmentScope
@Component(modules = StatisticsModule.class, dependencies = AppComponent.class)
public interface StatisticsComponent {
    void inject(StatisticsFragment fragment);
}