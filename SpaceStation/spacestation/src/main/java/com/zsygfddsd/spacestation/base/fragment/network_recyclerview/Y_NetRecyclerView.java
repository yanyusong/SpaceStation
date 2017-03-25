package com.zsygfddsd.spacestation.base.fragment.network_recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsygfddsd.spacestation.R;
import com.zsygfddsd.spacestation.base.adapter.GeneralRecyclerViewHolder;
import com.zsygfddsd.spacestation.base.adapter.multirecycler.ItemEntityList;
import com.zsygfddsd.spacestation.base.adapter.multirecycler.MultiRecyclerAdapter;
import com.zsygfddsd.spacestation.base.adapter.multirecycler.OnBind;
import com.zsygfddsd.spacestation.common.widgets.Y_DividerItemDecoration;


/**
 * Created by mac on 15/12/19.
 * T: 是IBaseRecyclerViewPresenter
 * D: 是item的bean
 */
public abstract class Y_NetRecyclerView implements Y_I_NetRecyclerView {

    public static final String TAG = "YRecyFrag";

    protected SwipeRefreshLayout refreshView;
    protected RecyclerView recyclerView;
    protected MultiRecyclerAdapter adapter;
    protected ItemEntityList itemEntityList = new ItemEntityList();

    protected int itemLayoutId = android.R.layout.simple_list_item_1;// item的布局id,默认是只有一个textview
    protected int bottomItemLayoutId = android.R.layout.simple_list_item_1;// item的布局id,默认是只有一个textview

    protected boolean hasNextPage = true;//是否还有下一页数据

    private boolean canLoadMore = true;
    private int loadOffset = 2;//设置滚动到倒数第几个时开始加载下一页，默认是倒数第2个
    private LinearLayoutManager layoutManager;

    private RecyclerView.ItemDecoration itemDecoration = null;

    //lazy懒加载
    private boolean lazyLoad = false;//配置是否懒加载数据
    //先不做布局的懒加载了
    //    private boolean isFirstCreateView = true;//是否是第一次创建布局
    private boolean isFirstLoadData = true;//是否是第一次加载数据
    private boolean isViewCreated = false;//是否View已经创建好准备好接收数据了
    private boolean alwaysRefreshForPerVisible = false;

    private boolean isLoadDataFirstEnter = true;//第一次进来是否加载数据

    private Fragment fragment;
    private Context ct;

    private boolean isLoadingData = false;//是否正在加载数据了


    public Y_NetRecyclerView(Context ct, Fragment fragment) {
        this.ct = ct;
        this.fragment = fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        Bundle args = fragment.getArguments();
        if (args != null) {
            this.itemLayoutId = args.getInt(ITEM_LAYOUT_ID) == -1 ? android.R.layout.simple_list_item_1 : args.getInt(ITEM_LAYOUT_ID);
            this.lazyLoad = args.getBoolean(LAZY_LOAD, false);
            this.alwaysRefreshForPerVisible = args.getBoolean(ALWAYS_REFRESH_FOR_PER_VISIBLE, false);
            if (alwaysRefreshForPerVisible) {
                //若AlwaysRefreshForPerVisible=true,则必须同时使lazyLoad为true；
                lazyLoad = true;
                fragment.getArguments().putBoolean(LAZY_LOAD, true);
            }
        }
        this.isFirstLoadData = true;
        this.bottomItemLayoutId = getBottomViewLayoutId();
    }

