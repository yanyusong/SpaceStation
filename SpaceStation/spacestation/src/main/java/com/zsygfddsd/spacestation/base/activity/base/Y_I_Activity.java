package com.zsygfddsd.spacestation.base.activity.base;

import com.zsygfddsd.spacestation.base.activity.F_RxAppCompatActivity;

/**
 * Created by mac on 2017/2/9.
 */

public interface Y_I_Activity {

    F_RxAppCompatActivity getRxView();

    void showToast(String content);

    void reCreateActivity();

}
