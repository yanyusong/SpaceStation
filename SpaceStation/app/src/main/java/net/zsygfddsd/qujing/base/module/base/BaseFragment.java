package net.zsygfddsd.qujing.base.module.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zsygfddsd.spacestation.base.module.base.Y_BaseFragment;

/**
 * Created by mac on 2016/12/26.
 */

public abstract class BaseFragment<T extends BaseContract.IPresenter> extends Y_BaseFragment<T> implements BaseContract.IView<T> {

    public Context ct;
    public T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ct = getActivity();
    }


}
