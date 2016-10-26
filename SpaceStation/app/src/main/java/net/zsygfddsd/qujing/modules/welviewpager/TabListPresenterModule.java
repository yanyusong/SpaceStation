package net.zsygfddsd.qujing.modules.welviewpager;

import android.content.Context;

import net.zsygfddsd.qujing.R;
import net.zsygfddsd.qujing.common.utils.ActivityScoped;
import net.zsygfddsd.qujing.data.DataSource;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 2016/10/26.
 */
@Module
public class TabListPresenterModule {

    @Named("page0")
    @ActivityScoped
    @Provides
    TabListPresenter provideTabListPresenter0(Context context, DataSource.WelfareDataSource mRepository) {
        return new TabListPresenter(context, TabListFragment.newInstance(R.layout.item_welfare), mRepository);
    }

    @Named("page1")
    @ActivityScoped
    @Provides
    TabListPresenter provideTabListPresenter1(Context context, DataSource.WelfareDataSource mRepository) {
        return new TabListPresenter(context, TabListFragment.newInstance(R.layout.item_welfare), mRepository);
    }

    @Named("page2")
    @ActivityScoped
    @Provides
    TabListPresenter provideTabListPresenter2(Context context, DataSource.WelfareDataSource mRepository) {
        return new TabListPresenter(context, TabListFragment.newInstance(R.layout.item_welfare), mRepository);
    }

    @Named("page3")
    @ActivityScoped
    @Provides
    TabListPresenter provideTabListPresenter3(Context context, DataSource.WelfareDataSource mRepository) {
        return new TabListPresenter(context, TabListFragment.newInstance(R.layout.item_welfare), mRepository);
    }

    @Named("page4")
    @ActivityScoped
    @Provides
    TabListPresenter provideTabListPresenter4(Context context, DataSource.WelfareDataSource mRepository) {
        return new TabListPresenter(context, TabListFragment.newInstance(R.layout.item_welfare), mRepository);
    }

    @Named("page5")
    @ActivityScoped
    @Provides
    TabListPresenter provideTabListPresenter5(Context context, DataSource.WelfareDataSource mRepository) {
        return new TabListPresenter(context, TabListFragment.newInstance(R.layout.item_welfare), mRepository);
    }

}
