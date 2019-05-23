package com.zhhl.cloudpolice.mvp.presenter;

import android.app.Application;

import com.zhhl.cloudpolice.common.di.FragmentScope;
import com.zhhl.cloudpolice.common.presenter.BasePresenter;
import com.zhhl.cloudpolice.mvp.Contract.StatisticsContract;
import com.zhhl.cloudpolice.mvp.model.entity.Statistics;
import com.zhhl.cloudpolice.mvp.view.fragment.StatisticsFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by miao on 2018/9/19.
 */

@FragmentScope
public class StatisticsPresenter extends BasePresenter<StatisticsContract.Model, StatisticsContract.View> {
    private Application mApplication;

    @Inject
    public StatisticsPresenter(StatisticsContract.Model model, StatisticsContract.View rootView
            , Application application) {
        super(model, rootView);
        this.mApplication = application;
    }



    public void getType(int idx, String startDate, String endDate) {

        ArrayList<Observable<ArrayList<Statistics>>> list = new ArrayList<>();
        Observable<ArrayList<Statistics>> result = null;
        switch (idx) {
            case StatisticsFragment.hangye:
                result = mModel.hangye(startDate, endDate);
                break;
            case StatisticsFragment.congye:
                result = mModel.congye(startDate, endDate);
                break;

            case StatisticsFragment.cheliang:
                result = mModel.cheliang(startDate, endDate);
                break;
            case StatisticsFragment.kaisuo:
                result = mModel.kaisuo(startDate, endDate);
                break;
            case StatisticsFragment.sanzhuangqiyou:
                result = mModel.sanzhuangqiyou(startDate, endDate);
                break;
            case StatisticsFragment.wuzhengjuzhu:
                result = mModel.wuzhengjuzhu(startDate, endDate);
                break;
            case StatisticsFragment.all:
                list.clear();
                list.add(mModel.hangye(startDate, endDate));
                list.add(mModel.congye(startDate, endDate));
                list.add(mModel.cheliang(startDate, endDate));
                list.add(mModel.kaisuo(startDate, endDate));
                list.add(mModel.sanzhuangqiyou(startDate, endDate));
                list.add(mModel.wuzhengjuzhu(startDate, endDate));

        }


        for (int i = 0; i < list.size(); i++) {
            Observable<ArrayList<Statistics>> arrayListObservable = list.get(i);
            int finalI = i;
            arrayListObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(statistics -> mRootView.data(statistics, finalI,startDate,endDate,false), this::onError, this::onComplete).isDisposed();
        }


        if (result != null) {
            result.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((d)->mRootView.data(d,idx,startDate,endDate,true), this::onError, this::onComplete)
                    .isDisposed();

        }
    }


}
