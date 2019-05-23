package com.zhhl.cloudpolice.mvp.Contract;


import com.zhhl.cloudpolice.common.ViewCommon;
import com.zhhl.cloudpolice.common.model.ModelCommon;
import com.zhhl.cloudpolice.mvp.model.entity.Statistics;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by miao on 2018/9/19.
 */

public abstract class StatisticsContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    public interface View extends ViewCommon {
        void data(ArrayList<Statistics> statistics, int idx, String startDate, String endDate, boolean onlyOne);

        void data(ArrayList<Statistics> statistics, int idx, String startDate, String endDate);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    public interface Model extends ModelCommon {
        Observable<ArrayList<Statistics>> hangye(String create_datefrom, String create_dateto);

        Observable<ArrayList<Statistics>> congye(String create_datefrom, String create_dateto);

        Observable<ArrayList<Statistics>> cheliang(String create_datefrom, String create_dateto);

        Observable<ArrayList<Statistics>> kaisuo(String create_datefrom, String create_dateto);

        Observable<ArrayList<Statistics>> sanzhuangqiyou(String create_datefrom, String create_dateto);

        Observable<ArrayList<Statistics>> wuzhengjuzhu(String create_datefrom, String create_dateto);
    }

}