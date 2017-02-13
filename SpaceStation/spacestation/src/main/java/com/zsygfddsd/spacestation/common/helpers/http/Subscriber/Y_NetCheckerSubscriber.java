package com.zsygfddsd.spacestation.common.helpers.http.Subscriber;

import android.content.Context;

import com.zsygfddsd.spacestation.base.Y_I_Net;
import com.zsygfddsd.spacestation.common.utils.DeviceUtils;

import rx.Subscriber;

/**
 * Created by mac on 16/7/27.
 */
public abstract class Y_NetCheckerSubscriber<T> extends Subscriber<T> {

    private Context context;

    public Y_NetCheckerSubscriber(Context context) {
        this.context = context;
        if (!(context instanceof Y_I_Net)) {
            throw new IllegalArgumentException("the context must implements Y_I_Net!");
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (!new DeviceUtils(context).isHasNetWork()) {
            if (!isUnsubscribed()) {
                unsubscribe();
            }
            ((Y_I_Net) context).showNotHasNetwork();
        }
    }

}
