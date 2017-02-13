package com.zsygfddsd.spacestation.base.fragment.network;

import android.content.Context;

import com.zsygfddsd.spacestation.base.activity.network.Y_I_NetActivity;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;


/**
 * Created by mac on 16/3/1.
 */
public class Y_NetView implements Y_I_NetView {

    private Context ct;

    public Y_NetView(Context ct) {
        this.ct = ct;
        if (!(ct instanceof Y_I_NetActivity)) {
            throw new IllegalArgumentException("the activity of " + this.getClass().getSimpleName() + " must implements Y_I_NetActivity!");
        }
    }


    @Override
    public void showLoading(boolean cancelable, ILoadingCancelListener listener) {
        ((Y_I_NetActivity) ct).showLoading(cancelable, listener);
    }

    @Override
    public void hideLoading() {
        ((Y_I_NetActivity) ct).hideLoading();
    }

    @Override
    public void showLoadingError() {
        ((Y_I_NetActivity) ct).showLoadingError();
    }

    @Override
    public void showEmptyPage() {
        ((Y_I_NetActivity) ct).showEmptyPage();
    }

    @Override
    public void showNotHasNetwork() {
        ((Y_I_NetActivity) ct).showNotHasNetwork();
    }

    @Override
    public boolean checkAndHandleHttpError(ComRespInfo comRespInfo) {
        return ((Y_I_NetActivity) ct).checkAndHandleHttpError(comRespInfo);
    }
}
