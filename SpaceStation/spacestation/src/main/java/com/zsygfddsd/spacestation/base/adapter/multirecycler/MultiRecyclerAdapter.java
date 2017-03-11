package com.zsygfddsd.spacestation.base.adapter.multirecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zsygfddsd.spacestation.base.adapter.GeneralRecyclerViewHolder;


/**
 * Created by mac on 16/1/6.
 */
public class MultiRecyclerAdapter extends RecyclerView.Adapter<GeneralRecyclerViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ItemEntityList itemList;


    public MultiRecyclerAdapter(Context ct, ItemEntityList itemList) {
        this.mContext = ct;
        this.itemList = itemList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public GeneralRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GeneralRecyclerViewHolder(mLayoutInflater.inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(GeneralRecyclerViewHolder holder, int position) {
        if (itemList.getOnBind(position) != null) {
            itemList.getOnBind(position).onBindChildViewData(holder, itemList.getItemData(position), position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.getItemLayoutId(position);
    }

    @Override
    public int getItemCount() {
        return itemList.getItemCount();
    }

}
