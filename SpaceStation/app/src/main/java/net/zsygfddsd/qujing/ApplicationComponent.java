package net.zsygfddsd.qujing;

import android.content.Context;

import net.zsygfddsd.qujing.common.utils.ApplicationScoped;
import net.zsygfddsd.qujing.modules.common.ContextModule;

import dagger.Component;

/**
 * Created by mac on 2016/10/14.
 */
@ApplicationScoped
@Component(modules = ContextModule.class)
public interface ApplicationComponent {

    void inject(QJapplication application);

    Context getApplicationContext();
}
