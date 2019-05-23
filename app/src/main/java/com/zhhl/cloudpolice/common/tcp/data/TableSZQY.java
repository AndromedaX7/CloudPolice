package com.zhhl.cloudpolice.common.tcp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by miao on 2018/11/1.
 */
public class TableSZQY {
    /**
     * success : true
     * msg : 查询成功
     * obj : [{"name":"段云龙","phone":"18843134285","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","identity_num":"429006199301054295","gasoline_use":"汽车没有油了","litre":"2L","create_date":"2018-08-10 13:38","userid":"18043134285","id":"0790870DB0EF4B5DB2FC2B3C2EFDFD66","longitude":"125.29014","latitude":"43.84067"},{"name":"段云龙","phone":"17600194545","address":"吉林省长春市朝阳区南湖街道前进大街","identity_num":"429006199301054295","gasoline_use":"智慧互联测试","litre":"1","create_date":"2018-09-03 17:20","userid":"17600194545","id":"11F7E494EE0F484BBCDC59B13106C545","longitude":"125.289965","latitude":"43.841316"},{"name":"冯金龙","phone":"16643416141","address":"长春市朝阳区力旺广场","identity_num":"220183199906051010","gasoline_use":"给汽车加97号汽油","litre":"20","create_date":"2018-09-03 17:20","userid":"17600194545","id":"52E1DEBD377C44C8B3E831F2A2593E08","longitude":"125.29014","latitude":"43.84067"},{"name":"冯金龙","phone":"17600194545","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","identity_num":"220183199606051012","gasoline_use":"给摩托加油","litre":"20","create_date":"2018-08-10 11:16","userid":"17600194545","id":"668DC5862921459E94332B0FD3A507BE","longitude":"125.29014","latitude":"43.84067"},{"name":"段云龙","phone":"18043134585","address":"吉林省长春市力旺广场","identity_num":"429006199301054295","gasoline_use":"预备使用","litre":"5","create_date":"2018-09-07 14:55","userid":"18043134285","id":"829ADCB6A77546FCAFA810C2EB08063C","longitude":"125.29014","latitude":"43.84067"},{"name":"段云龙","phone":"18843134285","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","identity_num":"429006199301054295","gasoline_use":"汽车没有油了","litre":"2L","create_date":"2018-09-07 14:55","userid":"18043134285","id":"0790870DB0EF4B5DB2FC2B3C2EFDFD66","longitude":"125.29014","latitude":"43.84067"},{"name":"冯金龙","phone":"16643416141","address":"长春市朝阳区力旺广场","identity_num":"220183199906051010","gasoline_use":"给汽车加97号汽油","litre":"20","create_date":"2018-09-07 14:55","userid":"17600194545","id":"52E1DEBD377C44C8B3E831F2A2593E08","longitude":"125.29014","latitude":"43.84067"},{"name":"冯金龙","phone":"17600194545","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","identity_num":"220183199606051012","gasoline_use":"给摩托加油","litre":"20","create_date":"2018-08-10 11:16","userid":"17600194545","id":"668DC5862921459E94332B0FD3A507BE","longitude":"125.29014","latitude":"43.84067"}]
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
        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        /**
         * name : 段云龙
         * phone : 18843134285
         * address : 吉林省长春市朝阳区南湖街道前进大街力旺广场
         * identity_num : 429006199301054295
         * gasoline_use : 汽车没有油了
         * litre : 2L
         * create_date : 2018-08-10 13:38
         * userid : 18043134285
         * id : 0790870DB0EF4B5DB2FC2B3C2EFDFD66
         * longitude : 125.29014
         * latitude : 43.84067
         */


        private String flag;
        private String name;
        private String phone;
        private String address;
        private String identity_num;
        private String gasoline_use;
        private String litre;
        private String create_date;
        private String userid;
        private String id;
        private String longitude;
        private String latitude;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIdentity_num() {
            return identity_num;
        }

        public void setIdentity_num(String identity_num) {
            this.identity_num = identity_num;
        }

        public String getGasoline_use() {
            return gasoline_use;
        }

        public void setGasoline_use(String gasoline_use) {
            this.gasoline_use = gasoline_use;
        }

        public String getLitre() {
            return litre;
        }

        public void setLitre(String litre) {
            this.litre = litre;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
            dest.writeString(this.name);
            dest.writeString(this.phone);
            dest.writeString(this.address);
            dest.writeString(this.identity_num);
            dest.writeString(this.gasoline_use);
            dest.writeString(this.litre);
            dest.writeString(this.create_date);
            dest.writeString(this.userid);
            dest.writeString(this.id);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
            dest.writeString(flag);
        }

        public ObjBean() {
        }

        protected ObjBean(Parcel in) {
            this.name = in.readString();
            this.phone = in.readString();
            this.address = in.readString();
            this.identity_num = in.readString();
            this.gasoline_use = in.readString();
            this.litre = in.readString();
            this.create_date = in.readString();
            this.userid = in.readString();
            this.id = in.readString();
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
