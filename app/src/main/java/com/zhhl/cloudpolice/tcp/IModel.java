package com.zhhl.cloudpolice.tcp;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by miao on 2018/11/26.
 */
public interface IModel {
    @POST("http://192.168.20.228:7098/jeesite/jinghao/yanzheng")
    @FormUrlEncoded
    Observable<Boolean> checkPermission(@Field("jinghao") String jinghao, @Field("dizhi") String dizhi);
}
