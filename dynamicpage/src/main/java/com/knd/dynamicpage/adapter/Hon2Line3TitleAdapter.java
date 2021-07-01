package com.knd.dynamicpage.adapter;

import androidx.recyclerview.widget.GridLayoutManager;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.FloorBean;
import com.knd.common.route.DynamicRouteManager;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemHonCourseBinding;
import com.knd.dynamicpage.itemAdapter.Hon2Line3ContentAdapter;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.Hon2Line3)
public class Hon2Line3TitleAdapter extends FloorDelegateAdapter {

    public Hon2Line3TitleAdapter() {
        super(R.layout.dynamic_item_hon_course,6,DelegateViewType.Hon2Line3);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        DynamicItemHonCourseBinding viewBinding = (DynamicItemHonCourseBinding)holder.getDataBinding();
        viewBinding.tvType.setText(item.getFloorName());
        viewBinding.tvTypeLly.setOnClickListener(view -> {
            DynamicRouteManager.getInstance().route(item.getSkipUrl(),item.getParam());
        });
        viewBinding.recycleCourse.setLayoutManager(new GridLayoutManager(getContext(),2));
        Hon2Line3ContentAdapter adapter = new Hon2Line3ContentAdapter(item.getElementDtoList());

        adapter.setOnItemClickListener((adapter1, view, position) -> DynamicRouteManager.getInstance().route(item.getElementDtoList().get(position).getSkipUrl(),item.getElementDtoList().get(position).getParam()));

        viewBinding.recycleCourse.setAdapter(adapter);
    }

}
