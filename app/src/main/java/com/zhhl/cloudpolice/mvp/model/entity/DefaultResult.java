package com.zhhl.cloudpolice.mvp.model.entity;

/**
 * Created by miao on 2018/12/21.
 */
public class DefaultResult {
    /**
     * success : true
     * msg : 更新成功
     * obj : null
     * attributes : null
     */

    private boolean success;
    private String msg;
    private Object obj;
    private Object attributes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }
}
