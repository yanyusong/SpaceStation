package net.zsygfddsd.qujing.base.module.net_refresh;

import com.zsygfddsd.spacestation.base.module.network_refresh.Y_I_NetRefreshPresenter;
import com.zsygfddsd.spacestation.base.module.network_refresh.Y_I_NetRefreshView;

import net.zsygfddsd.qujing.base.module.net.BaseNetContract;

/**
 * Created by mac on 2016/12/28.
 */

public class BaseNetRefreshContract {

    public interface INetRefreshView<T extends INetRefreshPresenter, DATA> extends BaseNetContract.INetView<T>, Y_I_NetRefreshView<DATA> {

    }

    public interface INetRefreshPresenter extends BaseNetContract.INetPresenter, Y_I_NetRefreshPresenter {

    }
}
