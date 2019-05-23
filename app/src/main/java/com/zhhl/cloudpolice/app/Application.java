package com.zhhl.cloudpolice.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.zhhl.cloudpolice.common.activity.BaseMvpActivity;
import com.zhhl.cloudpolice.di.component.AppComponent;
import com.zhhl.cloudpolice.di.component.DaggerAppComponent;
import com.zhhl.cloudpolice.di.module.AppModule;
import com.zhhl.cloudpolice.di.module.ClientModule;

import java.lang.ref.WeakReference;

/**
 * Created by miao on 2018/9/19.
 */
public abstract class Application extends android .app.Application implements android.app.Application.ActivityLifecycleCallbacks {
    public AppComponent appComponent;
    public Gson gson;

    private static Context c;

    public static Context getContext() {
        return c;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        c = this;
        registerActivityLifecycleCallbacks(this);
        Log.e("Application", "onCreate: ");
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .clientModule(new ClientModule(/*webSocketMessenger*/))
                .build();
        gson = appComponent.gson();
    }

    private WeakReference<BaseMvpActivity> weakReference;
    private int count = 0;


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        count++;
        Log.e( "AtyLefCircleListener", "onActivityCreated: ");
        Log.e(" current:" + activity.getClass().getName(), "ForceGroundActivityCount:" + count);
        if (activity instanceof BaseMvpActivity)
            weakReference = new WeakReference<>((BaseMvpActivity) activity);

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        count--;
        Log.e("CountDes: ", activity.getClass().getName() + count);
        Log.e(" current" + activity.getClass().getName(), "ForceGroundActivityCount:" + count);
        if (count == 0) {
            weakReference = null;
        }
    }
}
