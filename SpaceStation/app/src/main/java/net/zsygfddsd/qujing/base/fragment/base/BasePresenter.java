package net.zsygfddsd.qujing.base.fragment.base;

import android.content.Context;

import com.zsygfddsd.spacestation.base.fragment.base.Y_Presenter;

/**
 * Created by mac on 2016/12/28.
 */

public class BasePresenter extends Y_Presenter implements BaseContract.IPresenter {

    public BasePresenter(Context context, BaseContract.IView mView) {
        super(context, mView);
    }
}
