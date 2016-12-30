package net.zsygfddsd.qujing.base.module.base;

import android.content.Context;

import com.zsygfddsd.spacestation.base.module.base.Y_BasePresenter;

/**
 * Created by mac on 2016/12/28.
 */

public class BasePresenter extends Y_BasePresenter implements BaseContract.IPresenter {

    public BasePresenter(Context context, BaseContract.IView mView) {
        super(context, mView);
    }
}
