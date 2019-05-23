package com.zhhl.cloudpolice.mvp.presenter;

import android.app.Application;

import com.xdja.sslvpn.api.VpnApi50Impl;
import com.zhhl.cloudpolice.app.App;
import com.zhhl.cloudpolice.common.LoginBean;
import com.zhhl.cloudpolice.common.di.ActivityScope;
import com.zhhl.cloudpolice.common.presenter.BasePresenter;
import com.zhhl.cloudpolice.common.tcp.HttpTools;
import com.zhhl.cloudpolice.di.component.SplashContract;
import com.zhhl.cloudpolice.tcp.ILogUploadImpl;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/9/7.
 */

@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    private Application mApplication;

    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void login(String token) {
        HttpTools.buildLogin()
                .login(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::callResult, this::onError, this::onComplete)
                .isDisposed();
    }

    public void onError(Throwable throwable) {
        super.onError(throwable);
    }

    private void callResult(LoginBean loginBean) {
        App.app().setUserInfo(loginBean);
        App.app().setLogReport(new ILogUploadImpl(new VpnApi50Impl(mApplication)));
        mModel.checkPermission(loginBean.getUserInfo().getCode(), mApplication.getPackageName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::checkResult, this::onError, this::onComplete)
                .isDisposed();
//        mRootView.showMain();

    }

    private void checkResult(boolean o) {
        if (o)
            mRootView.showMain();
        else
            mRootView.dismissIndicator();
    }
}
