package com.zsygfddsd.spacestation.base.module.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;


/**
 * Created by mac on 16/3/1.
 */
public abstract class Y_NetView implements Y_I_NetView {

    private ProgressDialog pDialog;

    private Context ct;

    public Y_NetView(Context ct) {
        this.ct = ct;
    }

    @Override
    public void showLoading(boolean cancelable, @Nullable final ILoadingCancelListener listener) {
        if (pDialog == null) {
            pDialog = new ProgressDialog(ct);
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
        pDialog.show();
    }

    @Override
    public void hideLoading() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
