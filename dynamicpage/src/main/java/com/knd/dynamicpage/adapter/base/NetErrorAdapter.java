package com.knd.dynamicpage.adapter.base;

import android.graphics.Color;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.FloorBean;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.NET_ERROR)
public class NetErrorAdapter extends FloorDelegateAdapter{

    public NetErrorAdapter() {
        super(R.layout.dynamic_item_net_error, DelegateViewType.NET_ERROR);
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
        layoutHelper.setBgColor(Color.parseColor("#FFFFFF"));
        return layoutHelper;
    }
}
