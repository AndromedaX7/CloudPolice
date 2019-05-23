package com.zhhl.cloudpolice.tcp;

import com.zhhl.cloudpolice.mvp.model.entity.Statistics;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by miao on 2018/9/19.
 */
public interface NetworkInterface {

    interface IQuery {

        @GET("/qywxtj/count/hydwtj")
        Observable<ArrayList<Statistics>> hangye(@Query("create_datefrom") String create_datefrom, @Query("create_dateto") String create_dateto);

        @GET("/qywxtj/count/cyrytj")
        Observable<ArrayList<Statistics>> congye(@Query("create_datefrom") String create_datefrom, @Query("create_dateto") String create_dateto);

        @GET("/qywxtj/count/jtcltj")
        Observable<ArrayList<Statistics>> cheliang(@Query("create_datefrom") String create_datefrom, @Query("create_dateto") String create_dateto);

        @GET("/qywxtj/count/ksfwtj")
        Observable<ArrayList<Statistics>> kaisuo(@Query("create_datefrom") String create_datefrom, @Query("create_dateto") String create_dateto);

        @GET("/qywxtj/count/szqytj")
        Observable<ArrayList<Statistics>> sanzhuangqiyou(@Query("create_datefrom") String create_datefrom, @Query("create_dateto") String create_dateto);

        @GET("/qywxtj/count/wzrztj")
        Observable<ArrayList<Statistics>> wuzhengjuzhu(@Query("create_datefrom") String create_datefrom, @Query("create_dateto") String create_dateto);


//        @Headers({
//            "Content-Type:text/xml;charset=utf-8",
//            "SOAPAction:http://webservice.jws.jeecgframework.org/findUserInfo"
//        })
//        @POST("http://www.freetk.cn:8789/cxf/UserInfoWebService?wsdl")
//        Call<ResponseBody>call(@Body String b);
    }


//    //查询行业单位统计数据
//    http://yj.jlsgaj.cn:5380/qywxtj/count/hydwtj?create_datefrom=2018-1-1&create_dateto=2018-12-12
//
////查询从业人员统计数据
//    http://yj.jlsgaj.cn:5380/qywxtj/count/cyrytj?create_datefrom=2018-1-1&create_dateto=2018-12-12
//
////查询静态车辆统计数据
//    http://yj.jlsgaj.cn:5380/qywxtj/count/jtcltj?create_datefrom=2018-1-1&create_dateto=2018-12-12
//
////查询开锁服务统计数据
//    http://yj.jlsgaj.cn:5380/qywxtj/count/ksfwtj?create_datefrom=2018-1-1&create_dateto=2018-12-12
//
////查询散装汽油统计数据
//    http://yj.jlsgaj.cn:5380/qywxtj/count/szqytj?create_datefrom=2018-1-1&create_dateto=2018-12-12
//
////查询无证入住统计数据
//    http://yj.jlsgaj.cn:5380/qywxtj/count/wzrztj?create_datefrom=2018-1-1&create_dateto=2018-12-12
}
