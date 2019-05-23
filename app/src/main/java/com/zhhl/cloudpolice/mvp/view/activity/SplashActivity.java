package com.zhhl.cloudpolice.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xdja.uaac.api.TokenCallback;
import com.xdja.uaac.api.UaacApi;
import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.common.activity.BaseMvpActivity;
import com.zhhl.cloudpolice.di.component.AppComponent;
import com.zhhl.cloudpolice.di.component.DaggerSplashComponent;
import com.zhhl.cloudpolice.di.component.SplashContract;
import com.zhhl.cloudpolice.di.module.SplashModule;
import com.zhhl.cloudpolice.mvp.presenter.SplashPresenter;

import butterknife.BindView;


/**
 * Created by miao on 2018/9/7.
 */

public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashContract.View {

    //
//    @BindView(R.id.appName)
//    TextView appName;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.information)
    TextView information;
    private boolean finish;
    @BindView(R.id.root)
    CoordinatorLayout splashView;
    @BindView(R.id.indicator)
    ProgressBar indicator;
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSplashComponent
                .builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this)) //请将SplashModule()第一个首字母改为小写
                .build()
                .inject(this);
    }


    @Override
    public void showMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void dismissIndicator() {
        indicator.setVisibility(View.INVISIBLE);
        Snackbar.make(splashView, "您的账户没有在系统注册,请与管理员联系", Snackbar.LENGTH_LONG).show();

    }

    @Override
    protected void onCreated() {
        super.onCreated();
        initData();
    }

    protected void initData() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        description.setText("①统计分析外网采集的数据\n" +
//                "②自动比对七类重点人员");
//
//        information.setText("如需开通权限请联系系统管理员\n" +
//                "吉林省智慧互联信息科技有限公司\n" +
//                "技术联系电话18043134285");
        UaacApi.getToken(this, new TokenCallback() {
            @Override
            public void onSuccess(String token, boolean b) {
                new Handler(Looper.getMainLooper()).postDelayed(() -> mPresent .login(token), 5000);
            }

            @Override
            public void onError(String s) {
                if (s.equals("票据不存在")) {
                    new Handler(Looper.getMainLooper()).postDelayed(() -> UaacApi.getToken(SplashActivity.this, this), 2000);
                }
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }
}