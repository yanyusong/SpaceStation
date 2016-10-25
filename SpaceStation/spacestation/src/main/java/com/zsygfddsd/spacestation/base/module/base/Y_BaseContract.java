package com.zsygfddsd.spacestation.base.module.base;


import com.zsygfddsd.spacestation.base.fragment.F_RxFragment;

/**
 * Created by mac on 16/6/11.
 */
public class Y_BaseContract {

    public interface IBaseView<T extends IBasePresenter> {

        void setPresenter(T presenter);

        F_RxFragment getRxView();

        void showToast(String content);

    }

    public interface IBasePresenter {

        void start();

        void destroy();

    }

}
