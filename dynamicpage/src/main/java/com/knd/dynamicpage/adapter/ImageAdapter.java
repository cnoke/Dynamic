package com.knd.dynamicpage.adapter;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.ElementBean;
import com.knd.common.route.DynamicRouteManager;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.ElementDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemImageBinding;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.ImageAdapter)
public class ImageAdapter extends ElementDelegateAdapter {

    public ImageAdapter() {
        super(R.layout.dynamic_item_image, DelegateViewType.ImageAdapter);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, ElementBean item) {
        DynamicItemImageBinding viewBinding = (DynamicItemImageBinding)holder.getDataBinding();
        Glide.with(getContext()).load(item.getImageUrl()).into(viewBinding.image);
        viewBinding.getRoot().setOnClickListener(v -> DynamicRouteManager.getInstance().route(item.getSkipUrl(),item.getParam()));
    }

}
