package com.knd.dynamicpage.adapter;

import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.ElementBean;
import com.knd.common.route.DynamicRouteManager;
import com.knd.common.utils.ScreenUtils;
import com.knd.common.utils.StringUtils;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.ModuleApp;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.ElementDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemVerImageTextBinding;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.GridHon4Adapter)
public class GridHon4Adapter extends ElementDelegateAdapter {

    public GridHon4Adapter() {
        super(R.layout.dynamic_item_ver_image_text, DelegateViewType.GridHon4Adapter);
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, ElementBean item) {
        DynamicItemVerImageTextBinding viewBinding = (DynamicItemVerImageTextBinding)holder.getDataBinding();
        Glide.with(getContext()).load(item.getImageUrl()).into(viewBinding.image);
        if(!StringUtils.isEmpty(item.getElementName())){
            viewBinding.tvTitle.setText(item.getElementName());
            viewBinding.tvTitle.setVisibility(View.VISIBLE);
        }else{
            viewBinding.tvTitle.setVisibility(View.GONE);
        }
        viewBinding.getRoot().setOnClickListener(v -> DynamicRouteManager.getInstance().route(item.getSkipUrl(),item.getParam()));
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        int dp30 = (int)ScreenUtils.dp2px(ModuleApp.getInstance().appContext,30f);
        GridLayoutHelper layoutHelper = new GridLayoutHelper(4);
        layoutHelper.setMarginTop(dp30);
        layoutHelper.setMarginBottom(dp30);
        layoutHelper.setWeights(new float[]{25f,25f,25f,25f});
        layoutHelper.setVGap(dp30);
        layoutHelper.setAutoExpand(true);
        return layoutHelper;
    }
}
