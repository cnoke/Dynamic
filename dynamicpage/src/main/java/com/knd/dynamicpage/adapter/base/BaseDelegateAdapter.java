package com.knd.dynamicpage.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.dynamicpage.adapter.DynamicBind;
import com.knd.dynamicpage.inter.OnItemChildClickListener;
import com.knd.dynamicpage.inter.OnItemClickListener;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public abstract class BaseDelegateAdapter<T> extends DelegateAdapter.Adapter<BaseDataBindingHolder>
        implements LifecycleObserver {

    private Context mContext;
    private int layoutId;
    private List<T> data = new ArrayList<>();
    private int viewTypeItem;
    private int count = -1;
    private OnItemClickListener mOnItemClickListener;
    private OnItemChildClickListener mOnItemChildClickListener;
    private boolean loadMore;

    public BaseDelegateAdapter(@LayoutRes int layoutId, @NonNull String viewType){
        this(layoutId,-1,viewType);
    }

    /**
     *
     * @param layoutId 布局ID
     * @param count 截取网络数据个数 -1不截取
     * @param viewType view类型
     */
    public BaseDelegateAdapter(@LayoutRes int layoutId, int count,@NonNull String viewType){
        this.layoutId = layoutId;
        this.count = count;
        this.viewTypeItem = DynamicBind.getIndex(viewType);
        loadMore = loadMore();
    }

    /**
     * 支持加载更多
     * 需要支持加载更多的adapter需要重写此方法返回true
     * @return
     */
    protected boolean loadMore() {
        return false;
    }

    @NonNull
    @Override
    public BaseDataBindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if(mContext != null && mContext instanceof LifecycleOwner){
            ((LifecycleOwner) mContext).getLifecycle().addObserver(this);
        }
        BaseDataBindingHolder viewHolder = new BaseDataBindingHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        bindViewClickListener(viewHolder, viewType);
        onItemViewHolderCreated(viewHolder, viewType);
        return viewHolder;
    }


    public void bindViewClickListener(BaseDataBindingHolder viewHolder, int viewType){
        viewHolder.itemView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) {
                return;
            }
            setOnItemClick(v, position);
        });
        for (int id : getChildClickViewIds()) {
            View childView = viewHolder.itemView.findViewById(id);
            if(childView != null){
                if (!childView.isClickable()) {
                    childView.setClickable(true);
                }
                childView.setOnClickListener(v -> {
                    int position = viewHolder.getAdapterPosition();
                    if (position == RecyclerView.NO_POSITION) {
                        return;
                    }
                    setOnItemChildClick(v, position);
                });
            }
        }
    }


    /**
     * 用于保存需要设置点击事件的 item
     */
    private LinkedHashSet childClickViewIds = new LinkedHashSet<Integer>();

    public LinkedHashSet<Integer> getChildClickViewIds() {
        return childClickViewIds;
    }

    /**
     * 设置需要点击事件的子view
     * @param viewIds IntArray
     */
    public void addChildClickViewIds(@IdRes int... viewIds) {
        for (int viewId : viewIds) {
            childClickViewIds.add(viewId);
        }
    }

    public void setOnItemChildClick(View v, int position){
        if(mOnItemChildClickListener == null){
            return;
        }
        mOnItemChildClickListener.onItemChildClick(this, v, position);
    }

    /**
     * override this method if you want to override click event logic
     *
     * 如果你想重新实现 item 点击事件逻辑，请重写此方法
     * @param v
     * @param position
     */
    protected void setOnItemClick(View v,int position ) {
        if(mOnItemClickListener == null){
            return;
        }
        mOnItemClickListener.onItemClick(this, v, position);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseDataBindingHolder holder, int position) {
        convert(holder,getItem(position));
    }

    public T getItem(int position){
        if(data.isEmpty()){
            return null;
        }
        return data.get(position);
    }

    public int getItemPosition(T item){
        return item != null && !data.isEmpty() ? data.indexOf(item) : -1;
    }
    public List<T> getData(){
        return data;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * 实现此方法，并使用 holder 完成 item 视图的操作
     *
     * @param holder A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(BaseDataBindingHolder holder,T item );

    /**
     * （可选重写）当 item 的 ViewHolder创建完毕后，执行此方法。
     * 可在此对 ViewHolder 进行处理，例如进行 DataBinding 绑定 view
     *
     * @param viewHolder VH
     * @param viewType Int
     */
    protected void onItemViewHolderCreated(BaseDataBindingHolder viewHolder, int viewType){

    }

    /**
     * 必须重写不然会出现滑动不流畅的情况
     */
    @Override
    public int getItemViewType(int position) {
        return viewTypeItem;
    }

    /**
     * 条目数量
     * @return          条目数量
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mContext = recyclerView.getContext();
    }

    public Context getContext() {
        return mContext;
    }

    /************************************** Set Listener ****************************************/
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        this.mOnItemChildClickListener = listener;
    }

    /*************************** 设置数据相关 ******************************************/

    /**
     * setting up a new instance to data;
     * 设置新的数据实例，替换原有内存引用。
     * 通常情况下，如非必要，请使用[setList]修改内容
     *
     * @param list
     */
    public void setNewInstance(List<T> list) {
        if (list == this.data
                || list == null) {
            return;
        }

        this.data = list;
        notifyDataSetChanged();
    }

    public void addData(T item) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.add(item);
        notifyDataSetChanged();
    }

    public void addList(List<T> list) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    public void resetData(T item) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }else{
            this.data.clear();
        }
        this.data.add(item);
        notifyDataSetChanged();
    }

    /**
     * 使用新的数据集合，改变原有数据集合内容。
     * 注意：不会替换原有的内存引用，只是替换内容
     *
     * @param list List<T>
     */
    public void setList(List<T> list) {
        if (list != this.data) {
            this.data.clear();
            if (list != null && !list.isEmpty()) {
                this.data.addAll(list);
            }
        } else {
            if (list != null && !list.isEmpty()) {
                List<T> newList = new ArrayList(list);
                this.data.clear();
                this.data.addAll(newList);
            } else {
                this.data.clear();
            }
        }
        notifyDataSetChanged();
    }

    public boolean isLoadMore() {
        return loadMore;
    }

    public void setLoadMore(boolean loadMore) {
        this.loadMore = loadMore;
    }

    public int getCount() {
        return count;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
    }
}
