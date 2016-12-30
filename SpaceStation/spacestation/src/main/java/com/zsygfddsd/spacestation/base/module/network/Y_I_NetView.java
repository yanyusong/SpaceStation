package com.zsygfddsd.spacestation.base.module.network;

/**
 * Created by mac on 2016/12/27.
 */

public interface Y_I_NetView {

    interface ILoadingCancelListener {
        void onLoadCancelListener();
    }

    void showLoading(boolean cancelable, ILoadingCancelListener listener);

    void hideLoading();

    void showLoadingError();

    void showEmptyPage();

}
