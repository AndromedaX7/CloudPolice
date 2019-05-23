package com.zhhl.cloudpolice.di.module;

import com.google.gson.Gson;
import com.zhhl.cloudpolice.common.di.ActivityScope;
import com.zhhl.cloudpolice.mvp.Contract.MainContract;
import com.zhhl.cloudpolice.mvp.model.MainModel;

import android.app.Application;

import dagger.Module;
import dagger.Provides;


/**
 *
 * Created by miao on 2018/9/19.
 */

@Module
public class MainModule {
    private MainContract.View view;

    /**
     * 构建MainModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view contract
     */
    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainContract.View provideMainView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainContract.Model provideMainModel(Gson gson, Application application) {
        return new MainModel(gson, application);
    }
}