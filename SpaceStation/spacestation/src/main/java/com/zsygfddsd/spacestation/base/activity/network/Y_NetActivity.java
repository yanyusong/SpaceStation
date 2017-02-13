package com.zsygfddsd.spacestation.base.activity.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.util.Log;


public abstract class Y_NetActivity implements Y_I_NetActivity {

    public Context context;
    public ProgressDialog pDialog;

    public Y_NetActivity(Context context) {
        this.context = context;
    }

    public void showLoading(boolean cancelable, @Nullable final ILoadingCancelListener listener) {
        if (pDialog == null) {
            pDialog = new ProgressDialog(context);
        }
        pDialog.setCancelable(cancelable);
        pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (listener != null) {
                    listener.onLoadCancelListener();
                }
                hideLoading();
            }
        });
        pDialog.setMessage("Loading...");
        Log.e("Thread", "showLoading线程的名字是---------" + Thread.currentThread().getName());
        pDialog.show();

    }

    @Override
    public void hideLoading() {
        if (pDialog != null && pDialog.isShowing()) {
            Log.e("Thread", "hideLoading线程的名字是---------" + Thread.currentThread().getName());
            pDialog.dismiss();
        }
    }




}
