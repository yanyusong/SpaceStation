package net.zsygfddsd.qujing.modules.welviewpager;


import net.zsygfddsd.qujing.base.fragment.net_recyclerview.BaseNetRecyclerContract;

/**
 * Created by mac on 16/7/24.
 */
public class TabsContract {

    public interface View extends BaseNetRecyclerContract.INetRecyclerView<TabsContract.Presenter> {

    }


    public interface Presenter<DATA, D> extends BaseNetRecyclerContract.INetRecyclerPresenter<DATA, D> {

    }

}
