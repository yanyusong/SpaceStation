package com.zsygfddsd.spacestation.base.module.network_recyclerview;


import android.content.Context;

import com.zsygfddsd.spacestation.base.adapter.multirecycler.ItemEntityList;
import com.zsygfddsd.spacestation.base.module.network.Y_BaseNetPresenter;
import com.zsygfddsd.spacestation.common.helpers.http.ObservableFactory;
import com.zsygfddsd.spacestation.common.helpers.http.Subscriber.NetAndErrorCheckerSubscriber;
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
public abstract class Y_BasePagePresenter<DATA, D> extends Y_BaseNetPresenter implements Y_BasePageContract.IBaseRecyclerViewPresenter {

    protected Context context;
    private Y_BasePageContract.IBaseRecyclerView mView;

    protected int page = 1;
    protected int pageSize = 10;
    protected volatile boolean isClear = false;//是否清空列表所有数据
    protected volatile List<D> items = new ArrayList<>();// list中当前最新页的数据
    protected volatile List<D> allItems = new ArrayList<>();// list中当前所有的数据

    private Y_PagePresenterConfig pageConfig;

    public Y_BasePagePresenter(Context context, Y_BasePageContract.IBaseRecyclerView mView, Y_PagePresenterConfig pageConfig) {
        super(mView);
        this.context = context;
        this.mView = mView;
        this.pageConfig = pageConfig;
        this.page = pageConfig.FirstPageIndex;
        this.pageSize = pageConfig.PageSize;
    }

    public Y_BasePagePresenter(Context context, Y_BasePageContract.IBaseRecyclerView mView) {
        this(context, mView, new Y_PagePresenterConfig.Builder().create());
    }


    @Override
    public void start() {
        super.start();
    }

    public NetAndErrorCheckerSubscriber getDefaultSubscriber() {
        return new NetAndErrorCheckerSubscriber<DATA>(context, mView) {

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
                    ItemEntityList itemEntityList = mView.getItemEntityList();
                    boolean hasnext = getIsHasNextFromResponse(dataComRespInfo.getData());
                    mView.setHasNextPage(hasnext);
                    items.clear();
                    items = getListFromResponse(dataComRespInfo.getData());

                    if (isClear) {
                        itemEntityList.clearItemDatas();
                        allItems.clear();
                        isClear = false;
                    }
                    if (itemEntityList.getItemCount() >= pageSize) {
                        itemEntityList.remove(itemEntityList.getItemCount() - 1);
                    }
                    itemEntityList.addItems(mView.getItemLayoutId(), items);
                    allItems.addAll(items);
                    if (itemEntityList.getItemCount() >= pageSize) {
                        itemEntityList.addItem(mView.getBottomViewLayoutId(), "bottomText");
                    }
                    if (itemEntityList.getItemCount() == 0) {
                        mView.showEmptyPage();
                    }
                    mView.updateData();
                    mView.setIsFirstLoadData(false);

                } else {
                    mView.showLoadingError();
                }
            }
        };
    }

    public List<D> getCurAllItems() {
        return allItems;
    }

    public abstract boolean getIsHasNextFromResponse(DATA result);

    public abstract List<D> getListFromResponse(DATA result);

    public abstract Observable<ComRespInfo<DATA>> getRequestObservable(int page, int pageSize);

    public void loadData(Observable<ComRespInfo<DATA>> observable, boolean canShowLoading, boolean canLoadCelable) {
        NetAndErrorCheckerSubscriber subscriber = getDefaultSubscriber();
        ObservableFactory.createNetObservable(context, observable, mView.getRxView())
                .compose(new EmitBeforeAndAfterTransformer<DATA>(mView, subscriber, canShowLoading, canLoadCelable))
                .subscribe(subscriber);
    }

    @Override
    public void onInitData() {
        page = 1;
        isClear = true;
        if (pageConfig.isInitRefreshIndicationShow) {
            mView.showRefreshIndication();
        }
        loadData(getRequestObservable(page, pageSize), pageConfig.isInitDialogShow, false);
    }

    @Override
    public void onLoadMore() {
        page++;
        isClear = false;
        loadData(getRequestObservable(page, pageSize), pageConfig.isLoadMoreDialogShow, true);
    }

    @Override
    public void onLoadRefresh() {
        page = 1;
        isClear = true;
        loadData(getRequestObservable(page, pageSize), pageConfig.isRefreshDialogShow, true);
    }

}








































