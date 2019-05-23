package com.zhhl.cloudpolice.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.cloudpolice.common.model.BaseModel;
import com.zhhl.cloudpolice.mvp.Contract.MainContract;

/**
 * Created by miao on 2018/9/19.
 */

public class MainModel extends BaseModel implements MainContract.Model {

    public MainModel(Gson gson, Application application) {
        super(application, gson);
    }


}

