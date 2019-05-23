package com.zhhl.cloudpolice.common.tcp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.zhhl.cloudpolice.app.Application;


/**
 * Created by miao on 2018/9/3.
 */
public class NetworkStateCheck {
    private static final String TAG = NetworkStateCheck.class.getSimpleName();

    public static int getState() {
        ConnectivityManager connectivityManager = (ConnectivityManager)  Application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected()) {
            String name = activeNetworkInfo.getTypeName();
            Log.e(TAG, "network State: " + name);
            switch (name) {
                case "WIFI":
                    return WIFI_STATE;
                case "MOBILE":
                    return MOBILE_STATE;
                default:
                    return NO_CONNECT_STATE;
            }
        } else {
            return NO_CONNECT_STATE;
        }
    }

    public static boolean isConnection(int state) {
        return state > 0;
    }

    public static final int WIFI_STATE = 2;
    public static final int MOBILE_STATE = 1;
    public static final int NO_CONNECT_STATE = 0;
}
