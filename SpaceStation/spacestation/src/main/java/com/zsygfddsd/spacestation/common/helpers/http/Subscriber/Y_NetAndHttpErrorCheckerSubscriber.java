package com.zsygfddsd.spacestation.common.helpers.http.Subscriber;

import android.content.Context;

import com.zsygfddsd.spacestation.base.Y_I_Net;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

/**
 * Created by mac on 16/7/27.
 */
public abstract class Y_NetAndHttpErrorCheckerSubscriber<T> extends Y_NetCheckerSubscriber<ComRespInfo<T>> {

    private Context context;

    public Y_NetAndHttpErrorCheckerSubscriber(Context context) {
        super(context);
        this.context = context;
        if (!(context instanceof Y_I_Net)) {
            throw new IllegalArgumentException("the context must implements Y_I_Net!");
        }
    }

    @Override
    public void onNext(ComRespInfo<T> tComRespInfo) {
        if (((Y_I_Net) context).checkAndHandleHttpError(tComRespInfo)) {
            onCorrectResult(tComRespInfo);
        }
    }

    public abstract void onCorrectResult(ComRespInfo<T> tComRespInfo);



}
