package com.zsygfddsd.spacestation.base.module.network_refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zsygfddsd.spacestation.R;
import com.zsygfddsd.spacestation.base.module.network.Y_BaseNetFragment;


/**
 * Created by mac on 15/12/19.
 * T: 是IBaseRecyclerViewPresenter
 * D: 是item的bean
 */
public abstract class Y_BaseRefreshFragment<T extends Y_BaseRefreshContract.IBaseRefreshPresenter, DATA> extends Y_BaseNetFragment<T> implements Y_BaseRefreshContract.IBaseRefreshView<T, DATA>, SwipeRefreshLayout.OnRefreshListener {

    protected SwipeRefreshLayout refreshView;

    private Y_BaseRefreshContract.IBaseRefreshPresenter mPresenter;
    private FrameLayout refreshContentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.y_frag_com_refresh, null);
        refreshView = (SwipeRefreshLayout) view.findViewById(R.id.com_refreshLayout);
        refreshView.setOnRefreshListener(this);
        refreshContentView = (FrameLayout) view.findViewById(R.id.frame_refresh_content);
        refreshContentView.addView(initView(inflater, container, savedInstanceState));
        return view;
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    protected abstract void initData(Bundle savedInstanceState);


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefreshData();
    }

    @Override
    public void showRefreshIndication() {
        if (!refreshView.isRefreshing()) {
            refreshView.setRefreshing(true);
        }
    }

    @Override
    public void hideRefreshInfication() {
        if (refreshView.isRefreshing()) {
            refreshView.setRefreshing(false);
        }
    }


}
















