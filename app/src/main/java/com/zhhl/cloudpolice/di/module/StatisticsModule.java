package com.zhhl.cloudpolice.di.module;

import com.google.gson.Gson;
import com.zhhl.cloudpolice.common.di.FragmentScope;
import com.zhhl.cloudpolice.mvp.Contract.StatisticsContract;
import com.zhhl.cloudpolice.mvp.model.StatisticsModel;
import com.zhhl.cloudpolice.tcp.NetworkInterface;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Created by miao on 2018/9/19.
 */

@Module
public class StatisticsModule {
    private StatisticsContract.View view;

    /**
     * 构建StatisticsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view contract
     */
    public StatisticsModule(StatisticsContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    StatisticsContract.View provideStatisticsView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    StatisticsContract.Model provideStatisticsModel(Gson gson, Application application , NetworkInterface.IQuery iQuery) {
        return new StatisticsModel(gson, application,iQuery);
    }
}