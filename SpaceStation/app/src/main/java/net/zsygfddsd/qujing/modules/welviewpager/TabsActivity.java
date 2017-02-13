package net.zsygfddsd.qujing.modules.welviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import net.zsygfddsd.qujing.R;
import net.zsygfddsd.qujing.base.activity.BaseNetToolbarActivity;
import net.zsygfddsd.qujing.modules.common.ContextModule;
import net.zsygfddsd.qujing.modules.common.RepositoryModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac on 2016/10/24.
 */

public class TabsActivity extends BaseNetToolbarActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    android.support.design.widget.TabLayout tabLayout;
    @BindView(R.id.smooth_app_bar_layout)
    android.support.design.widget.AppBarLayout smoothAppBarLayout;
    //    @Named("page0")
    //    @Inject
    //    WelfareListContract.View welfareListFragment0;
    //    @Named("page1")
    //    @Inject
    //    WelfareListContract.View welfareListFragment1;
    //    @Named("page2")
    //    @Inject
    //    WelfareListContract.View welfareListFragment2;
    //    @Named("page3")
    //    @Inject
    //    WelfareListContract.View welfareListFragment3;
    //    @Named("page4")
    //    @Inject
    //    WelfareListContract.View welfareListFragment4;

    @Named("page0")
    @Inject
    TabListPresenter welfareListPresenter0;
    @Named("page1")
    @Inject
    TabListPresenter welfareListPresenter1;
    @Named("page2")
    @Inject
    TabListPresenter welfareListPresenter2;
    @Named("page3")
    @Inject
    TabListPresenter welfareListPresenter3;
    @Named("page4")
    @Inject
    TabListPresenter welfareListPresenter4;
    @Named("page5")
    @Inject
    TabListPresenter welfareListPresenter5;

    //    private TabsActivityComponent daggerTabsActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        ButterKnife.bind(this);

        DaggerTabsActivityComponent.builder()
                .contextModule(new ContextModule(this))
                .repositoryModule(new RepositoryModule())
                .tabListPresenterModule(new TabListPresenterModule())
                .build()
                .inject(this);
        initView();
    }

    private void initView() {

        toolbar.setTitle("发现");
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            titles.add("tab" + i);
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }

        List<Fragment> fragments = new ArrayList<>();

        fragments.add((Fragment) welfareListPresenter0.getView());
        fragments.add((Fragment) welfareListPresenter1.getView());
        fragments.add((Fragment) welfareListPresenter2.getView());
        fragments.add((Fragment) welfareListPresenter3.getView());
        fragments.add((Fragment) welfareListPresenter4.getView());
        fragments.add((Fragment) welfareListPresenter5.getView());
        viewPager.setAdapter(new TabPagesAdapter(getSupportFragmentManager(), fragments, titles.toArray(new String[titles.size()])));
        viewPager.setCurrentItem(0);
        //        viewPager.setOffscreenPageLimit(titles.size());
        tabLayout.setupWithViewPager(viewPager);
    }

}
