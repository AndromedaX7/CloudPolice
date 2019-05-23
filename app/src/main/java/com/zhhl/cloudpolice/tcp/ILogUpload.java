package com.zhhl.cloudpolice.tcp;


import com.zhhl.cloudpolice.mvp.model.entity.LogReportData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ILogUpload {
    @POST(Api.logReport)
    @Headers("content-type:application/json")
    Observable<Object> upload(@Body LogReportData data);
}
