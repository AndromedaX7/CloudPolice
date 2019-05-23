package com.zhhl.cloudpolice.common.tcp.inf;

import com.zhhl.cloudpolice.common.tcp.Api;
import com.zhhl.cloudpolice.common.tcp.data.TableHYRY;
import com.zhhl.cloudpolice.common.tcp.data.TableKSXX;
import com.zhhl.cloudpolice.common.tcp.data.TableSZQY;
import com.zhhl.cloudpolice.common.tcp.data.TableWZRZ;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by miao on 2018/11/1.
 */
public interface QueryAll {
    @POST(Api.QueryAll.query)
    @FormUrlEncoded
    Observable<TableHYRY> queryHYRY(@Field("table") String table);


    @FormUrlEncoded
    @POST(Api.QueryAll.query)
    Observable<TableKSXX> queryKSXX(@Field("table") String table);

    @FormUrlEncoded
    @POST(Api.QueryAll.query)
    Observable<TableSZQY> querySZQY(@Field("table") String table);

    @FormUrlEncoded
    @POST(Api.QueryAll.query)
    Observable<TableWZRZ> queryWZRZ(@Field("table") String table);

}
