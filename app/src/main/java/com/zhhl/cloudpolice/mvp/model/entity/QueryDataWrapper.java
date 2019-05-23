package com.zhhl.cloudpolice.mvp.model.entity;

import com.zhhl.cloudpolice.mvp.QueryResultAdapter;

/**
 * Created by miao on 2018/11/12.
 */
public class QueryDataWrapper {
    private QueryResultData queryResultData;
    private QueryResultAdapter.Flag flag;


    public QueryDataWrapper(QueryResultData queryResultData, QueryResultAdapter.Flag flag) {
        this.queryResultData = queryResultData;
        this.flag = flag;
    }

    public QueryResultAdapter.Flag getFlag() {

        return flag;
    }

    public void setFlag(QueryResultAdapter.Flag flag) {
        this.flag = flag;
    }

    public QueryResultData getQueryResultData() {

        return queryResultData;
    }

    public void setQueryResultData(QueryResultData queryResultData) {
        this.queryResultData = queryResultData;
    }
}
