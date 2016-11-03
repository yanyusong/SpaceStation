package com.zsygfddsd.spacestation.base.module.network_recyclerview;


import com.zsygfddsd.spacestation.base.adapter.multirecycler.ItemEntityList;
import com.zsygfddsd.spacestation.base.module.base.Y_BaseContract;
import com.zsygfddsd.spacestation.base.module.network.Y_BaseNetContract;

/**
 * Created by mac on 16/6/11.
 */
public class Y_BasePageContract {

    public interface IBaseRecyclerView<T extends IBaseRecyclerViewPresenter> extends Y_BaseNetContract.IBaseNetView<T> {

        void setHasNextPage(boolean hasNext);

        int getItemLayoutId();

        int getBottomViewLayoutId();

        void setRefreshEnable(boolean enable);

        void setIsFirstLoadData(boolean isFirstLoadData);

        ItemEntityList getItemEntityList();

        //        List<D> getItemDatas();

        void updateData();
        //
        //        void updateData(List<D> itemdatas);
        //
        //        void updateData(int position);

        void showRefreshIndication();

        void hideRefreshInfication();

    }

    public interface IBaseRecyclerViewPresenter extends Y_BaseContract.IBasePresenter {

        void onInitData();

        void onLoadMore();

        void onLoadRefresh();

    }

}
