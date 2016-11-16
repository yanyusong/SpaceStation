package com.zsygfddsd.spacestation.common;

import android.content.Context;
import android.widget.Toast;

import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

/**
 * Created by mac on 2016/11/16.
 */

public class DefaultErrorHandler implements ErrorHandler {

    private Toast toast;

    @Override
    public void handlerHttpError(Context context, ComRespInfo comRespInfo) {
        if (comRespInfo.getResultcode() == TokenOutDated) {
            if (toast == null) {
                toast = Toast.makeText(context, "token过期，请重新登录", Toast.LENGTH_SHORT);
            } else {
                toast.setText("token过期，请重新登录");
            }
            toast.show();
        }
    }

    @Override
    public void handlerNotHasNetwork(Context context) {
        if (toast == null) {
            toast = Toast.makeText(context, "网络未连接", Toast.LENGTH_SHORT);
        } else {
            toast.setText("网络未连接");
        }
        toast.show();
    }


}
