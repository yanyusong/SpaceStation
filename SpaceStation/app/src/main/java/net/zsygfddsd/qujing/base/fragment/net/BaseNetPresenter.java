package net.zsygfddsd.qujing.base.fragment.net;

import android.content.Context;

import com.zsygfddsd.spacestation.base.fragment.network.Y_I_NetView;
import com.zsygfddsd.spacestation.base.fragment.network.Y_NetPresenter;

import net.zsygfddsd.qujing.base.fragment.base.BasePresenter;

/**
 * Created by mac on 2016/12/28.
 */

public class BaseNetPresenter extends BasePresenter implements BaseNetContract.INetPresenter {

    private NetPresenter netPresenter;

    public BaseNetPresenter(Context context, BaseNetContract.INetView mView) {
        super(context, mView);
        if (netPresenter == null) {
            netPresenter = new NetPresenter(context, mView);
        }
    }


    private class NetPresenter extends Y_NetPresenter {

        NetPresenter(Context context, Y_I_NetView mView) {
            super(context, mView);
        }
    }
}
