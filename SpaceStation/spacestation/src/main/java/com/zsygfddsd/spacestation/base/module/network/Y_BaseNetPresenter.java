package com.zsygfddsd.spacestation.base.module.network;


import com.zsygfddsd.spacestation.base.module.base.Y_BaseContract;

/**
 * Created by mac on 16/6/13.
 */
public class Y_BaseNetPresenter implements Y_BaseContract.IBasePresenter {

    private Y_BaseNetContract.IBaseNetView mView;

    public Y_BaseNetPresenter(Y_BaseNetContract.IBaseNetView mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }
}
