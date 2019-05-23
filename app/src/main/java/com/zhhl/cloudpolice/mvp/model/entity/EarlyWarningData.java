package com.zhhl.cloudpolice.mvp.model.entity;

/**
 * Created by miao on 2018/11/6.
 */
public class EarlyWarningData {
    private long time;
    private String message;

    public EarlyWarningData() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EarlyWarningData(long time, String message) {

        this.time = time;
        this.message = message;
    }
}
