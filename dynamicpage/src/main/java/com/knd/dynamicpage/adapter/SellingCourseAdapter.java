package com.knd.dynamicpage.adapter;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.ElementBean;
import com.knd.common.route.DynamicRouteManager;
import com.knd.common.utils.ScreenUtils;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.ElementDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemSellingCourseBinding;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.SellingCourse)
public class SellingCourseAdapter extends ElementDelegateAdapter {

    public SellingCourseAdapter() {
        super(R.layout.dynamic_item_selling_course, DelegateViewType.SellingCourse);
    }

    @Override
    protected boolean loadMore() {
        return true;
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, ElementBean item) {
        DynamicItemSellingCourseBinding viewBinding = (DynamicItemSellingCourseBinding)holder.getDataBinding();
        viewBinding.tvName.setText(item.getElementName());
        viewBinding.tvContent.setText(item.getElementDetail());
        Glide.with(getContext()).load(item.getImageUrl()).transform(
                new RoundedCorners((int)ScreenUtils.dp2px(getContext()
                ,6.67f))).into(viewBinding.ivIcon);
        viewBinding.getRoot().setOnClickListener(v -> DynamicRouteManager.getInstance().route(item.getSkipUrl(),item.getParam()));
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }
}
