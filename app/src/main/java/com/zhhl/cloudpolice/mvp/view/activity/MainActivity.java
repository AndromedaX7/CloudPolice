package com.zhhl.cloudpolice.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xdja.sslvpn.api.VpnApi50Impl;
import com.xdja.uaac.api.UaacApi;
import com.xdja.watermarklibrary.WaterMarkUtils;
import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.app.App;
import com.zhhl.cloudpolice.common.Login;
import com.zhhl.cloudpolice.common.LoginBean;
import com.zhhl.cloudpolice.common.activity.BaseMvpActivity;
import com.zhhl.cloudpolice.common.tcp.HttpTools;
import com.zhhl.cloudpolice.di.component.AppComponent;
import com.zhhl.cloudpolice.di.component.DaggerMainComponent;
import com.zhhl.cloudpolice.di.module.MainModule;
import com.zhhl.cloudpolice.mvp.Contract.MainContract;
import com.zhhl.cloudpolice.mvp.QueryResultAdapter;
import com.zhhl.cloudpolice.mvp.presenter.MainPresenter;
import com.zhhl.cloudpolice.mvp.view.fragment.EarlyFragment;
import com.zhhl.cloudpolice.mvp.view.fragment.MineFragment;
import com.zhhl.cloudpolice.mvp.view.fragment.QueryFragment;
import com.zhhl.cloudpolice.mvp.view.fragment.StatisticsFragment;
import com.zhhl.cloudpolice.tcp.ILogUploadImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/9/19.
 */

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View,QueryResultAdapter.CallOutLine {

    private ProgressDialog dialog;
    private int flag;
    @BindView(R.id.mStatistics)
    ImageView mStatistics;
    @BindView(R.id.mQuery)
    ImageView mQuery;
    @BindView(R.id.mEarlyWarning)
    ImageView mEarlyWarning;
    @BindView(R.id.mMine)
    ImageView mMine;
    @BindView(R.id.navigationBar)
    LinearLayout navigationBar;
    @BindView(R.id.mContent)
    FrameLayout mContent;


    ArrayList<ImageView> rbs;
    private AppComponent appComponent;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this)) //请将MainModule()第一个首字母改为小写
                .build()
                .inject(this);

    }

    public static void startCurrent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }


    @Override
    protected View navigationBar() {
        return navigationBar;
    }

    @Override
    protected void onCreated() {
        super.onCreated();
        rbs = new ArrayList<>();
        rbs.add(mStatistics);
        rbs.add(mQuery);
        rbs.add(mEarlyWarning);
        rbs.add(mMine);
        mStatistics.setSelected(true);
        open(StatisticsFragment.class, true);
        setAppComponentToFragment(appComponent, StatisticsFragment.class);
    }

    @OnClick({R.id.mStatistics, R.id.mQuery, R.id.mEarlyWarning, R.id.mMine})
    public void onCheckedChanged(View view) {
        for (int i = 0; i < rbs.size(); i++) {
            rbs.get(i).setSelected(rbs.get(i).getId() == view.getId());
        }
        switch (view.getId()) {
            case R.id.mStatistics:
                open(StatisticsFragment.class, true);
                break;
            case R.id.mQuery:
                open(QueryFragment.class, true);
                break;
            case R.id.mEarlyWarning:
                open(EarlyFragment.class, true);
                break;
            case R.id.mMine:
                open(MineFragment.class, true);
                break;
        }
    }

    @Override
    protected int fragmentInsertId() {
        return mContent.getId();
    }

    @Override
    public void onBackPressed() {

        if (last.size() > 0) {
            onClose();
            return;
        }

        if (System.currentTimeMillis() - lastTime <= 2000) {
            UaacApi.notifyLogout(this);
            super.onBackPressed();
        } else {
            lastTime = System.currentTimeMillis();
            Toast.makeText(this, "请再按一次退出", Toast.LENGTH_SHORT).show();
        }
    }

    private long lastTime;

    @Override
    public void call(int tag, QueryResultAdapter.Flag flag) {
        mEarlyWarning.setSelected(true);
    }
}