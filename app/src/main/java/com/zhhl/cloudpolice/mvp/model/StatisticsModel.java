package com.zhhl.cloudpolice.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.zhhl.cloudpolice.common.model.BaseModel;
import com.zhhl.cloudpolice.mvp.Contract.StatisticsContract;
import com.zhhl.cloudpolice.mvp.model.entity.Statistics;
import com.zhhl.cloudpolice.tcp.NetworkInterface;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by miao on 2018/9/19.
 */

public class StatisticsModel extends BaseModel implements StatisticsContract.Model {

    private NetworkInterface.IQuery iQuery;

    public StatisticsModel(Gson gson, Application application, NetworkInterface.IQuery iQuery) {
        super(application, gson);
        this.iQuery = iQuery;
    }


    @Override
    public Observable<ArrayList<Statistics>> hangye(String create_datefrom, String create_dateto) {
        return iQuery.hangye(create_datefrom,create_dateto);
    }

    @Override
    public Observable<ArrayList<Statistics>> congye(String create_datefrom, String create_dateto) {
        return iQuery.congye(create_datefrom,create_dateto);
    }

    @Override
    public Observable<ArrayList<Statistics>> cheliang(String create_datefrom, String create_dateto) {
        return iQuery.cheliang(create_datefrom,create_dateto);
    }

    @Override
    public Observable<ArrayList<Statistics>> kaisuo(String create_datefrom, String create_dateto) {
        return iQuery.kaisuo(create_datefrom,create_dateto);
    }

    @Override
    public Observable<ArrayList<Statistics>> sanzhuangqiyou(String create_datefrom, String create_dateto) {
        return iQuery.sanzhuangqiyou(create_datefrom,create_dateto);
    }

    @Override
    public Observable<ArrayList<Statistics>> wuzhengjuzhu(String create_datefrom, String create_dateto) {
        return iQuery.wuzhengjuzhu(create_datefrom,create_dateto);
    }
}

