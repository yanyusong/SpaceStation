package com.zsygfddsd.spacestation.base.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.zsygfddsd.spacestation.base.Y_BaseApplication;
import com.zsygfddsd.spacestation.base.module.network.Y_BaseNetContract;


public class Y_BaseActivity extends F_RxAppCompatActivity implements Y_BaseNetContract.INetView {

    public Context mContext;
    public ProgressDialog pDialog;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        ((Y_BaseApplication) getApplication()).addActivity(this);
        /**
         * 设置为竖屏
         */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((Y_BaseApplication) getApplication()).removeActivity(this);
    }

    public void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    @Override
    public void showLoading(boolean cancelable, @Nullable final ILoadingCancelListener listener) {
        if (pDialog == null) {
            pDialog = new ProgressDialog(mContext);
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
            pDialog = null;
        }
    }

    public void showLoadingError() {
        showToast("获取失败");
    }

    public void showEmptyPage() {
        showToast("暂无数据");
    }

    public F_RxAppCompatActivity getRxView() {
        return this;
    }

}
