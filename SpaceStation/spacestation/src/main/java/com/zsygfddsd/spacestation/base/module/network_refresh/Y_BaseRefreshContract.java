package com.zsygfddsd.spacestation.base.module.network_refresh;


import com.zsygfddsd.spacestation.base.module.base.Y_BaseContract;
import com.zsygfddsd.spacestation.base.module.network.Y_BaseNetContract;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

/**
 * Created by mac on 16/6/11.
 */
public class Y_BaseRefreshContract {

    public interface IBaseRefreshView<T extends IBaseRefreshPresenter, DATA> extends Y_BaseNetContract.IBaseNetView<T> {

        void onBindViewData(ComRespInfo<DATA> dataComRespInfo);

        void showRefreshIndication();

        void hideRefreshInfication();

    }

    public interface IBaseRefreshPresenter extends Y_BaseContract.IBasePresenter {

        void onRefreshData();

    }

}
