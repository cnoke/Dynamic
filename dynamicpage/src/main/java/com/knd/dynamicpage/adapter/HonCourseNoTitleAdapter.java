package com.knd.dynamicpage.adapter;

import androidx.recyclerview.widget.GridLayoutManager;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.FloorBean;
import com.knd.common.route.DynamicRouteManager;
import com.knd.common.utils.ScreenUtils;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.ModuleApp;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemRecycleviewBinding;
import com.knd.dynamicpage.itemAdapter.HonCourseContentAdapter;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.HonCourseNoTitle)
public class HonCourseNoTitleAdapter extends FloorDelegateAdapter {

    public HonCourseNoTitleAdapter() {
        super(R.layout.dynamic_item_recycleview,3,DelegateViewType.HonCourseNoTitle);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
        int dp26 = (int)ScreenUtils.dp2px(ModuleApp.getInstance().appContext,26.67f);
        layoutHelper.setPaddingLeft(dp26);
        layoutHelper.setPaddingRight(dp26);
        return layoutHelper;
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        DynamicItemRecycleviewBinding viewBinding = (DynamicItemRecycleviewBinding)holder.getDataBinding();
        viewBinding.recycleCourse.setLayoutManager(new GridLayoutManager(getContext(),3));
        HonCourseContentAdapter adapter = new HonCourseContentAdapter(item.getElementDtoList());

        adapter.setOnItemClickListener((adapter1, view, position) -> DynamicRouteManager.getInstance().route(item.getElementDtoList().get(position).getSkipUrl(),item.getElementDtoList().get(position).getParam()));

        viewBinding.recycleCourse.setAdapter(adapter);
    }

}
