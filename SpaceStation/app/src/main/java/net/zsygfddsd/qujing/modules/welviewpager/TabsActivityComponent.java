package net.zsygfddsd.qujing.modules.welviewpager;


import net.zsygfddsd.qujing.common.utils.ActivityScoped;
import net.zsygfddsd.qujing.modules.common.ContextModule;
import net.zsygfddsd.qujing.modules.common.RepositoryModule;

import dagger.Component;

/**
 * Created by mac on 2016/10/11.
 */
@ActivityScoped
@Component(modules = {RepositoryModule.class, TabListPresenterModule.class, ContextModule.class})
public interface TabsActivityComponent {

    void inject(TabsActivity activity);

}
