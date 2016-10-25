package com.zsygfddsd.spacestation.base.module.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.zsygfddsd.spacestation.base.fragment.F_RxFragment;


/**
 * Created by mac on 16/3/1.
 */
public class Y_BaseFragment<T extends Y_BaseContract.IBasePresenter> extends F_RxFragment implements Y_BaseContract.IBaseView<T> {

    public Context ct;
    private Y_BaseContract.IBasePresenter mPresenter;
    private Toast toast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ct = getActivity();
    }

    @Override
    public void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(ct, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    @CallSuper
    @Override
    public void setPresenter(T presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public F_RxFragment getRxView() {
        return this;
    }

}
