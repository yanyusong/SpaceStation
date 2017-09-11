package net.zsygfddsd.qujing.modules.welfarelist;


import net.zsygfddsd.qujing.base.fragment.net_recyclerview.BaseNetRecyclerContract;

/**
 * Created by mac on 16/7/24.
 */
public class WelfareListContract {

    public interface View<D> extends BaseNetRecyclerContract.INetRecyclerView<Presenter, D> {

    }


    public interface Presenter<DATA, D> extends BaseNetRecyclerContract.INetRecyclerPresenter<DATA, D> {


    }

}
