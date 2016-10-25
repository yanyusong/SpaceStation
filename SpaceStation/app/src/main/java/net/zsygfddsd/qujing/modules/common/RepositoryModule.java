package net.zsygfddsd.qujing.modules.common;

import com.zsygfddsd.spacestation.common.helpers.dagger.ActivityScoped;

import net.zsygfddsd.qujing.data.DataSource;
import net.zsygfddsd.qujing.data.repository.WelfareRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 2016/10/14.
 */
@Module
public class RepositoryModule {
    @ActivityScoped
    @Provides
    DataSource.WelfareDataSource provideWelfareRepository() {
        return new WelfareRepository();
    }


}
