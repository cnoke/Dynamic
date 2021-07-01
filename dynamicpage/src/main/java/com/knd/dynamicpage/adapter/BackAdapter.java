package com.knd.dynamicpage.adapter;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.FloorBean;
import com.knd.common.utils.ScreenUtils;
import com.knd.common.utils.StringUtils;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.ModuleApp;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemBackBinding;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.BackAdapter)
public class BackAdapter extends FloorDelegateAdapter {

    public BackAdapter() {
        super(R.layout.dynamic_item_back, DelegateViewType.BackAdapter);
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        DynamicItemBackBinding viewBinding = (DynamicItemBackBinding)holder.getDataBinding();
        if(!StringUtils.isEmpty(item.getImageUrl())){
            Glide.with(getContext()).load(item.getImageUrl()).into(viewBinding.ivButton);
        }
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.BOTTOM_RIGHT
                , 0
                ,(int)ScreenUtils.dp2px(ModuleApp.getInstance().appContext,78));
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);
        return scrollFixLayoutHelper;
    }
}
