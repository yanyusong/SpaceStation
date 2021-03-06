package net.zsygfddsd.qujing.base.fragment.net;

import com.zsygfddsd.spacestation.base.fragment.network.Y_I_NetPresenter;
import com.zsygfddsd.spacestation.base.fragment.network.Y_I_NetView;

import net.zsygfddsd.qujing.base.fragment.base.BaseContract;

/**
 * Created by mac on 2016/12/28.
 */

public class BaseNetContract {


    public interface INetView<T extends INetPresenter> extends BaseContract.IView<T>, Y_I_NetView {

    }

    public interface INetPresenter extends BaseContract.IPresenter, Y_I_NetPresenter {

    }

}
