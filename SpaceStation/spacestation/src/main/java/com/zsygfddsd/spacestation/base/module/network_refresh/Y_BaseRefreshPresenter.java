package com.zsygfddsd.spacestation.base.module.network_refresh;


import android.content.Context;

import com.zsygfddsd.spacestation.base.module.network.Y_BaseNetPresenter;
import com.zsygfddsd.spacestation.common.helpers.http.ObservableFactory;
import com.zsygfddsd.spacestation.common.helpers.http.Subscriber.NetAndErrorCheckerSubscriber;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import rx.Observable;


/**
 * Created by mac on 16/6/11.
 * DATA:表示ComRespInfo<DATA> 中的DATA的bean
 * D:表示每一个item的bean
 */
public abstract class Y_BaseRefreshPresenter<DATA> extends Y_BaseNetPresenter implements Y_BaseRefreshContract.IBaseRefreshPresenter {

    private Context context;
    private Y_BaseRefreshContract.IBaseRefreshView mView;

    private Y_RefreshPresenterConfig config;

    public Y_BaseRefreshPresenter(Context context, Y_BaseRefreshContract.IBaseRefreshView mView, Y_RefreshPresenterConfig config) {
        super(mView);
        this.context = context;
        this.mView = mView;
        this.config = config;
    }

    public Y_BaseRefreshPresenter(Context context, Y_BaseRefreshContract.IBaseRefreshView mView) {
        this(context, mView, new Y_RefreshPresenterConfig.Builder().create());
    }

    @Override
    public void start() {
        super.start();
    }

    public NetAndErrorCheckerSubscriber getDefaultSubscriber() {
        return new NetAndErrorCheckerSubscriber<DATA>(context, config.errorHandler) {

            @Override
            public void onCompleted() {
                mView.hideRefreshInfication();
            }

            @Override
            public void onError(Throwable e) {
                mView.showLoadingError();
                mView.hideRefreshInfication();
            }

            @Override
            public void onNext(ComRespInfo<DATA> dataComRespInfo) {
                super.onNext(dataComRespInfo);
                if (dataComRespInfo.getResult()) {
                    mView.onBindViewData(dataComRespInfo);
                } else {
                    mView.showToast("刷新失败!");
                }
            }
        };
    }

    public abstract Observable<ComRespInfo<DATA>> getRequestObservable();

    public void loadData(Observable<ComRespInfo<DATA>> observable) {
        NetAndErrorCheckerSubscriber subscriber = getDefaultSubscriber();
        ObservableFactory.createNetObservable(context, observable, mView.getRxView())
                .subscribe(subscriber);
    }

    @Override
    public void onRefreshData() {
        loadData(getRequestObservable());
    }

}








































