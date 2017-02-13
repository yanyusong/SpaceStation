package com.zsygfddsd.spacestation.base;

import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

/**
 * Created by mac on 2017/2/9.
 */

public interface Y_I_Net {

    interface ILoadingCancelListener {
        void onLoadCancelListener();
    }

    void showLoading(boolean cancelable, ILoadingCancelListener listener);

    void hideLoading();

    void showLoadingError();

    void showEmptyPage();

    void showNotHasNetwork();

    /**
     * httperror预处理
     *
     * @param comRespInfo
     * @return true 则执行正常的处理代码，false 则不往下执行了
     */
    boolean checkAndHandleHttpError(ComRespInfo comRespInfo);

}
