package com.zhhl.cloudpolice.common.activity;

import android.os.Bundle;

import com.zhhl.cloudpolice.common.fragment.BaseFragment;


/**
 * Created by miao on 2018/10/24.
 */
public interface FragmentOpenCallback {
    void open(Class<? extends BaseFragment> classes, boolean isBased);

    void open(Class<? extends BaseFragment> classes, Bundle bundle, boolean isBased);

}
