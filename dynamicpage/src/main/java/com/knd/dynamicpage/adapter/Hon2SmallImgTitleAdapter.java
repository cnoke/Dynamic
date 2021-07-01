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
import com.knd.dynamicpage.itemAdapter.Hon2SmallImgContentAdapter;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.Hon2SmallImgTitle)
public class Hon2SmallImgTitleAdapter extends FloorDelegateAdapter {

    public Hon2SmallImgTitleAdapter() {
        super(R.layout.dynamic_item_hon_course,2,DelegateViewType.Hon2SmallImgTitle);
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
        Hon2SmallImgContentAdapter adapter = new Hon2SmallImgContentAdapter(item.getElementDtoList());

        adapter.setOnItemClickListener((adapter1, view, position) -> DynamicRouteManager.getInstance().route(item.getElementDtoList().get(position).getSkipUrl(),item.getElementDtoList().get(position).getParam()));

        viewBinding.recycleCourse.setAdapter(adapter);
    }

}
