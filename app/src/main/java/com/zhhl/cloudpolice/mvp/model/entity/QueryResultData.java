package com.zhhl.cloudpolice.mvp.model.entity;

import android.os.Parcelable;

import com.zhhl.cloudpolice.DateUtil;
import com.zhhl.cloudpolice.common.tcp.data.TableHYRY;
import com.zhhl.cloudpolice.common.tcp.data.TableKSXX;
import com.zhhl.cloudpolice.common.tcp.data.TableSZQY;
import com.zhhl.cloudpolice.common.tcp.data.TableWZRZ;

/**
 * Created by miao on 2018/11/6.
 */
public class QueryResultData {
    private long time;
    private int type;
    private String identity;
    private String name;

    public String getName() {
        return name;
    }


    public String getIdentity() {
        return identity;
    }

    private TableHYRY.ObjBean hy;
    private TableKSXX.ObjBean ks;
    private TableSZQY.ObjBean sz;
    private TableWZRZ.ObjBean wz;


    public QueryResultData(TableHYRY.ObjBean hy) {
        setData(hy);
    }

    private void setData(TableHYRY.ObjBean hy) {
        type = 1;
        this.hy = hy;
        this.time = DateUtil.parseDate("yyyy-MM-dd HH:mm:ss", hy.getCreate_date());
        identity = hy.getId_number();
        name = hy.getUser_name();
    }

    public QueryResultData(TableKSXX.ObjBean ks) {
        setData(ks);
    }

    private void setData(TableKSXX.ObjBean ks) {
        type = 2;
        this.ks = ks;
        this.time = DateUtil.parseDate("yyyy-MM-dd HH:mm", ks.getCreate_date());
        identity = ks.getBy_unlocking_person_num();
        name = ks.getBy_unlocking_person();
    }

    public QueryResultData(TableSZQY.ObjBean sz) {
        setData(sz);
    }

    private void setData(TableSZQY.ObjBean sz) {
        type = 3;
        this.sz = sz;
        time = DateUtil.parseDate("yyyy-MM-dd HH:mm", sz.getCreate_date());
        identity = sz.getIdentity_num();
        name = sz.getName();
    }

    public QueryResultData(TableWZRZ.ObjBean wz) {
        setData(wz);
    }

    private void setData(TableWZRZ.ObjBean wz) {
        type = 4;
        this.wz = wz;
        this.time = DateUtil.parseDate("yyyy-MM-dd HH:mm:ss", wz.getCreate_date());
        identity = wz.getIdentity_num();
        name = wz.getName();
    }


    public TableHYRY.ObjBean getHy() {
        return hy;
    }

    public TableKSXX.ObjBean getKs() {
        return ks;
    }

    public TableSZQY.ObjBean getSz() {
        return sz;
    }

    public TableWZRZ.ObjBean getWz() {
        return wz;
    }

    public QueryResultData() {
    }

    public int getType() {
        return type;
    }

    public long getTime() {
        return time;
    }

    public Parcelable getByType() {
        switch (getType()) {
            case 1:
                return hy;
            case 2:
                return ks;
            case 3:
                return sz;
            case 4:
                return wz;
        }
        return null;
    }


}
