package com.zhhl.cloudpolice;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;

/**
 * Created by miao on 2018/11/12.
 */
public class DialogUtils {
    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("正在加载,请稍后...");
        return dialog;
    }

    public static ProgressDialog createProgressDialog(Context context, String msg) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(msg);
        return dialog;
    }

    public static AlertDialog showSuccess(View view, CallbackDialog callback) {

        final AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                .setView(R.layout.dialog_success)
                .setCancelable(false)
                .create();
        dialog.show();
        view.postDelayed(() -> {
            dialog.dismiss();
            callback.callback();
        }, 3000);
        return dialog;
    }

    public interface CallbackDialog {
        void callback();
    }

}
