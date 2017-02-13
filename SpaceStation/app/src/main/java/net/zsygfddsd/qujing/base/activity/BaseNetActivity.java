package net.zsygfddsd.qujing.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zsygfddsd.spacestation.base.activity.network.Y_I_NetActivity;
import com.zsygfddsd.spacestation.base.activity.network.Y_NetActivity;
import com.zsygfddsd.spacestation.common.Y_HttpResultType;
import com.zsygfddsd.spacestation.data.bean.ComRespInfo;

/**
 * Created by mac on 2017/2/9.
 */

public class BaseNetActivity extends BaseActivity implements Y_I_NetActivity {

    private NetActivity netActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (netActivity == null) {
            netActivity = new NetActivity(context);
        }
    }

    @Override
    public void showLoading(boolean cancelable, ILoadingCancelListener listener) {
        netActivity.showLoading(cancelable, listener);
    }

    @Override
    public void hideLoading() {
        netActivity.hideLoading();
    }

    @Override
    public void showLoadingError() {
        showToast("系统繁忙，请稍后重试！");
    }

    @Override
    public void showEmptyPage() {
        showToast("暂无数据！");
    }

    @Override
    public void showNotHasNetwork() {
        showToast("网络已断开，请检查网络连！");
    }

    @Override
    public boolean checkAndHandleHttpError(ComRespInfo comRespInfo) {
        if (!comRespInfo.getResult()) {
            // TODO: 2017/2/9 根据错误码添加代码，比如弹框跳转，或者其他处理，
            // TODO: 2017/2/9 但是需要注意的情况是，可能一个页面可能会同时调用多次这个处理方法，警惕是否需要控制只执行一次
            switch (comRespInfo.getResultcode()) {
                case Y_HttpResultType.TokenOutDated:
                    // TODO: 2017/2/10 token 失效，登陆过期
                    return false;
                case Y_HttpResultType.UncaughtException:
                    // TODO: 2017/2/10
                    return false;
                default:
                    return false;
            }
        }
        return true;
    }

    class NetActivity extends Y_NetActivity {


        NetActivity(Context context) {
            super(context);
        }


        @Override
        public void showLoadingError() {
            BaseNetActivity.this.showLoadingError();
        }

        @Override
        public void showEmptyPage() {
            BaseNetActivity.this.showEmptyPage();
        }

        @Override
        public void showNotHasNetwork() {
            BaseNetActivity.this.showNotHasNetwork();
        }

        @Override
        public boolean checkAndHandleHttpError(ComRespInfo comRespInfo) {
            return BaseNetActivity.this.checkAndHandleHttpError(comRespInfo);
        }
    }


}
