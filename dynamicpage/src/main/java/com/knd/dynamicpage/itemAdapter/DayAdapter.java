package com.knd.dynamicpage.itemAdapter;

import android.graphics.Color;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.databinding.DynamicCustomDayBinding;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class DayAdapter extends BaseQuickAdapter<Date, BaseDataBindingHolder> {

    // 当前时间
    private Date now = new Date();
    private HashMap<Date, String> markData;
    private Date select = now;

    public DayAdapter() {
        super(R.layout.dynamic_custom_day);
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder baseDataBindingHolder, Date date) {
        DynamicCustomDayBinding viewBinding = (DynamicCustomDayBinding)baseDataBindingHolder.getDataBinding();

        viewBinding.maker.setVisibility(VISIBLE);

        if (isSameDay(select,date)) {
            viewBinding.date.setTextColor(Color.parseColor("#FFFFFF"));
            viewBinding.maker.setEnabled(true);
            viewBinding.selectedBackground.setVisibility(VISIBLE);
            viewBinding.tvMarkNub.setBackgroundResource(R.drawable.dynamic_bg_ffeaea_12dp);
            viewBinding.tvMarkNub.setTextColor(Color.parseColor("#B45591"));
        } else {
            viewBinding.date.setTextColor(Color.parseColor("#BFBFBF"));
            viewBinding.maker.setEnabled(false);
            viewBinding.selectedBackground.setVisibility(GONE);
            viewBinding.tvMarkNub.setBackgroundResource(R.drawable.dynamic_bg_f5f5f5_12dp);
            viewBinding.tvMarkNub.setTextColor(Color.parseColor("#9F9D9D"));
        }

        if(isSameDay(now,date)){
            viewBinding.date.setText("今");
            viewBinding.todayBackground.setVisibility(VISIBLE);
        }else{
            viewBinding.date.setText(date.getDate() + "");
            viewBinding.todayBackground.setVisibility(GONE);
        }

        viewBinding.maker.setVisibility(GONE);
        if(markData != null){
            String dayPlan = markData.get(date);
            if(dayPlan != null){
                viewBinding.maker.setVisibility(VISIBLE);
                viewBinding.tvMarkNub.setText(dayPlan);
                viewBinding.tvMarkNub.setVisibility(VISIBLE);
            }
        }
    }

    public void setMarkData(HashMap<Date, String> markData) {
        this.markData = markData;
    }

    public void setSelect(Date select){
        if(select == null){
            return;
        }
        this.select = select;
    }

    private boolean isSameDay(Date date1 , Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}
