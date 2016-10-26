package net.zsygfddsd.qujing.modules.welfarelist;

import net.zsygfddsd.qujing.common.utils.ActivityScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 2016/10/26.
 */
@Module
public class WelfareListPresenterModule {

    private WelfareListContract.View mView;

    public WelfareListPresenterModule(WelfareListContract.View mView) {
        this.mView = mView;
    }

    @ActivityScoped
    @Provides
    WelfareListContract.View provideWelfareListContractView() {
        return mView;
    }

}
