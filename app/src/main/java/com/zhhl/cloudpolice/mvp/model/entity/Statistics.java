package com.zhhl.cloudpolice.mvp.model.entity;

import android.support.annotation.NonNull;

/**
 * Created by miao on 2018/9/19.
 */
public class Statistics implements Comparable<Statistics> {

    /**
     * count : 11
     * createDate : null
     * name : 文庙派出所
     */

    private String count;
    private Object createDate;
    private String name;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Object getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull Statistics o) {
        return Integer.parseInt(this.count) - Integer.parseInt(o.getCount());
    }
}
