package com.zhhl.cloudpolice.mvp.model.entity;

import java.util.List;

/**
 * Created by miao on 2018/11/29.
 */
public class ItemDetails {

    /**
     * success : true
     * msg : 查询成功
     * obj : [{"name":"段云龙","phone":"18043134585","address":"吉林省长春市力旺广场","identity_num":"429006199301054295","gasoline_use":"预备使用","litre":"5","create_date":"2018-09-07 14:55","userid":"18043134285","id":"829ADCB6A77546FCAFA810C2EB08063C","longitude":"125.29014","latitude":"43.84067"}]
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

    public static class ObjBean {
        /**
         * name : 段云龙
         * phone : 18043134585
         * address : 吉林省长春市力旺广场
         * identity_num : 429006199301054295
         * gasoline_use : 预备使用
         * litre : 5
         * create_date : 2018-09-07 14:55
         * userid : 18043134285
         * id : 829ADCB6A77546FCAFA810C2EB08063C
         * longitude : 125.29014
         * latitude : 43.84067
         */

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
    }
}
