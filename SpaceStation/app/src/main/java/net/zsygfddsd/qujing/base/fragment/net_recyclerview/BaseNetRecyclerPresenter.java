package net.zsygfddsd.qujing.base.fragment.net_recyclerview;

import android.content.Context;

import com.zsygfddsd.spacestation.base.fragment.base.Y_I_View;
import com.zsygfddsd.spacestation.base.fragment.network.Y_I_NetView;
import com.zsygfddsd.spacestation.base.fragment.network_recyclerview.Y_I_NetRecyclerView;
import com.zsygfddsd.spacestation.base.fragment.network_recyclerview.Y_NetRecyclerPresenter;
import com.zsygfddsd.spacestation.base.fragment.network_recyclerview.Y_PagePresenterConfig;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import net.zsygfddsd.qujing.base.fragment.net.BaseNetPresenter;

import java.util.List;

import rx.Observable;

/**
 * Created by mac on 2016/12/28.
 */

public abstract class BaseNetRecyclerPresenter<DATA, D> extends BaseNetPresenter implements BaseNetRecyclerContract.INetRecyclerPresenter<DATA, D> {

    private NetRecyclerPresenter netRecyclerPresenter;
    private Y_PagePresenterConfig pageConfig;

    public BaseNetRecyclerPresenter(Context context, BaseNetRecyclerContract.INetRecyclerView mView) {
        super(context, mView);
        if (netRecyclerPresenter == null) {
            netRecyclerPresenter = new NetRecyclerPresenter(context, mView, mView, mView, getPageConfig());
        }
    }

    public Y_PagePresenterConfig getPageConfig() {
        if (pageConfig == null) {
            return new Y_PagePresenterConfig.Builder().create();
        }
        return pageConfig;
    }


    @Override
    public void onInitData() {
        netRecyclerPresenter.onInitData();
    }

    @Override
    public void onLoadMore() {
        netRecyclerPresenter.onLoadMore();
    }

    @Override
    public void onLoadRefresh() {
        netRecyclerPresenter.onLoadRefresh();
    }

    @Override
    public void clearList() {
        netRecyclerPresenter.clearList();
    }

    private class NetRecyclerPresenter extends Y_NetRecyclerPresenter<DATA, D> {


        NetRecyclerPresenter(Context context, Y_I_View mView, Y_I_NetView mNetView, Y_I_NetRecyclerView mNetRecyclerView, Y_PagePresenterConfig pageConfig) {
            super(context, mView, mNetView, mNetRecyclerView, pageConfig);
        }

        @Override
        public boolean getIsHasNextFromResponse(DATA result) {
            return BaseNetRecyclerPresenter.this.getIsHasNextFromResponse(result);
        }

        @Override
        public List<D> getListFromResponse(DATA result) {
            return BaseNetRecyclerPresenter.this.getListFromResponse(result);
        }

        @Override
        public Observable<ComRespInfo<DATA>> getRequestObservable(int page, int pageSize) {
            return BaseNetRecyclerPresenter.this.getRequestObservable(page, pageSize);
        }
    }


}
