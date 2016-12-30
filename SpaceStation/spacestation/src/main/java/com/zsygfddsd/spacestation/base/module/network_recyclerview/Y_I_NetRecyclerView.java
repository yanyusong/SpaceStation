package com.zsygfddsd.spacestation.base.module.network_recyclerview;

import android.support.v4.widget.SwipeRefreshLayout;

import com.zsygfddsd.spacestation.base.adapter.GeneralRecyclerViewHolder;
import com.zsygfddsd.spacestation.base.adapter.multirecycler.ItemEntityList;

/**
 * 需要调用的Fragment生命周期中的方法
 * setUserVisibleHint
 * onCreate
 * onCreateView
 * onViewCreated
 * onActivityCreated
 * onDestroyView
 */
public interface Y_I_NetRecyclerView extends SwipeRefreshLayout.OnRefreshListener {

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

    /**
     * 给Item布局的各个控件设置分配好的数据
     *
     * @param holder   item的holder，利用getChildView(eg:控件id)的方法得到该控件
     * @param itemData 封装好的分配给该item的数据，数据一般为Hashmap<K,V>或者Modle等类型
     * @param position 当前item的position
     */
    void bindChildViewsData(GeneralRecyclerViewHolder holder, Object itemData, int position);

}
