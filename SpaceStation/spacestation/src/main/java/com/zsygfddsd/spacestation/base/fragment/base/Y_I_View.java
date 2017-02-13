package com.zsygfddsd.spacestation.base.fragment.base;

import com.zsygfddsd.spacestation.base.fragment.F_RxFragment;

/**
 * Created by mac on 2016/12/27.
 */

public interface Y_I_View<T extends Y_I_Presenter> {

    void setPresenter(T presenter);

    F_RxFragment getRxView();

    void showToast(String content);

}