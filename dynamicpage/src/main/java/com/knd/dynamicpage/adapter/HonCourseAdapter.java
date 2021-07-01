package com.knd.dynamicpage.adapter;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.FloorBean;
import com.knd.common.route.DynamicRouteManager;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemHonCourseBinding;
import com.knd.dynamicpage.itemAdapter.HonCourseContentAdapter;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.HonCourse)
public class HonCourseAdapter extends FloorDelegateAdapter {

    public HonCourseAdapter() {
        super(R.layout.dynamic_item_hon_course,3,DelegateViewType.HonCourse);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        DynamicItemHonCourseBinding viewBinding = (DynamicItemHonCourseBinding)holder.getDataBinding();
        viewBinding.tvType.setText(item.getFloorName());
        if (TextUtils.isEmpty(item.getSkipUrl())){
            viewBinding.ivItemHomeMore.setVisibility(View.INVISIBLE);
        }else {
            viewBinding.ivItemHomeMore.setVisibility(View.VISIBLE);
            viewBinding.tvTypeLly.setOnClickListener(view -> {
                DynamicRouteManager.getInstance().route(item.getSkipUrl(),item.getParam());
            });
        }
        viewBinding.recycleCourse.setLayoutManager(new GridLayoutManager(getContext(),3));
        HonCourseContentAdapter adapter = new HonCourseContentAdapter(item.getElementDtoList());

        adapter.setOnItemClickListener((adapter1, view, position) -> DynamicRouteManager.getInstance().route(item.getElementDtoList().get(position).getSkipUrl(),item.getElementDtoList().get(position).getParam()));

        viewBinding.recycleCourse.setAdapter(adapter);
    }

}
