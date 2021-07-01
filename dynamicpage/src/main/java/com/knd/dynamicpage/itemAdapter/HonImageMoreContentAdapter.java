package com.knd.dynamicpage.itemAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.ElementBean;
import com.knd.common.route.DynamicRouteManager;
import com.knd.common.utils.ScreenUtils;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.databinding.DynamicItem435200ImageBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HonImageMoreContentAdapter extends BaseQuickAdapter<ElementBean, BaseDataBindingHolder> {

    public HonImageMoreContentAdapter(List<ElementBean> elementDtoList) {
        super(R.layout.dynamic_item_435_200_image,elementDtoList);
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder baseDataBindingHolder, ElementBean item) {
        DynamicItem435200ImageBinding viewBinding = (DynamicItem435200ImageBinding)baseDataBindingHolder.getDataBinding();
        Glide.with(getContext()).load(item.getImageUrl()).transform(
                new RoundedCorners((int) ScreenUtils.dp2px(getContext()
                        ,6.67f))).into(viewBinding.image);
        viewBinding.getRoot().setOnClickListener(v -> DynamicRouteManager.getInstance().route(item.getSkipUrl(),item.getParam()));
    }
}
