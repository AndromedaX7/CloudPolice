package com.zhhl.cloudpolice.common.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zhhl.cloudpolice.common.activity.FragmentCloseCallback;
import com.zhhl.cloudpolice.common.activity.FragmentOpenCallback;


/**
 * Created by miao on 2018/10/24.
 */
public class BaseFragment extends Fragment {

    protected Bundle bundle;
    protected FragmentOpenCallback openCallback;
    protected FragmentCloseCallback closeCallback;

    public void setCallback(FragmentOpenCallback op, FragmentCloseCallback oc) {
        this.openCallback = op;
        this.closeCallback = oc;
    }

    public void put(Bundle bundle) {
        this.bundle = bundle;
    }
}
