package com.knd.base.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.module.BaseUpFetchModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.module.UpFetchModule;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 提供下拉刷新以及加载更多
 */
public abstract class CommonAdapter<T,VH> extends BaseQuickAdapter implements LoadMoreModule, UpFetchModule {

    private BaseLoadMoreModule mLoadMoreModule;
    private BaseUpFetchModule mUpFetchModule;

    public CommonAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        initLoadMore();
        initUpFetch();
    }

    private void initLoadMore(){
        mLoadMoreModule=getLoadMoreModule();
        mLoadMoreModule.setLoadMoreView(new SimpleLoadMoreView());
    }

    private void initUpFetch(){
        mUpFetchModule=getUpFetchModule();
        mUpFetchModule.setUpFetchEnable(true);
    }
}
