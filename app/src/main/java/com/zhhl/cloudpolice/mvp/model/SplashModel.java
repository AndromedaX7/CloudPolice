package com.zhhl.cloudpolice.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.zhhl.cloudpolice.common.model.BaseModel;
import com.zhhl.cloudpolice.tcp.IModel;
import com.zhhl.cloudpolice.di.component.SplashContract;

import io.reactivex.Observable;

/**
 * Created by miao on 2018/9/7.
 */

public class SplashModel extends BaseModel implements SplashContract.Model {

    private final IModel iModel;

    public SplashModel(Gson gson, Application application, IModel iModel) {
        super(application, gson);
        this.iModel = iModel;
    }


    @Override
    public Observable<Boolean> checkPermission(String code, String requiredId) {
        Log.e("checkPermission: ", requiredId);
        return iModel.checkPermission(code, requiredId);
    }
}

