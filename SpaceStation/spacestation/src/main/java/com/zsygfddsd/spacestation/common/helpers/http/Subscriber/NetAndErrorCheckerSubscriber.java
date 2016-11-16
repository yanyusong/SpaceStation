package com.zsygfddsd.spacestation.common.helpers.http.Subscriber;

import android.content.Context;
import android.support.annotation.CallSuper;

import com.zsygfddsd.spacestation.common.ErrorHandler;
import com.zsygfddsd.spacestation.common.utils.DeviceUtils;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

import rx.Subscriber;

/**
 * Created by mac on 16/7/27.
 */
public abstract class NetAndErrorCheckerSubscriber<T> extends Subscriber<ComRespInfo<T>> {

    private Context context;
    private ErrorHandler errorHandler;

    public NetAndErrorCheckerSubscriber(Context context, ErrorHandler errorHandler) {
        this.context = context;
        this.errorHandler = errorHandler;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!new DeviceUtils(context).isHasNetWork()) {
            if (!isUnsubscribed()) {
                unsubscribe();
            }
            errorHandler.handlerNotHasNetwork(context);
        }
    }

    @CallSuper
    @Override
    public void onNext(ComRespInfo<T> tComRespInfo) {
        errorHandler.handlerHttpError(context, tComRespInfo);
    }


}
