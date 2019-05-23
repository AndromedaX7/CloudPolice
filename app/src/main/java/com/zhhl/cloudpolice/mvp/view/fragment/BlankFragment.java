package com.zhhl.cloudpolice.mvp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.common.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment {

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
