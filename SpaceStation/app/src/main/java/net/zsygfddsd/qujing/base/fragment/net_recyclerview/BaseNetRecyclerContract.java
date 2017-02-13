package net.zsygfddsd.qujing.base.fragment.net_recyclerview;

import com.zsygfddsd.spacestation.base.fragment.network_recyclerview.Y_I_NetRecyclerPresenter;
import com.zsygfddsd.spacestation.base.fragment.network_recyclerview.Y_I_NetRecyclerView;

import net.zsygfddsd.qujing.base.fragment.net.BaseNetContract;

/**
 * Created by mac on 2016/12/28.
 */

public class BaseNetRecyclerContract {

    public interface INetRecyclerView<T extends INetRecyclerPresenter> extends BaseNetContract.INetView<T>, Y_I_NetRecyclerView {

    }

    public interface INetRecyclerPresenter<DATA, D> extends BaseNetContract.INetPresenter, Y_I_NetRecyclerPresenter<DATA, D> {

    }
}
