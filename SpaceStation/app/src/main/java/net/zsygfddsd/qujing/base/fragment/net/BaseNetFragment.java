package net.zsygfddsd.qujing.base.fragment.net;

import android.app.Activity;
import android.content.Context;

import com.zsygfddsd.spacestation.base.fragment.network.Y_NetView;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import net.zsygfddsd.qujing.base.fragment.base.BaseFragment;

/**
 * Created by mac on 2016/12/26.
 */

public abstract class BaseNetFragment<T extends BaseNetContract.INetPresenter> extends BaseFragment<T> implements BaseNetContract.INetView<T> {

    private NetView netView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (netView == null) {
            netView = new NetView(ct);
        }
    }

    @Override
    public void showLoading(boolean cancelable, ILoadingCancelListener listener) {
        netView.showLoading(cancelable, listener);
    }

    @Override
    public void hideLoading() {
        netView.hideLoading();
    }

    @Override
    public void showLoadingError() {
        netView.showLoadingError();
    }

    @Override
    public void showEmptyPage() {
        netView.showEmptyPage();
    }

    @Override
    public void showNotHasNetwork() {
        netView.showNotHasNetwork();
    }

    @Override
    public boolean checkAndHandleHttpError(ComRespInfo comRespInfo) {
        return netView.checkAndHandleHttpError(comRespInfo);
    }

    class NetView extends Y_NetView {

        NetView(Context ct) {
            super(ct);
        }
    }

}
