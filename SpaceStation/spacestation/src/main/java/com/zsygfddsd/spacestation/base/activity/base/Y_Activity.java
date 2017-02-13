package com.zsygfddsd.spacestation.base.activity.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.zsygfddsd.spacestation.base.Y_BaseApplication;
import com.zsygfddsd.spacestation.base.activity.F_RxAppCompatActivity;

/**
 * Created by mac on 2017/2/9.
 */

public class Y_Activity extends F_RxAppCompatActivity implements Y_I_Activity {

    public Context context;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
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

    @Override
    public F_RxAppCompatActivity getRxView() {
        return this;
    }

    @Override
    public void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 本activity重新创建本activity
     */
    @Override
    public void reCreateActivity() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
