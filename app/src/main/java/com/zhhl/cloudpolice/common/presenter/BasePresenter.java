package com.zhhl.cloudpolice.common.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.zhhl.cloudpolice.app.Application;
import com.zhhl.cloudpolice.common.RxResult;
import com.zhhl.cloudpolice.common.ViewCommon;
import com.zhhl.cloudpolice.common.model.ModelCommon;

import javax.inject.Inject;

/**
 * Created by miao on 2018/6/28.
 */
public class BasePresenter<M extends ModelCommon, V extends ViewCommon> implements IPresenter, RxResult {
    protected String TAG = getClass().getSimpleName();

    protected V mRootView;

    @Inject
    protected M mModel;


    public BasePresenter(M mModel, V mRootView) {
        this.mModel = mModel;
        this.mRootView = mRootView;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        mModel = null;
        mRootView = null;
    }


    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        Context context = Application.getContext();
        String msg = "服务器异常";
        if (throwable instanceof java.net.SocketTimeoutException) {
            msg = throwable.getMessage();
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: ");
    }
}
