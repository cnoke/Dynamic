package com.knd.dynamicpage.adapter;

import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.ElementBean;
import com.knd.common.bean.FloorBean;
import com.knd.common.route.DynamicRouteManager;
import com.knd.common.utils.StringUtils;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemTitleBinding;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.TitleAdapter)
public class TitleAdapter extends FloorDelegateAdapter {

    public TitleAdapter() {
        super(R.layout.dynamic_item_title, DelegateViewType.TitleAdapter);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }


    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        if(item.getElementDtoList() == null){
            return;
        }
        DynamicItemTitleBinding viewBinding = (DynamicItemTitleBinding)holder.getDataBinding();
        viewBinding.vwTop.setVisibility(View.GONE);
        viewBinding.vwLeft.setVisibility(View.GONE);
        viewBinding.vwRight.setVisibility(View.GONE);
        viewBinding.tvRight.setVisibility(View.GONE);
        viewBinding.tvTitle.setVisibility(View.GONE);
        viewBinding.tvTitleContent.setVisibility(View.GONE);
        for(ElementBean bean : item.getElementDtoList()){
            if("top".equals(bean.getKeyValue())){
                viewBinding.vwTop.setVisibility(View.VISIBLE);
            }else if("right".equals(bean.getKeyValue())){
                viewBinding.vwRight.setVisibility(View.VISIBLE);
                viewBinding.tvRight.setVisibility(View.VISIBLE);
                viewBinding.tvRight.setText(bean.getElementName());
            }else if("left".equals(bean.getKeyValue())){
                viewBinding.vwLeft.setVisibility(View.VISIBLE);
            }
        }
        if(!StringUtils.isEmpty(item.getFloorName())
                || !StringUtils.isEmpty(item.getFloorDetail())){
            viewBinding.tvTitle.setVisibility(View.VISIBLE);
            viewBinding.tvTitleContent.setVisibility(View.VISIBLE);
            viewBinding.tvTitle.setText(item.getFloorName());
            viewBinding.tvTitleContent.setText(item.getFloorDetail());
        }

        viewBinding.getRoot().setOnClickListener(v -> DynamicRouteManager.getInstance().route(item.getSkipUrl(),item.getParam()));
    }
}

