package com.zsygfddsd.spacestation.base.module.network_recyclerview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.zsygfddsd.spacestation.base.module.network.Y_BaseNetFragment;
import com.zsygfddsd.spacestation.common.widgets.DividerGridItemDecoration;


/**
 * Created by mac on 15/12/19.
 * T: 是IBaseRecyclerViewPresenter
 * D: 是item的bean
 */
public abstract class Y_BaseRecyclerViewNetFragment<T extends Y_BasePageContract.IBaseRecyclerViewPresenter> extends Y_BaseNetFragment<T> implements Y_BasePageContract.IBaseRecyclerView<T>, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "YRecyFrag";

    protected static final String ITEM_LAYOUT_ID = "itemLayoutId";

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
    private boolean isLazyLoad = false;//配置是否懒加载数据
    //先不做布局的懒加载了
    //    private boolean isFirstCreateView = true;//是否是第一次创建布局
    private boolean isFirstLoadData = true;//是否是第一次加载数据
    private boolean isViewCreated = false;//是否View已经创建好准备好接收数据了

    protected Bundle data2Bundle(int itemLayoutId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ITEM_LAYOUT_ID, itemLayoutId);
        return bundle;
    }

    protected void init(int itemLayoutId) {
        Bundle bundle = data2Bundle(itemLayoutId);
        setArguments(bundle);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e(TAG, "***********************onAttach=============");
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "***********************onCreate=============");
        Bundle args = getArguments();
        if (args != null) {
            this.itemLayoutId = args.getInt(ITEM_LAYOUT_ID) == -1 ? android.R.layout.simple_list_item_1 : args.getInt(ITEM_LAYOUT_ID);
        }
        this.bottomItemLayoutId = getBottomViewLayoutId();
        this.isLazyLoad = getIsLazyLoad();
        this.isFirstLoadData = true;
    }

    public boolean getIsLazyLoad() {
        return false;
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
    @Override
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //        isFirstCreateView = false;
        this.isViewCreated = true;
        Log.e(TAG, "***********************onViewCreated=============");
    }

    /**
     * 在oncreateView之前执行的
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, "***********************getUserVisibleHint=============" + getUserVisibleHint());
        if (isLazyLoad) {
            //            Log.e(TAG, "***********************isLazyLoad=============" + true);
            lazyInitData(null);
        }
    }

    public void lazyInitData(Bundle savedInstanceState) {
        if (!isFirstLoadData || !getUserVisibleHint() || !isViewCreated) {
            //不是第一次加载数据，即已经加载过数据了，不加载
            //界面不可见，即页面还没展示给用户的时候，不加载
            //没有承载数据的view时，即第一次进来或者onDetach以后，setUserVisibleHint时还没有oncreateview时，不加载
            Log.e(TAG, "***********************isFirstLoadData=" + isFirstLoadData + ",isViewCreated=" + isViewCreated + "=============" + "不加载数据");
            return;
        }
        initData(savedInstanceState);
        Log.e(TAG, "***********************isFirstLoadData=" + isFirstLoadData + ",isViewCreated=" + isViewCreated + "=============" + "初始化加载数据");
        isFirstLoadData = false;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "***********************onStart=============");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "***********************onResume=============");
    }


    /**
     * 在viewpger和多fragment的配合使用中，对于复杂布局可以用此方法替换来加快第一次创建时的速度
     * 如果为null，则代表不设置预加载布局,
     * 通过是否返回null来设置是否进行布局View懒加载
     */
    //    public View getPreCreateView() {
    //        return null;
    //    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
            itemDecoration = new DividerGridItemDecoration(ct, 1, 0xffEBEBF1) {
                @Override
                public boolean[] getItemSidesIsHaveOffsets(int itemPosition) {

                    boolean[] temp = {false, false, false, true};

                    return temp;
                }
            }.configLastItemShowDivider(false);
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
                                                 }

                                                 @Override
                                                 public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                                     super.onScrolled(recyclerView, dx, dy);
                                                     if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
                                                         lastVisibleItemPos[0] = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                                                     }

                                                     int totalCount = layoutManager.getItemCount();
                                                     if (lastVisibleItemPos[0] == totalCount - 1) {
                                                         if (isHasNextPage()) {
                                                             //加载下一页
                                                             onLoadMore();
                                                         } else {

                                                         }
                                                     }
                                                 }
                                             }
            );

        }
        return view;
    }

    protected RecyclerView.ItemDecoration getItemDecoration(Context ct) {

        return null;
    }

    public void setRefreshEnable(boolean enable) {
        refreshView.setEnabled(enable);
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

        onInitData();


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
        onLoadRefresh();
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
     * 第一页的数据加载
     */
    public void onInitData() {
        mPresenter.onInitData();
    }

    /**
     * 加载更多
     */
    public void onLoadMore() {
        mPresenter.onLoadMore();
    }

    /**
     * 下拉刷新
     */
    public void onLoadRefresh() {
        mPresenter.onLoadRefresh();
    }

    /**
     * 给Item布局的各个控件设置分配好的数据
     *
     * @param holder   item的holder，利用getChildView(eg:控件id)的方法得到该控件
     * @param itemData 封装好的分配给该item的数据，数据一般为Hashmap<K,V>或者Modle等类型
     * @param position 当前item的position
     */
    public abstract void bindChildViewsData(GeneralRecyclerViewHolder holder, Object itemData, int position);


}
















