package net.zsygfddsd.qujing.base.module.net_refresh;

import com.zsygfddsd.spacestation.base.module.network_refresh.Y_I_NetRefreshPresenter;

import net.zsygfddsd.qujing.base.module.net.BaseNetFragment;

/**
 * Created by mac on 2016/12/26.
 */

public abstract class BaseNetRefreshFragment<T extends Y_I_NetRefreshPresenter, DATA> extends BaseNetFragment<T> implements BaseNetRefreshContract.INetRefreshView<T,DATA> {


}
