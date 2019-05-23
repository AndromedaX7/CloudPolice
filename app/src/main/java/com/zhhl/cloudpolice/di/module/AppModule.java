package com.zhhl.cloudpolice.di.module;


import com.google.gson.Gson;
import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by miao on 2018/9/19.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return this.application;
    }


    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

}