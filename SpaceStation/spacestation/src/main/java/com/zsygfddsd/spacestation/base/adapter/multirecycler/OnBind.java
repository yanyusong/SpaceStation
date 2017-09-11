package com.zsygfddsd.spacestation.base.adapter.multirecycler;


import com.zsygfddsd.spacestation.base.adapter.GeneralRecyclerViewHolder;

/**
 * Created by mac on 16/6/18.
 */
public interface OnBind<T extends Object> {

    /**
     * @param holder
     * @param position
     */
    void onBindChildViewData(GeneralRecyclerViewHolder holder, T itemData, int position);
}
