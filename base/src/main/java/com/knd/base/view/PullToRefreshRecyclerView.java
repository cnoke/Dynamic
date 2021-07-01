package com.knd.base.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.knd.base.R;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

public class PullToRefreshRecyclerView extends FrameLayout {

    private SmartRefreshLayout mSmartRefreshLayout;
    private RecyclerView mRecyclerView;
    private LoadListener mLoadListener;
    private RefreshListener refreshListener;
    private LoadMoreListener loadMoreListener;

    public PullToRefreshRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public PullToRefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.base_view_pull_to_refresh,this,true);
        mSmartRefreshLayout=findViewById(R.id.refreshLayout);
        mRecyclerView=findViewById(R.id.recyclerView);
        mSmartRefreshLayout.setEnableRefresh(false);
        mSmartRefreshLayout.setEnableLoadMore(false);
        mSmartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        mSmartRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setAnimatingColor(0xFFBE5491));
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if(mLoadListener!=null){
                mLoadListener.onRefresh();
            }
            if(refreshListener != null){
                refreshListener.onRefresh();
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if(mLoadListener!=null){
                mLoadListener.onLoadmore();
            }
            if(loadMoreListener != null){
                loadMoreListener.onLoadMore();
            }
        });
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmLoadListener(LoadListener mLoadListener) {
        this.mLoadListener = mLoadListener;
        mSmartRefreshLayout.setEnableRefresh(true);
        mSmartRefreshLayout.setEnableLoadMore(true);
    }

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
        mSmartRefreshLayout.setEnableRefresh(true);
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
        mSmartRefreshLayout.setEnableLoadMore(true);
    }

    public SmartRefreshLayout getmSmartRefreshLayout() {
        return mSmartRefreshLayout;
    }

    public void addItemDecoration(@NonNull RecyclerView.ItemDecoration decor){
        mRecyclerView.addItemDecoration(decor);
    }

    public void setLayoutManager(@Nullable RecyclerView.LayoutManager layout){
        mRecyclerView.setLayoutManager(layout);
    }

    public void setAdapter(@Nullable RecyclerView.Adapter adapter){
        mRecyclerView.setAdapter(adapter);
    }

    public void setSupportsChangeAnimations(boolean supportsChangeAnimations){
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(supportsChangeAnimations);
    }

    public RefreshLayout finishRefresh() {
        return mSmartRefreshLayout.finishRefresh();
    }

    public RefreshLayout finishLoadMore() {
        return mSmartRefreshLayout.finishLoadMore();
    }

    public RefreshLayout finishLoadMore(boolean success) {
        return mSmartRefreshLayout.finishLoadMore(success);
    }

    public RefreshLayout finishLoadMoreWithNoMoreData() {
        return mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    public interface LoadListener{
        void onRefresh();
        void onLoadmore();
    }

    public interface RefreshListener{
        void onRefresh();
    }

    public interface LoadMoreListener{
        void onLoadMore();
    }
}
