package com.zhhl.cloudpolice.common.tcp.inf;

import com.zhhl.cloudpolice.common.tcp.Api;
import com.zhhl.cloudpolice.common.tcp.data.TableHYRY;
import com.zhhl.cloudpolice.common.tcp.data.TableKSXX;
import com.zhhl.cloudpolice.common.tcp.data.TableSZQY;
import com.zhhl.cloudpolice.common.tcp.data.TableWZRZ;
import com.zhhl.cloudpolice.mvp.model.entity.DefaultResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by miao on 2018/11/1.
 */
public interface QueryItem {
    @FormUrlEncoded
    @POST(Api.QueryAll.queryItem)
    Observable<TableHYRY> queryHYRY(@Field("table") String table, @Field("id") String id
    );


    @FormUrlEncoded
    @POST(Api.QueryAll.queryItem)
    Observable<TableKSXX> queryKSXX(@Field("table") String table, @Field("id") String id);

    ;

    @FormUrlEncoded
    @POST(Api.QueryAll.queryItem)
    Observable<TableSZQY> querySZQY(@Field("table") String table, @Field("id") String id);

    ;

    @FormUrlEncoded
    @POST(Api.QueryAll.queryItem)
    Observable<TableWZRZ> queryWZRZ(@Field("table") String table, @Field("id") String id);

    ;

    @FormUrlEncoded
    @POST("/qywxtj/find/upObjectInfoById")
    Observable<DefaultResult> updateItem(@Field("table") String table, @Field("id") String id);
}
