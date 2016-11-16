package com.zsygfddsd.spacestation.common;

import android.content.Context;

import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

/**
 * Created by mac on 2016/11/10.
 */

public interface ErrorHandler {

    int Normal = 0;

    int UncaughtException = -1;

    int TokenOutDated = 1;

    void handlerHttpError(Context context, ComRespInfo comRespInfo);

    void handlerNotHasNetwork(Context context);


}
