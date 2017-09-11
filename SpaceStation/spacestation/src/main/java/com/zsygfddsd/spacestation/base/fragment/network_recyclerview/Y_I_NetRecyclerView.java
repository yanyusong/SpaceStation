package com.zsygfddsd.spacestation.base.fragment.network_recyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

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
public interface Y_I_NetRecyclerView<D> extends SwipeRefreshLayout.OnRefreshListener {

    String ITEM_LAYOUT_ID = "itemLayoutId";
    String LAZY_LOAD = "lazyLoad";
    String ALWAYS_REFRESH_FOR_PER_VISIBLE = "alwaysRefreshForPerVisible";
    String IS_LOAD_DATA_FOR_FIRST_ENTER = "isLoadDataFirstEnter";

    void setHasNextPage(boolean hasNext);

    int getItemLayoutId();

    int getBottomViewLayoutId();

    RecyclerView.ItemDecoration getItemDecoration(Context ct);

    void setRefreshEnable(boolean enable);

    void setIsFirstLoadData(boolean isFirstLoadData);

    ItemEntityList getItemEntityList();

    //        List<D> getItemDatas();

    void updateData();
    //

    /**
     * 标志为正在加载数据了
     */
    void startLoadingData();

    /**
     * 是否正在加载数据了
     *
     * @return
     */
    boolean isLoadingData();

    /**
     * 结束正在加载数据
     */
    void completedLoadingData();

    void showRefreshIndication();

    void hideRefreshInfication();

    /**
     * 给Item布局的各个控件设置分配好的数据
     *
     * @param holder   item的holder，利用getChildView(eg:控件id)的方法得到该控件
     * @param itemData 封装好的分配给该item的数据，数据一般为Hashmap<K,V>或者Modle等类型
     * @param position 当前item的position
     */
    void bindChildViewsData(GeneralRecyclerViewHolder holder, D itemData, int position);

    void scrollToTop();

}
