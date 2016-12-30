package com.zsygfddsd.spacestation.base.module.network_recyclerview;

import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by mac on 2016/12/28.
 */

public interface Y_I_NetRecyclerPresenter<DATA, D> {

    void onInitData();

    void onLoadMore();

    void onLoadRefresh();

    void clearList();

    boolean getIsHasNextFromResponse(DATA result);

    List<D> getListFromResponse(DATA result);

    Observable<ComRespInfo<DATA>> getRequestObservable(int page, int pageSize);

}
