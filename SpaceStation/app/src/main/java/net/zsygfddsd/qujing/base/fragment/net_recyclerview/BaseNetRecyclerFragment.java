package net.zsygfddsd.qujing.base.fragment.net_recyclerview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsygfddsd.spacestation.base.adapter.GeneralRecyclerViewHolder;
import com.zsygfddsd.spacestation.base.adapter.multirecycler.ItemEntityList;
import com.zsygfddsd.spacestation.base.adapter.multirecycler.MultiRecyclerAdapter;
import com.zsygfddsd.spacestation.base.fragment.network_recyclerview.Y_NetRecyclerView;

import net.zsygfddsd.qujing.base.fragment.net.BaseNetFragment;

/**
 * Created by mac on 2016/12/26.
 */

public abstract class BaseNetRecyclerFragment<T extends BaseNetRecyclerContract.INetRecyclerPresenter> extends BaseNetFragment<T> implements BaseNetRecyclerContract.INetRecyclerView<T> {

    private NetRecyclerView netRecyclerView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (netRecyclerView == null) {
            netRecyclerView = new NetRecyclerView(ct, this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netRecyclerView.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return netRecyclerView.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        if (netRecyclerView != null) {
            netRecyclerView.setUserVisibleHint(isVisibleToUser);
        }
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

    @Override
    public void scrollToTop() {
        netRecyclerView.scrollToTop();
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration(Context ct) {
        //返回null，使用默认分割线
        return null;
    }

    @Override
    public void startLoadingData() {
        netRecyclerView.startLoadingData();
    }

    @Override
    public boolean isLoadingData() {
        return netRecyclerView.isLoadingData();
    }

    @Override
    public void completedLoadingData() {
        netRecyclerView.completedLoadingData();
    }

    public MultiRecyclerAdapter getAdapter() {
        return netRecyclerView.getAdapter();
    }

    class NetRecyclerView extends Y_NetRecyclerView {

        NetRecyclerView(Context ct, Fragment fragment) {
            super(ct, fragment);
        }

        @Override
        public RecyclerView.ItemDecoration getItemDecoration(Context ct) {
            return BaseNetRecyclerFragment.this.getItemDecoration(ct);
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

        public MultiRecyclerAdapter getAdapter() {
            return adapter;
        }


    }

}
