package com.zsygfddsd.spacestation.base.module.network;

import android.content.Context;

/**
 * Created by mac on 2016/12/28.
 */

public abstract class Y_NetPresenter implements Y_I_NetPresenter {

    private Context context;

    private Y_I_NetView mView;

    public Y_NetPresenter(Context context, Y_I_NetView mView) {
        this.context = context;
        this.mView = mView;
    }
}
