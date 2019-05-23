package com.zhhl.cloudpolice.common.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xdja.watermarklibrary.WaterMarkUtils;
import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.app.App;
import com.zhhl.cloudpolice.app.Application;
import com.zhhl.cloudpolice.common.CallbackDialog;
import com.zhhl.cloudpolice.common.fragment.BaseFragment;
import com.zhhl.cloudpolice.common.fragment.BaseMvpFragment;
import com.zhhl.cloudpolice.di.component.AppComponent;

import java.util.HashMap;
import java.util.LinkedList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by miao on 2018/9/3.
 */
public abstract class UnStructActivity extends AppCompatActivity implements FragmentCloseCallback, FragmentOpenCallback, ControlInterface {
    protected String TAG = getClass().getSimpleName();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected abstract int getLayoutRes();

    protected Unbinder bind;

    protected AppComponent appComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = ((Application) getApplication()).appComponent;
        setupActivityComponent(appComponent);
        last = new LinkedList<>();
        map2 = new HashMap<>();
    }

    protected abstract void onCreated();

    public void setLayout() {
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        if (App.app().getUserInfo() != null)
            WaterMarkUtils.addWaterMark(this, App.app().getUserInfo().getUserInfo().getName() + " " + App.app().getUserInfo().getUserInfo().getCode(), 270 + 45, getResources().getColor(R.color.wt), 60);
    }

    protected AlertDialog showSuccess(View view) {

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_success)
                .setCancelable(true)
                .create();
        dialog.show();
        view.postDelayed(() -> {
            dialog.dismiss();
            finish();
        }, 3000);
        return dialog;
    }

    protected AlertDialog showSuccess(View view, CallbackDialog callback) {

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_success)
                .setCancelable(true)
                .create();
        dialog.show();
        view.postDelayed(() -> {
            dialog.dismiss();
            callback.callback();
        }, 3000);
        return dialog;
    }

    protected AlertDialog showFailed(final CallbackDialog dialogCallback) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("提交失败")
                .setPositiveButton("重试", (dialog1, which) -> dialogCallback.callback())
                .setNegativeButton("取消", (dialog12, which) -> dialog12.dismiss()).create();
        dialog.show();
        return dialog;
    }


    protected HashMap<Class, BaseFragment> map2;

    protected void setAppComponentToFragment(AppComponent appComponent, Class<? extends BaseMvpFragment> classes) {
        BaseFragment baseFragment = map2.get(classes);
        if (baseFragment != null) {
            ((BaseMvpFragment) baseFragment).setAppComponent(appComponent);
        }
    }

    private void showNavigationIfNeed(boolean hidden) {
        if (navigationBar() != null)
            navigationBar().setVisibility(showNavigationCount > 1 && hidden ? View.VISIBLE : View.GONE);
    }

    public void show(BaseFragment fragment) {
        if (currentShow != null && currentShow.equals(fragment))
            return;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(fragment);
        if (currentShow != null) {
            fragmentTransaction.hide(currentShow);
        }
        currentShow = fragment;
        fragmentTransaction.commit();
    }

    protected LinkedList<Class<? extends BaseFragment>> last;

    private BaseFragment currentShow;

    @Override
    public void onClose() {
        if (last.size() > 0) {
            show(map2.get(last.removeLast()));
            showNavigationIfNeed(last.size() == 0);
        }
    }


    private void putAnded(Class<? extends BaseFragment> classes, BaseFragment o) {
        map2.put(classes, o);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(fragmentInsertId(), o).hide(o);
        fragmentTransaction.commit();
    }


    protected int fragmentInsertId() {
        return 0;
    }

    protected View navigationBar() {
        return null;
    }

    @Override
    public void open(Class<? extends BaseFragment> classes, boolean isRoot) {
        if (navigationBar() != null)
            navigationBar().setVisibility(isRoot ? View.VISIBLE : View.GONE);
        BaseFragment fragment = map2.get(classes);
        if (fragment == null) {
            try {
                fragment = classes.newInstance();
                putAnded(classes, fragment);
                fragment.setCallback(this, this);

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (!isRoot) last.add(currentShow.getClass());

        showNavigationIfNeed(isRoot);
        show(fragment);
    }

    @Override
    public void open(Class<? extends BaseFragment> classes, Bundle bundle, boolean isBased) {
        open(classes, isBased);
        map2.get(classes).put(bundle);

    }

    @Override
    public void callExit() {

    }


    protected int showNavigationCount = 4;
}
