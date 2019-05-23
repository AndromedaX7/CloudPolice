package com.zhhl.cloudpolice.common.tcp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by miao on 2018/11/1.
 */
public class TableHYRY {

    /**
     * success : true
     * msg : 查询成功
     * obj : [{"id":"B7E8287BD4E04732AFBE184847C4F1C9","user_name":"段云龙","position":null,"id_number":"429006199301054295","unit_code":null,"create_date":"2018-08-02 09:30:55","userid":"18043134285","phone":"18043134285","unit_code_id":"88467D75E2D04395B7C6FDC43895EEE2"},{"id":"AECF86AE7FA8400F8F459F7090571550","user_name":"段云龙","position":null,"id_number":"429006199301054295","unit_code":null,"create_date":"2018-08-02 09:36:31","userid":"18043134285","phone":"18043134285","unit_code_id":"88467D75E2D04395B7C6FDC43895EEE2"},{"id":"8059F0C23C774ABE9A70CB503C9773FB","user_name":"冯金龙","position":null,"id_number":"220183199606051012","unit_code":null,"create_date":"2018-08-02 09:35:25","userid":"17600194545","phone":"17600194545","unit_code_id":"88467D75E2D04395B7C6FDC43895EEE2"},{"id":"68A9BCE422E844E59BD4EA3F6F929C73","user_name":"段云龙","position":null,"id_number":"429006199301054295","unit_code":null,"create_date":"2018-08-02 09:24:49","userid":"18043134285","phone":"18043134285","unit_code_id":"88467D75E2D04395B7C6FDC43895EEE2"},{"id":"619EBD34BA2D4BB78B229A25E1E0B481","user_name":"段云龙","position":null,"id_number":"429006199301054295","unit_code":null,"create_date":"2018-08-02 09:40:53","userid":"18043134285","phone":"18043134285","unit_code_id":"88467D75E2D04395B7C6FDC43895EEE2"},{"id":"397EA198E5C545558F657FDDC69C6C35","user_name":"段云龙","position":null,"id_number":"429006199301054295","unit_code":null,"create_date":"2018-08-02 09:29:21","userid":"18043134285","phone":"18043134285","unit_code_id":"88467D75E2D04395B7C6FDC43895EEE2"},{"id":"0D41E21F07F047B894C9C49B678E8E66","user_name":"冯金龙","position":null,"id_number":"220183199606051012","unit_code":null,"create_date":"2018-08-02 09:25:37","userid":"17600194545","phone":"17600194545","unit_code_id":"88467D75E2D04395B7C6FDC43895EEE2"}]
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
         * id : B7E8287BD4E04732AFBE184847C4F1C9
         * user_name : 段云龙
         * position : null
         * id_number : 429006199301054295
         * unit_code : null
         * create_date : 2018-08-02 09:30:55
         * userid : 18043134285
         * phone : 18043134285
         * unit_code_id : 88467D75E2D04395B7C6FDC43895EEE2
         */

        private String id;
        private String user_name;
        private String position;
        private String id_number;
        private String unit_code;
        private String create_date;
        private String userid;
        private String phone;
        private String unit_code_id;
        private String flag;
//
        public String getFlag() {
            return flag;
        }
//
        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public Object getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public Object getUnit_code() {
            return unit_code;
        }

        public void setUnit_code(String unit_code) {
            this.unit_code = unit_code;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUnit_code_id() {
            return unit_code_id;
        }

        public void setUnit_code_id(String unit_code_id) {
            this.unit_code_id = unit_code_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.user_name);
            dest.writeString(this.position);
            dest.writeString(this.id_number);
            dest.writeString(this.unit_code);
            dest.writeString(this.create_date);
            dest.writeString(this.userid);
            dest.writeString(this.phone);
            dest.writeString(this.unit_code_id);
            dest.writeString(flag);
        }

        public ObjBean() {
        }

        protected ObjBean(Parcel in) {
            this.id = in.readString();
            this.user_name = in.readString();
            this.position = in.readString();
            this.id_number = in.readString();
            this.unit_code = in.readString();
            this.create_date = in.readString();
            this.userid = in.readString();
            this.phone = in.readString();
            this.unit_code_id = in.readString();
            this.flag = in.readString();
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
