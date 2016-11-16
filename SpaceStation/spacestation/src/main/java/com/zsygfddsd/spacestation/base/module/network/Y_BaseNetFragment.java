package com.zsygfddsd.spacestation.base.module.network;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.Nullable;

import com.zsygfddsd.spacestation.base.module.base.Y_BaseContract;
import com.zsygfddsd.spacestation.base.module.base.Y_BaseFragment;


/**
 * Created by mac on 16/3/1.
 */
public abstract class Y_BaseNetFragment<T extends Y_BaseContract.IBasePresenter> extends Y_BaseFragment<T> implements Y_BaseNetContract.IBaseNetView<T> {

    public ProgressDialog pDialog;

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

    public void showLoadingError() {
        showToast("获取失败");
    }

    public void showEmptyPage() {
        showToast("暂无数据");
    }

}
