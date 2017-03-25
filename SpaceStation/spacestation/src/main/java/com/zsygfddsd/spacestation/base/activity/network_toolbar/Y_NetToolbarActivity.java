package com.zsygfddsd.spacestation.base.activity.network_toolbar;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zsygfddsd.spacestation.R;


/**
 * 使用原生toolbar的兼容
 * Created by Clock on 2016/2/3.
 */
public abstract class Y_NetToolbarActivity implements Y_I_NetToolbarActivity {

    protected Toolbar mToolbar;
    protected TextView mToolbarTitle;
    protected FrameLayout mContentView;
    protected AppCompatActivity appCompatActivity;

    public Y_NetToolbarActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void onCreate(Bundle savedInstanceState) {
        appCompatActivity.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        appCompatActivity.setContentView(R.layout.y_activity_main);
        mToolbar = (Toolbar) appCompatActivity.findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) appCompatActivity.findViewById(R.id.toolbar_title);
        mContentView = (FrameLayout) appCompatActivity.findViewById(R.id.contentView);
        appCompatActivity.setSupportActionBar(mToolbar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitleTextColor(0xffffffff);//白色
    }

    public void setToolBarTitle(String title) {
        mToolbarTitle.setText(title);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public TextView getToolbarTitleTextView() {
        return mToolbarTitle;
    }

    public void addViewToContent(@LayoutRes int layoutId) {
        mContentView.addView(View.inflate(appCompatActivity, layoutId, null));
    }
}
