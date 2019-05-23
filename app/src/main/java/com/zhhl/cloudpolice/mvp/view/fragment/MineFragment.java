package com.zhhl.cloudpolice.mvp.view.fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.app.App;
import com.zhhl.cloudpolice.common.LoginBean;
import com.zhhl.cloudpolice.common.fragment.BaseFragment;
import com.zhhl.cloudpolice.mvp.view.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {

    @BindView(R.id.mExit)
    TextView mExit;
    @BindView(R.id.mName)
    TextView mName;
    @BindView(R.id.mPhone)
    TextView mPhone;
    @BindView(R.id.mLevel)
    TextView mLevel;
    @BindView(R.id.mCode)
    TextView mCode;
    @BindView(R.id.qingbaoMian)
    TextView qingbaoMian;


    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginBean userInfo = App.app().getUserInfo();
        if (userInfo != null) {
            mCode.setText(userInfo.getUserInfo().getCode());
            mName.setText(userInfo.getUserInfo().getName());
            mLevel.setText(getZhicheng(userInfo.getUserInfo().getPosition()));
            mPhone.setText(userInfo.getUserInfo().getMobile());
        }
    }

    private String getZhicheng(String position) {
        int i = Integer.parseInt(position);
        switch (i) {
            case 3:
                return "其他";
            default:
                return "";
        }


    }

    @OnClick(R.id.mExit)
    void onExitPress() {
        ((MainActivity) getActivity()).onBackPressed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.qingbaoMian)
    public void onViewClicked() {
//        Intent intent = new Intent(getContext(), com.zhhl.qingbao.MainActivity.class);
//        intent.putExtra("has", true);
//        intent.putExtra("name", mName.getText().toString());
//        intent.putExtra("phone",  mPhone.getText().toString());
//        intent.putExtra("code",  mCode.getText().toString());
//        intent.putExtra("level",  mLevel.getText().toString());
//
//
//        startActivity(intent);
    }
}
