package net.zsygfddsd.qujing.base.fragment.net_refresh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsygfddsd.spacestation.base.fragment.network_refresh.Y_NetRefreshView;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import net.zsygfddsd.qujing.base.fragment.net.BaseNetFragment;

/**
 * Created by mac on 2016/12/26.
 */

public abstract class BaseNetRefreshFragment<T extends BaseNetRefreshContract.INetRefreshPresenter, DATA> extends BaseNetFragment<T> implements BaseNetRefreshContract.INetRefreshView<T, DATA> {

    private NetRefreshView netRefreshView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (netRefreshView == null) {
            netRefreshView = new NetRefreshView(ct);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return netRefreshView.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        netRefreshView.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onRefresh() {
        netRefreshView.onRefresh();
    }

    @Override
    public void showRefreshIndication() {
        netRefreshView.showRefreshIndication();
    }

    @Override
    public void hideRefreshInfication() {
        netRefreshView.hideRefreshInfication();
    }


    class NetRefreshView extends Y_NetRefreshView<DATA> {

        NetRefreshView(Context ct) {
            super(ct);
        }

        @Override
        public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return BaseNetRefreshFragment.this.initView(inflater, container, savedInstanceState);
        }

        @Override
        public void initData(Bundle savedInstanceState) {
            BaseNetRefreshFragment.this.initData(savedInstanceState);
        }

        @Override
        public void onBindViewData(ComRespInfo<DATA> dataComRespInfo) {
            BaseNetRefreshFragment.this.onBindViewData(dataComRespInfo);
        }

        @Override
        public void onRefresh() {
            mPresenter.onRefreshData();
        }
    }
}
