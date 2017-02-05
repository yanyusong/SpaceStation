package com.zsygfddsd.spacestation.base.module.network_refresh;


import android.content.Context;

import com.zsygfddsd.spacestation.base.module.base.Y_I_View;
import com.zsygfddsd.spacestation.base.module.network.Y_I_NetView;
import com.zsygfddsd.spacestation.common.helpers.http.ObservableFactory;
import com.zsygfddsd.spacestation.common.helpers.http.Subscriber.NetAndErrorCheckerSubscriber;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import rx.Observable;


/**
 * Created by mac on 16/6/11.
 * DATA:表示ComRespInfo<DATA> 中的DATA的bean
 * D:表示每一个item的bean
 */
public abstract class Y_NetRefreshPresenter<DATA> implements Y_I_NetRefreshPresenter<DATA> {

    private Context context;
    private Y_I_View mView;
    private Y_I_NetView mNetView;
    private Y_I_NetRefreshView mNetRefreshView;

    private Y_RefreshPresenterConfig config;

    public Y_NetRefreshPresenter(Context context, Y_I_View mView, Y_I_NetView mNetView, Y_I_NetRefreshView mNetRefreshView, Y_RefreshPresenterConfig config) {
        this.context = context;
        this.mView = mView;
        this.mNetView = mNetView;
        this.mNetRefreshView = mNetRefreshView;
        this.config = config;
    }

    public Y_NetRefreshPresenter(Context context, Y_I_View mView, Y_I_NetView mNetView, Y_I_NetRefreshView mNetRefreshView) {
        this(context, mView, mNetView, mNetRefreshView, new Y_RefreshPresenterConfig.Builder().create());
    }

    public NetAndErrorCheckerSubscriber getDefaultSubscriber() {
        return new NetAndErrorCheckerSubscriber<DATA>(context, config.errorHandler) {

            @Override
            public void onCompleted() {
                mNetRefreshView.hideRefreshInfication();
            }

            @Override
            public void onError(Throwable e) {
                mNetView.showLoadingError();
                mNetRefreshView.hideRefreshInfication();
            }

            @Override
            public void onNext(ComRespInfo<DATA> dataComRespInfo) {
                super.onNext(dataComRespInfo);
                if (dataComRespInfo.getResult()) {
                    mNetRefreshView.onBindViewData(dataComRespInfo);
                } else {
                    mView.showToast("刷新失败!");
                }
            }
        };
    }

    public void loadData(Observable<ComRespInfo<DATA>> observable) {
        NetAndErrorCheckerSubscriber subscriber = getDefaultSubscriber();
        ObservableFactory.createNetObservable(context, observable, mView.getRxView())
                .subscribe(subscriber);
    }

    @Override
    public void onRefreshData() {
        loadData(getRequestObservable());
    }

    public Y_RefreshPresenterConfig getConfig() {
        return config;
    }
}








































