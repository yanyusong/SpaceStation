package com.zsygfddsd.spacestation.base.module.base;


import android.content.Context;

/**
 * Created by mac on 16/6/13.
 */
public class Y_BasePresenter implements Y_I_Presenter {

    private Context context;

    private Y_I_View mView;

    public Y_BasePresenter(Context context, Y_I_View mView) {
        this.context = context;
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }
}
