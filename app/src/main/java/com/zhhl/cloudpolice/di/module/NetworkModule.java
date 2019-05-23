package com.zhhl.cloudpolice.di.module;


import com.zhhl.cloudpolice.common.tcp.Api;
import com.zhhl.cloudpolice.common.tcp.inf.QueryAll;
import com.zhhl.cloudpolice.tcp.IModel;
import com.zhhl.cloudpolice.tcp.NetworkInterface;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by miao on 2018/6/21.
 */

@Module
public class NetworkModule {

    public static Retrofit retrofitCreator(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Api.__BASED__.base_url())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
//        }
        return builder
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    NetworkInterface.IQuery provideNetworkQueryInterface(OkHttpClient client) {
        return retrofitCreator(client).create(NetworkInterface.IQuery.class);
    }

    @Provides
    @Singleton
    QueryAll provideNetworkQueryAllItem(OkHttpClient client) {
        return retrofitCreator(client).create(QueryAll.class);
    }

    @Provides
    @Singleton
    IModel provideNetworkModel(OkHttpClient client){
        return retrofitCreator(client).create(IModel.class);
    }

}
