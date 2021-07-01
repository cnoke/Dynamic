package com.knd.dynamicpage.adapter.home;

import android.graphics.Color;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.FloorBean;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemHomePowerBinding;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.POWER_VIEW)
public class HomePowerAdapter extends FloorDelegateAdapter{
    private DynamicItemHomePowerBinding viewBinding;

    public HomePowerAdapter() {
        super(R.layout.dynamic_item_home_power,DelegateViewType.POWER_VIEW);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
        layoutHelper.setBgColor(Color.parseColor("#FFFFFF"));
        return layoutHelper;
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        viewBinding = (DynamicItemHomePowerBinding)holder.getDataBinding();
    }

    public void setState(boolean isAddingPower, boolean b){
        viewBinding.powerView.setState(isAddingPower,b);
    }

    public void setValue(int realTimeValue, boolean isPowerLimited){
        viewBinding.powerView.setValue(realTimeValue, isPowerLimited);
    }

    public void setPowerNoChange(int baseP, int conP, int backP, boolean isAuto){
        viewBinding.powerView.setPowerNoChange(baseP,conP,backP,isAuto);
    }

}
