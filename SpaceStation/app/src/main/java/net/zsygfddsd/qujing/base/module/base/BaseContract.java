package net.zsygfddsd.qujing.base.module.base;

import com.zsygfddsd.spacestation.base.module.base.Y_I_Presenter;
import com.zsygfddsd.spacestation.base.module.base.Y_I_View;

/**
 * Created by mac on 2016/12/28.
 */

public class BaseContract {

    public interface IView<T extends IPresenter> extends Y_I_View<T> {

    }

    public interface IPresenter extends Y_I_Presenter {

    }

}
