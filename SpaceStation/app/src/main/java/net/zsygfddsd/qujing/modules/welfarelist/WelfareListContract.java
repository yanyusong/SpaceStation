package net.zsygfddsd.qujing.modules.welfarelist;


import net.zsygfddsd.qujing.base.fragment.net_recyclerview.BaseNetRecyclerContract;

/**
 * Created by mac on 16/7/24.
 */
public class WelfareListContract {

    public interface View extends BaseNetRecyclerContract.INetRecyclerView<Presenter> {

    }


    public interface Presenter<DATA, D> extends BaseNetRecyclerContract.INetRecyclerPresenter<DATA, D> {


    }

}
