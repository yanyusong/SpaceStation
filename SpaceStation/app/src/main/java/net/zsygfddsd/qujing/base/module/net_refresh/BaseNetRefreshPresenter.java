package net.zsygfddsd.qujing.base.module.net_refresh;

import android.content.Context;

import com.zsygfddsd.spacestation.base.module.base.Y_I_View;
import com.zsygfddsd.spacestation.base.module.network.Y_I_NetView;
import com.zsygfddsd.spacestation.base.module.network_refresh.Y_I_NetRefreshView;
import com.zsygfddsd.spacestation.base.module.network_refresh.Y_NetRefreshPresenter;
import com.zsygfddsd.spacestation.base.module.network_refresh.Y_RefreshPresenterConfig;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import net.zsygfddsd.qujing.base.module.net.BaseNetPresenter;

import rx.Observable;

/**
 * Created by mac on 2016/12/29.
 */

public abstract class BaseNetRefreshPresenter<DATA> extends BaseNetPresenter implements BaseNetRefreshContract.INetRefreshPresenter<DATA> {

    private NetRefreshPresenter netRefreshPresenter;
    private Y_RefreshPresenterConfig refreshConfig;

    public BaseNetRefreshPresenter(Context context, BaseNetRefreshContract.INetRefreshView mView) {
        super(context, mView);
        if (netRefreshPresenter == null) {
            netRefreshPresenter = new NetRefreshPresenter(context, mView, mView, mView, getRefreshConfig());
        }
    }

    public Y_RefreshPresenterConfig getRefreshConfig() {
        if (refreshConfig == null) {
            refreshConfig = new Y_RefreshPresenterConfig.Builder().create();
        }
        return refreshConfig;
    }

    @Override
    public void onRefreshData() {
        netRefreshPresenter.onRefreshData();
    }


    class NetRefreshPresenter extends Y_NetRefreshPresenter<DATA> {

        public NetRefreshPresenter(Context context, Y_I_View mView, Y_I_NetView mNetView, Y_I_NetRefreshView mNetRefreshView, Y_RefreshPresenterConfig config) {
            super(context, mView, mNetView, mNetRefreshView, config);
        }

        @Override
        public Observable<ComRespInfo<DATA>> getRequestObservable() {
            return BaseNetRefreshPresenter.this.getRequestObservable();
        }
    }

}
