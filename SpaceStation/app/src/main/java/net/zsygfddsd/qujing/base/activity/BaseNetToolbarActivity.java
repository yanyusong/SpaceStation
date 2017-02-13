package net.zsygfddsd.qujing.base.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.zsygfddsd.spacestation.base.activity.network_toolbar.Y_I_NetToolbarActivity;
import com.zsygfddsd.spacestation.base.activity.network_toolbar.Y_NetToolbarActivity;

/**
 * Created by mac on 2017/2/9.
 */

public class BaseNetToolbarActivity extends BaseNetActivity implements Y_I_NetToolbarActivity {

    private NetToolbarActivity netToolbarActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (netToolbarActivity == null) {
            netToolbarActivity = new NetToolbarActivity(this);
        }
        netToolbarActivity.onCreate(savedInstanceState);
    }

    @Override
    public void setToolBarTitle(String title) {
        netToolbarActivity.setToolBarTitle(title);
    }

    @Override
    public Toolbar getToolbar() {
        return netToolbarActivity.getToolbar();
    }

    @Override
    public TextView getToolbarTitleTextView() {
        return netToolbarActivity.getToolbarTitleTextView();
    }

    @Override
    public void addViewToContent(@LayoutRes int layoutId) {
        netToolbarActivity.addViewToContent(layoutId);
    }

    class NetToolbarActivity extends Y_NetToolbarActivity {

        NetToolbarActivity(AppCompatActivity appCompatActivity) {
            super(appCompatActivity);
        }
    }

}
