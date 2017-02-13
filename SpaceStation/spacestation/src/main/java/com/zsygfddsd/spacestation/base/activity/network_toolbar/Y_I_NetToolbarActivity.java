package com.zsygfddsd.spacestation.base.activity.network_toolbar;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by mac on 2017/2/9.
 */

public interface Y_I_NetToolbarActivity {

    void setToolBarTitle(String title);

    Toolbar getToolbar();

    TextView getToolbarTitleTextView();

    void addViewToContent(@LayoutRes int layoutId);
}
