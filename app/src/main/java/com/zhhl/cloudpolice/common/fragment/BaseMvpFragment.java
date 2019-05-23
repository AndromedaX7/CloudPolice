package com.zhhl.cloudpolice.common.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zhhl.cloudpolice.common.presenter.BasePresenter;
import com.zhhl.cloudpolice.di.component.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment /*implements IPalette, WebSocketMessageDispatcher*/ {
    protected String TAG = getClass().getSimpleName();
    private Unbinder bind;

    @Inject
    protected P mPresenter;
    private AppComponent appComponent;

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
        setupFragmentComponent(appComponent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onCreated();
    }

    protected abstract int getLayoutRes();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void setupFragmentComponent(AppComponent appComponent);

//    @Override
//    public void palette(Palette palette) {
//
//    }

    protected void onCreated() {

    }

//    @Override
//    public void dispatchMessage_onMessage(String message) {
//        Log.e(TAG, "dispatchMessage_onMessage: " + message);
//    }

//    @Override
//    public void dispatchMessage(Bundle bundle) {
//
//    }

    public void addData(String message) {

    }
}
