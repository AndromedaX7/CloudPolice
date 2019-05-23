package com.zhhl.cloudpolice.di.component;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.cloudpolice.common.tcp.inf.QueryAll;
import com.zhhl.cloudpolice.di.module.AppModule;
import com.zhhl.cloudpolice.di.module.ClientModule;
import com.zhhl.cloudpolice.di.module.NetworkModule;
import com.zhhl.cloudpolice.tcp.IModel;
import com.zhhl.cloudpolice.tcp.NetworkInterface;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;


@Singleton
@Component(modules = {AppModule.class, ClientModule.class, NetworkModule.class})
public interface AppComponent {
    Application application();

    Gson gson();

    OkHttpClient http();

    NetworkInterface.IQuery query();

    QueryAll queryTableItem();

    IModel imodel();
}
