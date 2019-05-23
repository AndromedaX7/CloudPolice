package com.zhhl.cloudpolice.common.tcp;

import com.zhhl.cloudpolice.BuildConfig;

public interface Api {
    boolean debug = true;


    interface __BASED__ {

        String __BASED_Url2 = "http://yj.jlsgaj.cn:5380/";
        String __LoginD_Url = "http://192.168.20.230:8081/";
        String __BASED_Url = "http://192.168.20.228:7103/";

        String count = "/listTj";


        static String base_url() {
            if (BuildConfig.IS_OUT_SITE){
                return __BASED_Url2;
            }else {
                return __BASED_Url;
            }
        }
    }

    interface QueryAll {
        String queryItem = "/qywxtj/find/objectInfoById";
        String query = "/qywxtj/find/objectInfo";
    }


}
