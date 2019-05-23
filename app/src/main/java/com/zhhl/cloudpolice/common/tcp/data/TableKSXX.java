package com.zhhl.cloudpolice.common.tcp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by miao on 2018/11/1.
 */
public class TableKSXX {

    /**
     * success : true
     * msg : 查询成功
     * obj : [{"id":"13FC37A3664C49AAA10671CAC90E9A76","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","detail_address":"B座1410室","unlocking_person":"冯金龙","unlocking_person_num":null,"by_unlocking_person":"冯金龙","by_unlocking_person_num":"220183199606051012","create_date":"2018-08-02 11:00","userid":"17600194545","dept_name":"系统维护","longitude":"125.29013","latitude":"43.84066"},{"id":"21800237DE3B499C9F75B393860F6E63","address":"吉林省长春市朝阳区南湖街道","detail_address":"力旺广场14","unlocking_person":"段云龙","unlocking_person_num":null,"by_unlocking_person":"段云龙","by_unlocking_person_num":"429006199301054295","create_date":"2018-09-07 15:11","userid":"18043134285","dept_name":"系统维护","longitude":"125.29013","latitude":"43.84066"},{"id":"6B3220CDE03E41D0A5B4A101B769CF02","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","detail_address":"A座1511室","unlocking_person":"冯金龙","unlocking_person_num":null,"by_unlocking_person":"冯金龙","by_unlocking_person_num":"220183199606051012","create_date":"2018-08-02 11:18","userid":"17600194545","dept_name":"系统维护","longitude":"125.29013","latitude":"43.84066"},{"id":"9AE27A262B2B471A9F20BBA945CA0207","address":"测试1","detail_address":"吉林市局","unlocking_person":"段云龙","unlocking_person_num":null,"by_unlocking_person":"段云龙","by_unlocking_person_num":"429006199301054295","create_date":"2018-09-28 11:36","userid":"18043134285","dept_name":"系统维护","longitude":"125.29013","latitude":"43.84066"},{"id":"E250327F837D48CD9274B2C09A5E8E75","address":"测试2","detail_address":"测试","unlocking_person":"段云龙","unlocking_person_num":null,"by_unlocking_person":"段云龙","by_unlocking_person_num":"429006199301054295","create_date":"2018-09-28 14:42","userid":"18043134285","dept_name":"系统维护","longitude":"125.29013","latitude":"43.84066"}]
     * attributes : null
     */

    private boolean success;
    private String msg;
    private Object attributes;
    private List<ObjBean> obj;

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

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean implements Parcelable {
        /**
         * id : 13FC37A3664C49AAA10671CAC90E9A76
         * address : 吉林省长春市朝阳区南湖街道前进大街力旺广场
         * detail_address : B座1410室
         * unlocking_person : 冯金龙
         * unlocking_person_num : null
         * by_unlocking_person : 冯金龙
         * by_unlocking_person_num : 220183199606051012
         * create_date : 2018-08-02 11:00
         * userid : 17600194545
         * dept_name : 系统维护
         * longitude : 125.29013
         * latitude : 43.84066
         */

        private String flag;
        private String id;
        private String address;
        private String detail_address;
        private String unlocking_person;
        private String unlocking_person_num;
        private String by_unlocking_person;
        private String by_unlocking_person_num;
        private String create_date;
        private String userid;
        private String dept_name;
        private String longitude;
        private String latitude;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
        }

        public String getUnlocking_person() {
            return unlocking_person;
        }

        public void setUnlocking_person(String unlocking_person) {
            this.unlocking_person = unlocking_person;
        }

        public Object getUnlocking_person_num() {
            return unlocking_person_num;
        }

        public void setUnlocking_person_num(String unlocking_person_num) {
            this.unlocking_person_num = unlocking_person_num;
        }

        public String getBy_unlocking_person() {
            return by_unlocking_person;
        }

        public void setBy_unlocking_person(String by_unlocking_person) {
            this.by_unlocking_person = by_unlocking_person;
        }

        public String getBy_unlocking_person_num() {
            return by_unlocking_person_num;
        }

        public void setBy_unlocking_person_num(String by_unlocking_person_num) {
            this.by_unlocking_person_num = by_unlocking_person_num;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getDept_name() {
            return dept_name;
        }

        public void setDept_name(String dept_name) {
            this.dept_name = dept_name;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.address);
            dest.writeString(this.detail_address);
            dest.writeString(this.unlocking_person);
            dest.writeString(this.unlocking_person_num);
            dest.writeString(this.by_unlocking_person);
            dest.writeString(this.by_unlocking_person_num);
            dest.writeString(this.create_date);
            dest.writeString(this.userid);
            dest.writeString(this.dept_name);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
            dest.writeString(flag);
        }

        public ObjBean() {
        }

        protected ObjBean(Parcel in) {
            this.id = in.readString();
            this.address = in.readString();
            this.detail_address = in.readString();
            this.unlocking_person = in.readString();
            this.unlocking_person_num = in.readString();
            this.by_unlocking_person = in.readString();
            this.by_unlocking_person_num = in.readString();
            this.create_date = in.readString();
            this.userid = in.readString();
            this.dept_name = in.readString();
            this.longitude = in.readString();
            this.latitude = in.readString();
            flag = in.readString();
        }

        public static final Parcelable.Creator<ObjBean> CREATOR = new Parcelable.Creator<ObjBean>() {
            @Override
            public ObjBean createFromParcel(Parcel source) {
                return new ObjBean(source);
            }

            @Override
            public ObjBean[] newArray(int size) {
                return new ObjBean[size];
            }
        };
    }
}