    /**
     * 在onCreateView里边将除数据源以外的一些全局配置变量重置，初始化，避免detach以后，
     * 重新attach时生命周期留下无用的脏数据
     * viewpager中detach以后，重新attach是走如下生命周期，
     * onCreateView ---》 onResume。原理看：
     * http://blog.csdn.net/afanyusong/article/details/52934839
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.isViewCreated = false;

        //        this.isFirstCreateView = true;
        View view = null;
        //先不做布局的懒加载了
        //        if (!getUserVisibleHint() && isFirstCreateView && getPreCreateView() != null) {
        //            view = getPreCreateView();
        //        } else {
        view = initView(inflater, container, savedInstanceState);
        //        }
        Log.e(TAG, "***********************onCreateView=============");
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //        isFirstCreateView = false;
        this.isViewCreated = true;
        Log.e(TAG, "***********************onViewCreated=============");
    }

    public void onDestroyView() {
        this.isViewCreated = false;
    }

    /**
     * 在oncreateView之前执行的
     *
     * @param isVisibleToUser
     */
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG, "***********************getUserVisibleHint=============" + fragment.getUserVisibleHint());
        if (lazyLoad) {
            //            Log.e(TAG, "***********************lazyLoad=============" + true);
            lazyInitData(null);
        }
    }

    public void lazyInitData(Bundle savedInstanceState) {
        if (fragment.getUserVisibleHint() && alwaysRefreshForPerVisible) {
            if (!isFirstLoadData) {
                isFirstLoadData = true;
            }
        }

        if (!isFirstLoadData || !fragment.getUserVisibleHint() || !isViewCreated) {
            //不是第一次加载数据，即已经加载过数据了，不加载
            //界面不可见，即页面还没展示给用户的时候，不加载
            //没有承载数据的view时，即第一次进来或者onDetach以后，setUserVisibleHint时还没有oncreateview时，不加载
            Log.e(TAG, "***********************isFirstLoadData=" + isFirstLoadData + ",isViewCreated=" + isViewCreated + "=============" + "不加载数据");
            return;
        }

        initData(savedInstanceState);
        Log.e(TAG, "***********************isFirstLoadData=" + isFirstLoadData + ",isViewCreated=" + isViewCreated + "=============" + "初始化加载数据");
    }

    /**
     * 在viewpger和多fragment的配合使用中，对于复杂布局可以用此方法替换来加快第一次创建时的速度
     * 如果为null，则代表不设置预加载布局,
     * 通过是否返回null来设置是否进行布局View懒加载
     */
    //    public View getPreCreateView() {
    //        return null;
    //    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "***********************onActivityCreated=============");
        lazyInitData(savedInstanceState);
    }

    private View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.y_frag_com_recyclerview, null);
        refreshView = (SwipeRefreshLayout) view.findViewById(R.id.com_refreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.com_recyclerView);
        //        refreshView.setColorSchemeResources();
        //        recyclerView.setHasFixedSize(true);//如果item大小不会因为内容变化而变化，则设为true，提高绘制效率
        //                recyclerView.setLayoutManager(new LinearLayoutManager(ct, LinearLayout.VERTICAL, false));
        layoutManager = new LinearLayoutManager(ct);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration divider = getItemDecoration(ct);

        if (divider == null) {
            itemDecoration = new Y_DividerItemDecoration(ct, 1, 0xffEBEBF1) {
                @Override
                public boolean[] getItemSidesIsHaveOffsets(int itemPosition) {
                    boolean[] temp = {false, false, false, true};
                    //最后一个条目下边不要分割线
                    if (itemPosition == getItemEntityList().getItemCount() - 1) {
                        temp[3] = false;
                    }
                    return temp;
                }
            };
        } else {
            itemDecoration = divider;
        }
        recyclerView.addItemDecoration(itemDecoration);
        initRecyclerView(recyclerView);
        refreshView.setOnRefreshListener(this);
        adapter = new MultiRecyclerAdapter(ct, itemEntityList);
        //        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
        //            @Override
        //            public int getSpanSize(int position) {
        //                int viewType = adapter.getItemViewType(position);
        //                return viewType == itemLayoutId ? 1 : 4;
        //            }
        //        });
        recyclerView.setAdapter(adapter);

        canLoadMore = getCanLoadMore();
        if (canLoadMore) {

            final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            final int[] lastVisibleItemPos = new int[1];
            recyclerView.clearOnScrollListeners();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                                 @Override
                                                 public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                                     super.onScrollStateChanged(recyclerView, newState);

                                                     if (newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadingData) {
                                                         RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                                                         int lastVisibleItemPosition;
                                                         if (layoutManager instanceof GridLayoutManager) {
                                                             lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                                                         } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                                                             int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                                                             ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                                                             lastVisibleItemPosition = findMax(into);
                                                         } else {
                                                             lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                                                         }
                                                         if (layoutManager.getChildCount() > 0
                                                                 && lastVisibleItemPosition >= layoutManager.getItemCount() - 1 && layoutManager.getItemCount() > layoutManager.getChildCount() && isHasNextPage()) {
                                                             loadNextPage();
                                                         }
                                                     }

                                                 }

                                                 @Override
                                                 public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                                     super.onScrolled(recyclerView, dx, dy);
                                                     //                                                     if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
                                                     //                                                         lastVisibleItemPos[0] = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                                                     //                                                     }
                                                     //
                                                     //                                                     int totalCount = layoutManager.getItemCount();
                                                     //                                                     if (lastVisibleItemPos[0] == totalCount - 1) {
                                                     //                                                         if (isHasNextPage()) {
                                                     //                                                             //加载下一页
                                                     //                                                             loadNextPage();
                                                     //                                                         } else {
                                                     //
                                                     //                                                         }
                                                     //                                                     }
                                                 }
                                             }
            );

        }
        return view;
    }


    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public void setRefreshEnable(boolean enable) {
        refreshView.setEnabled(enable);
    }

    @Override
    public void setIsFirstLoadData(boolean isFirstLoadData) {
        this.isFirstLoadData = isFirstLoadData;
    }

    private void initData(Bundle savedInstanceState) {
        itemEntityList.clear();
        itemEntityList
                .addOnBind(itemLayoutId, new OnBind() {
                    @Override
                    public void onBindChildViewData(GeneralRecyclerViewHolder holder, Object itemData, int position) {
                        bindChildViewsData(holder, itemData, position);
                    }
                })
                .addOnBind(bottomItemLayoutId, new OnBind() {
                    @Override
                    public void onBindChildViewData(GeneralRecyclerViewHolder holder, Object itemData, int position) {
                        if (hasNextPage) {
                            holder.setText(R.id.item_bottom_text, "正在加载中...");
                        } else {
                            holder.setText(R.id.item_bottom_text, "您已滚动到最底部了");
                        }
                    }
                });

        if (isLoadDataFirstEnter) {
            loadFirstPage();
        }

    }

    /**
     * 设置是否可以加载更多，默认true可以加载，
     * 重写它改false，没有加载更多功能
     *
     * @return
     */
    protected boolean getCanLoadMore() {
        return true;
    }

    @Override
    public void onRefresh() {
        loadFirstPage();
    }

    @Override
    public void showRefreshIndication() {
        if (!refreshView.isRefreshing()) {
            refreshView.setRefreshing(true);
        }
    }

    @Override
    public void hideRefreshInfication() {
        if (refreshView.isRefreshing()) {
            refreshView.setRefreshing(false);
        }
    }

    @Override
    public void scrollToTop() {
        if (recyclerView != null) {
            recyclerView.scrollToPosition(0);
        }
    }

    //第一次进来的时候是否加载数据
    protected boolean getIsLoadDataFirstEnter() {
        return true;
    }

    /**
     * 若想改变RecyclerView的某些属性，只需重写此方法
     *
     * @param mRecyclerView 该fragment中默认的RecyclerView
     */
    public void initRecyclerView(RecyclerView mRecyclerView) {

    }

    /**
     * 是否还有下一页
     *
     * @return
     */
    public boolean isHasNextPage() {
        return hasNextPage;
    }

    /**
     * 设置是否还有下一页
     *
     * @param hasNext
     */
    @Override
    public void setHasNextPage(boolean hasNext) {
        this.hasNextPage = hasNext;
    }

    @Override
    public void updateData() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public ItemEntityList getItemEntityList() {
        return itemEntityList;
    }

    @Override
    public int getItemLayoutId() {
        return itemLayoutId;
    }

    @Override
    public int getBottomViewLayoutId() {
        return R.layout.y_item_recycler_bottom_view;
    }


    /**
     * 是否正在加载数据了
     */
    public void startLoadingData() {
        isLoadingData = true;
    }

    /**
     * 是否正在加载数据了
     *
     * @return
     */
    public boolean isLoadingData() {
        return isLoadingData;
    }

    /**
     * 是否正在加载数据了
     */
    public void completedLoadingData() {
        isLoadingData = false;
    }

    public abstract void loadFirstPage();

    public abstract void loadNextPage();


}
















