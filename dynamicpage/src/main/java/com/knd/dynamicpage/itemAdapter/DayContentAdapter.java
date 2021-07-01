package com.knd.dynamicpage.itemAdapter;

import android.content.res.ColorStateList;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.bean.DayContent;
import com.knd.dynamicpage.databinding.DynamicItemDayContentBinding;

import org.jetbrains.annotations.NotNull;


public class DayContentAdapter extends BaseQuickAdapter<DayContent, BaseDataBindingHolder> {

    public DayContentAdapter() {
        super(R.layout.dynamic_item_day_content);
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder baseDataBindingHolder, DayContent dayContent) {
        DynamicItemDayContentBinding viewBinding = (DynamicItemDayContentBinding)baseDataBindingHolder.getDataBinding();
        viewBinding.tvStart.setText(dayContent.getStart());
        viewBinding.tvEnd.setText(dayContent.getEnd());
        viewBinding.avImg.setData(dayContent.getName(),dayContent.getHeadUrl());
        viewBinding.tvName.setText(dayContent.getTitle());
        viewBinding.tvLeader.setText(dayContent.getLeader());
        viewBinding.tvDetail.setText(dayContent.getDetail());
        viewBinding.tvAdd.setText(dayContent.getRight());
        viewBinding.tvAdd.setTextColor(dayContent.getRightTextColor());
        viewBinding.tvAdd.setBackgroundTintList(ColorStateList.valueOf(dayContent.getRightColor()));
    }
}
