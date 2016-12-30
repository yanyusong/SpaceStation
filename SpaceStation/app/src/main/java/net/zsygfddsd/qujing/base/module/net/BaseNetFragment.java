package net.zsygfddsd.qujing.base.module.net;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zsygfddsd.spacestation.base.module.network.Y_NetView;

import net.zsygfddsd.qujing.base.module.base.BaseFragment;

/**
 * Created by mac on 2016/12/26.
 */

public abstract class BaseNetFragment<T extends BaseNetContract.INetPresenter> extends BaseFragment<T> implements BaseNetContract.INetView<T> {

    private NetView netView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    class NetView extends Y_NetView {

        NetView(Context ct) {
            super(ct);
        }

        @Override
        public void showLoadingError() {
            showToast("获取失败");
        }

        @Override
        public void showEmptyPage() {
            showToast("暂无数据");
        }
    }

}
