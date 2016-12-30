package net.zsygfddsd.qujing.base.module.net_recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsygfddsd.spacestation.base.adapter.GeneralRecyclerViewHolder;
import com.zsygfddsd.spacestation.base.adapter.multirecycler.ItemEntityList;
import com.zsygfddsd.spacestation.base.module.network_recyclerview.Y_NetRecyclerView;

import net.zsygfddsd.qujing.base.module.net.BaseNetFragment;

/**
 * Created by mac on 2016/12/26.
 */

public abstract class BaseNetRecyclerFragment<T extends BaseNetRecyclerContract.INetRecyclerPresenter> extends BaseNetFragment<T> implements BaseNetRecyclerContract.INetRecyclerView<T> {

    private NetRecyclerView netRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (netRecyclerView == null) {
            netRecyclerView = new NetRecyclerView(ct, this);
        }
        netRecyclerView.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return netRecyclerView.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        netRecyclerView.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        netRecyclerView.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        netRecyclerView.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        netRecyclerView.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void setHasNextPage(boolean hasNext) {
        netRecyclerView.setHasNextPage(hasNext);
    }

    @Override
    public int getItemLayoutId() {
        return netRecyclerView.getItemLayoutId();
    }

    @Override
    public int getBottomViewLayoutId() {
        return netRecyclerView.getBottomViewLayoutId();
    }

    @Override
    public void setRefreshEnable(boolean enable) {
        netRecyclerView.setRefreshEnable(enable);
    }

    @Override
    public void setIsFirstLoadData(boolean isFirstLoadData) {
        netRecyclerView.setIsFirstLoadData(isFirstLoadData);
    }

    @Override
    public ItemEntityList getItemEntityList() {
        return netRecyclerView.getItemEntityList();
    }

    @Override
    public void updateData() {
        netRecyclerView.updateData();
    }

    @Override
    public void showRefreshIndication() {
        netRecyclerView.showRefreshIndication();
    }

    @Override
    public void hideRefreshInfication() {
        netRecyclerView.hideRefreshInfication();
    }

    @Override
    public void onRefresh() {
        netRecyclerView.onRefresh();
    }


    class NetRecyclerView extends Y_NetRecyclerView {

        NetRecyclerView(Context ct, Fragment fragment) {
            super(ct, fragment);
        }

        @Override
        public void bindChildViewsData(GeneralRecyclerViewHolder holder, Object itemData, int position) {
            BaseNetRecyclerFragment.this.bindChildViewsData(holder, itemData, position);
        }

        @Override
        public void loadFirstPage() {
            mPresenter.onLoadRefresh();
        }

        @Override
        public void loadNextPage() {
            mPresenter.onLoadMore();
        }
    }

}
