package com.zsygfddsd.spacestation.base.fragment.network_refresh;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

/**
 * Created by mac on 2016/12/29.
 */

public interface Y_I_NetRefreshView<DATA> extends SwipeRefreshLayout.OnRefreshListener {

    View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void initData(Bundle savedInstanceState);

    void onBindViewData(ComRespInfo<DATA> dataComRespInfo);

    void showRefreshIndication();

    void hideRefreshInfication();

}
