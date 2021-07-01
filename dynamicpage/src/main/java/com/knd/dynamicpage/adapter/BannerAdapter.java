package com.knd.dynamicpage.adapter;

import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.activity.BaseActivity;
import com.knd.common.bean.ElementBean;
import com.knd.common.bean.FloorBean;
import com.knd.common.route.DynamicRouteManager;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemBannerBinding;
import com.knd.dynamicpage.utils.DelegateViewType;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

@Adapter(DelegateViewType.BANNER)
public class BannerAdapter extends FloorDelegateAdapter {

    private DynamicItemBannerBinding viewBinding;

    public BannerAdapter() {
        super(R.layout.dynamic_item_banner,DelegateViewType.BANNER);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        viewBinding = (DynamicItemBannerBinding)holder.getDataBinding();
        BannerImageAdapter<ElementBean> adapter = new BannerImageAdapter<ElementBean>(item.getElementDtoList()) {
            @Override
            public void onBindView(BannerImageHolder holder, ElementBean data, int position, int size) {
                //图片加载自己实现
                Glide.with(getContext())
                        .load(data.getImageUrl())
                        .into(holder.imageView);
            }
        };
        adapter.setOnBannerListener((OnBannerListener<ElementBean>) (data, position) ->
                DynamicRouteManager.getInstance().route(data.getSkipUrl(),data.getParam()));
        viewBinding.banner.setAdapter(adapter).addBannerLifecycleObserver((BaseActivity)getContext())//添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()));
        viewBinding.banner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(viewBinding != null && viewBinding.banner != null){
            viewBinding.banner.destroy();
        }
    }
}
