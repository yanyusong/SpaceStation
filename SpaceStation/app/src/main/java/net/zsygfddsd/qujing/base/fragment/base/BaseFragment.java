package net.zsygfddsd.qujing.base.fragment.base;

import android.app.Activity;
import android.content.Context;

import com.zsygfddsd.spacestation.base.fragment.base.Y_Fragment;

/**
 * Created by mac on 2016/12/26.
 */

public abstract class BaseFragment<T extends BaseContract.IPresenter> extends Y_Fragment<T> implements BaseContract.IView<T> {

    public Context ct;
    public T mPresenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ct = activity;
    }

}
