package com.zsygfddsd.spacestation.base.module.network_refresh;

import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import rx.Observable;

/**
 * Created by mac on 2016/12/29.
 */

public interface Y_I_NetRefreshPresenter<DATA> {

    void onRefreshData();
    
    Observable<ComRespInfo<DATA>> getRequestObservable();

}
