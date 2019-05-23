//package com.zhhl.cloudpolice.tcp;
//
//import com.zhhl.cloudpolice.app.App;
//import com.zhhl.cloudpolice.mvp.model.entity.Statistics;
//
//import java.util.ArrayList;
//
//import io.reactivex.Observable;
//
//public class IQueryStaticProxy implements NetworkInterface.IQuery {
//
//    private NetworkInterface.IQuery iQuery;
//
//    private  ILogUploadImpl logUpload;
//
//    public IQueryStaticProxy(NetworkInterface.IQuery iQuery) {
//        this.iQuery = iQuery;
//        logUpload=App.app().getLogReport();
//    }
//
//    @Override
//    public Observable<ArrayList<Statistics>> hangye(String create_datefrom, String create_dateto) {
//        logUpload.log("{}");
//        return iQuery.hangye(create_datefrom, create_dateto);
//    }
//
//    @Override
//    public Observable<ArrayList<Statistics>> congye(String create_datefrom, String create_dateto) {
//        logUpload.log("{}");
//        return iQuery.congye(create_datefrom, create_dateto);
//    }
//
//    @Override
//    public Observable<ArrayList<Statistics>> cheliang(String create_datefrom, String create_dateto) {
//        logUpload.log("{}");
//        return iQuery.cheliang(create_datefrom, create_dateto);
//    }
//
//    @Override
//    public Observable<ArrayList<Statistics>> kaisuo(String create_datefrom, String create_dateto) {
//        logUpload.log("{}");
//        return iQuery.kaisuo(create_datefrom, create_dateto);
//    }
//
//    @Override
//    public Observable<ArrayList<Statistics>> sanzhuangqiyou(String create_datefrom, String create_dateto) {
//        logUpload.log("{}");
//        return iQuery.sanzhuangqiyou(create_datefrom, create_dateto);
//    }
//
//    @Override
//    public Observable<ArrayList<Statistics>> wuzhengjuzhu(String create_datefrom, String create_dateto) {
//        logUpload.log("{}");
//        return iQuery.wuzhengjuzhu(create_datefrom, create_dateto);
//    }
//}
