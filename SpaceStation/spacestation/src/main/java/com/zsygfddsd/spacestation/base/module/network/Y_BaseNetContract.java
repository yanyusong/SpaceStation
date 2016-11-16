package com.zsygfddsd.spacestation.base.module.network;


import com.zsygfddsd.spacestation.base.module.base.Y_BaseContract;

/**
 * Created by mac on 16/6/11.
 */
public class Y_BaseNetContract {

    public interface IBaseNetView<T extends Y_BaseContract.IBasePresenter> extends Y_BaseContract.IBaseView<T>, INetView {


    }

    public interface INetView {

        interface ILoadingCancelListener {
            void onLoadCancelListener();
        }

        void showLoading(boolean cancelable, ILoadingCancelListener listener);

        void hideLoading();

        void showLoadingError();

        void showEmptyPage();

    }

}
