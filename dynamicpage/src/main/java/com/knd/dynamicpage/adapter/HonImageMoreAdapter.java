package com.knd.dynamicpage.adapter;

import android.graphics.Color;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.arjinmc.recyclerviewdecoration.RecyclerViewLinearItemDecoration;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.FloorBean;
import com.knd.common.route.DynamicRouteManager;
import com.knd.common.utils.ScreenUtils;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.ModuleApp;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemRecycleviewBinding;
import com.knd.dynamicpage.itemAdapter.HonImageMoreContentAdapter;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.HonImageMore)
public class HonImageMoreAdapter extends FloorDelegateAdapter {

    public HonImageMoreAdapter() {
        super(R.layout.dynamic_item_recycleview, DelegateViewType.HonImageMore);
    }

    @Override
    protected void onItemViewHolderCreated(BaseDataBindingHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);
        DynamicItemRecycleviewBinding viewBinding = (DynamicItemRecycleviewBinding)viewHolder.getDataBinding();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        viewBinding.recycleCourse.setLayoutManager(layoutManager);
        viewBinding.recycleCourse.addItemDecoration(
                new RecyclerViewLinearItemDecoration.Builder(getContext())
                        .thickness((int)ScreenUtils.dp2px(getContext(),26f))
                        .color(Color.TRANSPARENT)
                        .firstLineVisible(true)
                        .lastLineVisible(true)
                        .create());
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        DynamicItemRecycleviewBinding viewBinding = (DynamicItemRecycleviewBinding)holder.getDataBinding();
        HonImageMoreContentAdapter adapter  = new HonImageMoreContentAdapter(item.getElementDtoList());
        adapter.setOnItemClickListener((adapter1, view, position) -> DynamicRouteManager.getInstance().route(item.getElementDtoList().get(position).getSkipUrl(),item.getElementDtoList().get(position).getParam()));
        viewBinding.recycleCourse.setAdapter(adapter);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }
}
