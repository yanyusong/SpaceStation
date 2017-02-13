package com.zsygfddsd.spacestation.base.fragment.network_refresh;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zsygfddsd.spacestation.R;


/**
 * Created by mac on 15/12/19.
 * T: 是IBaseRecyclerViewPresenter
 * D: 是item的bean
 */
public abstract class Y_NetRefreshView<DATA> implements Y_I_NetRefreshView<DATA> {

    protected SwipeRefreshLayout refreshView;

    private FrameLayout refreshContentView;

    private Context ct;

    public Y_NetRefreshView(Context ct) {
        this.ct = ct;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.y_frag_com_refresh, null);
        refreshView = (SwipeRefreshLayout) view.findViewById(R.id.com_refreshLayout);
        refreshView.setOnRefreshListener(this);
        refreshContentView = (FrameLayout) view.findViewById(R.id.frame_refresh_content);
        refreshContentView.addView(initView(inflater, container, savedInstanceState));
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData(savedInstanceState);
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
















