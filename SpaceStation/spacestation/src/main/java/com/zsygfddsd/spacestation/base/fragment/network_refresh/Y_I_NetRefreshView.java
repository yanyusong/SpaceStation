package com.zsygfddsd.spacestation.base.fragment.network_refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

/**
 * Created by mac on 2016/12/29.
 */

public interface Y_I_NetRefreshView<DATA> extends SwipeRefreshLayout.OnRefreshListener {

    // onCreateView 中
    View viewInflate(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    // onViewCreated 中
    void viewInit(View view, @Nullable Bundle savedInstanceState);

    // onActivityCreated 中
    void dataInit(Bundle savedInstanceState);

    void onBindViewData(ComRespInfo<DATA> dataComRespInfo);

    void showRefreshIndication();

    void hideRefreshInfication();

}
