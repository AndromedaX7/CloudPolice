package com.zhhl.cloudpolice.common.tcp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by miao on 2018/11/1.
 */
public class TableWZRZ {

    /**
     * success : true
     * msg : 查询成功
     * obj : [{"id":"037424BBC7EE418EA48BA8474FFC79E2","image":"null","identity_num":"220183199606051012","result":"true","create_date":"2018-07-30 08:34:40","name":"冯金龙","userid":"17600194545","phone":"1","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","longitude":null,"latitude":null},{"id":"0F0D055136884C5CB0DCDB4AFE1D569A","image":"null","identity_num":"220183199606051012","result":"true","create_date":"2018-07-27 10:03:39","name":"冯金龙","userid":"17600194545","phone":"1","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","longitude":null,"latitude":null},{"id":"15847AFE37D44924BE4997E6AD0D45C9","image":"null","identity_num":"220183199606051012","result":"true","create_date":"2018-07-27 10:35:17","name":"冯金龙","userid":"17600194545","phone":"1","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","longitude":null,"latitude":null},{"id":"8ECF780BE60144749EA337932ACECC0B","image":"null","identity_num":"220183199606051012","result":"true","create_date":"2018-07-30 08:38:14","name":"冯金龙","userid":"17600194545","phone":"1","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","longitude":null,"latitude":null},{"id":"F33C7AA2969242819ACD94F4FB6EEF95","image":"null","identity_num":"220183199606081012","result":"false","create_date":"2018-07-30 08:31:40","name":"冯金龙","userid":"17600194545","phone":"1","address":"吉林省长春市朝阳区南湖街道前进大街力旺广场","longitude":null,"latitude":null}]
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
         * id : 037424BBC7EE418EA48BA8474FFC79E2
         * image : null
         * identity_num : 220183199606051012
         * result : true
         * create_date : 2018-07-30 08:34:40
         * name : 冯金龙
         * userid : 17600194545
         * phone : 1
         * address : 吉林省长春市朝阳区南湖街道前进大街力旺广场
         * longitude : null
         * latitude : null
         */


        private String flag;
        private String id;
        private String image;
        private String identity_num;
        private String result;
        private String create_date;
        private String name;
        private String userid;
        private String phone;
        private String address;
        private String longitude;
        private String latitude;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIdentity_num() {
            return identity_num;
        }

        public void setIdentity_num(String identity_num) {
            this.identity_num = identity_num;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
            dest.writeString(this.image);
            dest.writeString(this.identity_num);
            dest.writeString(this.result);
            dest.writeString(this.create_date);
            dest.writeString(this.name);
            dest.writeString(this.userid);
            dest.writeString(this.phone);
            dest.writeString(this.address);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
            dest.writeString(flag);
        }

        public ObjBean() {
        }

        protected ObjBean(Parcel in) {
            this.id = in.readString();
            this.image = in.readString();
            this.identity_num = in.readString();
            this.result = in.readString();
            this.create_date = in.readString();
            this.name = in.readString();
            this.userid = in.readString();
            this.phone = in.readString();
            this.address = in.readString();
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
