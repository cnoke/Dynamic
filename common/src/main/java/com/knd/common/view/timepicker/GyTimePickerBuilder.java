package com.knd.common.view.timepicker;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.knd.common.R;

import java.util.Calendar;

public class GyTimePickerBuilder {

    private TimePickerBuilder timePickerBuilder;
    private TimePickerView timePickerView;
    private boolean[] type = {true,true,true,true,true,false};

    //Required
    public GyTimePickerBuilder(Context context, OnTimeSelectListener listener) {
        timePickerBuilder = new TimePickerBuilder(context,listener).setLayoutRes(R.layout.common_pickerview_time, view -> {
            view.findViewById(R.id.ll_close).setOnClickListener(v -> {
                if(timePickerView != null){
                    timePickerView.dismiss();
                }
            });
            view.findViewById(R.id.btn_cancel).setOnClickListener(v -> {
                if(timePickerView != null){
                    timePickerView.dismiss();
                }
            });
            view.findViewById(R.id.btn_submit).setOnClickListener(v -> {
                if(timePickerView != null){
                    timePickerView.returnData();
                    timePickerView.dismiss();
                }

            });
            view.findViewById(R.id.ll_year).setVisibility(type[0] ? View.VISIBLE : View.GONE);
            view.findViewById(R.id.ll_month).setVisibility(type[1] ? View.VISIBLE : View.GONE);
            view.findViewById(R.id.ll_day).setVisibility(type[2] ? View.VISIBLE : View.GONE);
            view.findViewById(R.id.ll_hour).setVisibility(type[3] ? View.VISIBLE : View.GONE);
            view.findViewById(R.id.ll_min).setVisibility(type[4] ? View.VISIBLE : View.GONE);
            view.findViewById(R.id.ll_secondn).setVisibility(type[5] ? View.VISIBLE : View.GONE);

        }).isAlphaGradient(true)
                .setContentTextSize(21)
                .setLineSpacingMultiplier((float) 2)
                .setItemVisibleCount(5)
                .setDividerColor(0xFFF5F5F5)
                .setType(type)
                .isDialog(true)
                .setLabel("","","","","","");

    }

    public GyTimePickerBuilder setDate(Calendar date) {
        timePickerBuilder.setDate(date);
        return this;
    }

    public GyTimePickerBuilder setRangDate(Calendar startDate, Calendar endDate) {
        timePickerBuilder.setRangDate(startDate,endDate);
        return this;
    }

    public GyTimePickerBuilder setTitleText(String textContentTitle) {
        timePickerBuilder.setTitleText(textContentTitle);
        return this;
    }

    public GyTimePickerBuilder setType(boolean[] type) {
        this.type = type;
        timePickerBuilder.setType(type);
        return this;
    }

    public TimePickerView build() {
        timePickerView = timePickerBuilder.build();
        timePickerView.getDialogContainerLayout().setOnClickListener(v -> {

        });
        return timePickerView;
    }

}
