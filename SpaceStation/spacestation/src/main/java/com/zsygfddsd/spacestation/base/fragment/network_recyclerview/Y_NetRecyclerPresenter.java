package com.zsygfddsd.spacestation.base.fragment.network_recyclerview;


import android.content.Context;

import com.zsygfddsd.spacestation.base.adapter.multirecycler.ItemEntityList;
import com.zsygfddsd.spacestation.base.fragment.base.Y_I_View;
import com.zsygfddsd.spacestation.base.fragment.network.Y_I_NetView;
import com.zsygfddsd.spacestation.common.helpers.http.ObservableFactory;
import com.zsygfddsd.spacestation.common.helpers.http.Subscriber.Y_NetAndHttpErrorCheckerSubscriber;
import com.zsygfddsd.spacestation.common.helpers.http.transformer.EmitBeforeAndAfterTransformer;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


/**
 * Created by mac on 16/6/11.
 * DATA:表示ComRespInfo<DATA> 中的DATA的bean
 * D:表示每一个item的bean
 */
public abstract class Y_NetRecyclerPresenter<DATA, D> implements Y_I_NetRecyclerPresenter<DATA, D> {

    protected Context context;
    private Y_I_View mView;
    private Y_I_NetView mNetView;
    private Y_I_NetRecyclerView mNetRecyclerView;

    protected int page = 1;
    protected int pageSize = 10;
    protected volatile boolean isClear = false;//是否清空列表所有数据
    protected volatile List<D> items = new ArrayList<>();// list中当前最新页的数据
    protected volatile List<D> allItems = new ArrayList<>();// list中当前所有的数据

    private Y_PagePresenterConfig pageConfig;

    public Y_NetRecyclerPresenter(Context context, Y_I_View mView, Y_I_NetView mNetView, Y_I_NetRecyclerView mNetRecyclerView, Y_PagePresenterConfig pageConfig) {
        this.context = context;
        this.mView = mView;
        this.mNetView = mNetView;
        this.mNetRecyclerView = mNetRecyclerView;
        this.pageConfig = pageConfig;
        this.page = pageConfig.FirstPageIndex;
        this.pageSize = pageConfig.PageSize;
    }

    public Y_NetRecyclerPresenter(Context context, Y_I_View mView, Y_I_NetView mNetView, Y_I_NetRecyclerView mNetRecyclerView) {
        this(context, mView, mNetView, mNetRecyclerView, new Y_PagePresenterConfig.Builder().create());
    }

    public Y_NetAndHttpErrorCheckerSubscriber getDefaultSubscriber() {
        return new Y_NetAndHttpErrorCheckerSubscriber<DATA>(context) {
            @Override
            public void onCompleted() {
                mNetRecyclerView.hideRefreshInfication();
                mNetRecyclerView.completedLoadingData();
            }

            @Override
            public void onError(Throwable e) {
                mNetView.showLoadingError();
                mNetRecyclerView.hideRefreshInfication();
                mNetRecyclerView.completedLoadingData();
            }

            @Override
            public void onCorrectResult(ComRespInfo<DATA> dataComRespInfo) {
                ItemEntityList itemEntityList = mNetRecyclerView.getItemEntityList();
                boolean hasnext = getIsHasNextFromResponse(dataComRespInfo.getData());
                mNetRecyclerView.setHasNextPage(hasnext);
                items.clear();
                items = getListFromResponse(dataComRespInfo.getData());

                if (isClear) {
                    mNetRecyclerView.scrollToTop();
                    itemEntityList.clearItemDatas();
                    allItems.clear();
                    isClear = false;
                }
                if (itemEntityList.getItemCount() >= pageSize) {
                    itemEntityList.remove(itemEntityList.getItemCount() - 1);
                }
                itemEntityList.addItems(mNetRecyclerView.getItemLayoutId(), items);
                allItems.addAll(items);
                if (itemEntityList.getItemCount() >= pageSize) {
                    itemEntityList.addItem(mNetRecyclerView.getBottomViewLayoutId(), "bottomText");
                }
                if (itemEntityList.getItemCount() == 0) {
                    mNetView.showEmptyPage();
                }
                mNetRecyclerView.updateData();
                mNetRecyclerView.setIsFirstLoadData(false);
            }
        };
    }

    public List<D> getCurAllItems() {
        return allItems;
    }

    public void loadData(Observable<ComRespInfo<DATA>> observable, boolean canShowLoading, boolean canLoadCelable) {
        Y_NetAndHttpErrorCheckerSubscriber subscriber = getDefaultSubscriber();
        ObservableFactory.createNetObservable(context, observable, mView.getRxView())
                .compose(new EmitBeforeAndAfterTransformer<DATA>(mNetView, subscriber, canShowLoading, canLoadCelable))
                .subscribe(subscriber);
    }

    @Override
    public void onInitData() {
        mNetRecyclerView.startLoadingData();
        page = pageConfig.FirstPageIndex;
        isClear = true;
        if (pageConfig.isInitRefreshIndicationShow) {
            mNetRecyclerView.showRefreshIndication();
        }
        loadData(getRequestObservable(page, pageSize), pageConfig.isInitDialogShow, false);
    }

    @Override
    public void onLoadMore() {
        mNetRecyclerView.startLoadingData();
        page++;
        isClear = false;
        loadData(getRequestObservable(page, pageSize), pageConfig.isLoadMoreDialogShow, false);
    }

    @Override
    public void onLoadRefresh() {
        mNetRecyclerView.startLoadingData();
        page = pageConfig.FirstPageIndex;
        isClear = true;
        loadData(getRequestObservable(page, pageSize), pageConfig.isRefreshDialogShow, false);
    }

    @Override
    public void clearList() {
        page = pageConfig.FirstPageIndex;
        isClear = true;
        mNetRecyclerView.getItemEntityList().clearItemDatas();
        allItems.clear();
        mNetRecyclerView.updateData();
    }

    public Y_PagePresenterConfig getPageConfig() {
        return pageConfig;
    }
}








































