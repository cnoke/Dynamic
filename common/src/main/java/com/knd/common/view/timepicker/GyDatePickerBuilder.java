package com.knd.common.view.timepicker;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.knd.common.R;

import java.util.Calendar;

public class GyDatePickerBuilder {

    private TimePickerBuilder timePickerBuilder;
    private TimePickerView timePickerView;
    private boolean[] type;

    //Required
    public GyDatePickerBuilder(Context context, OnTimeSelectListener listener) {
        timePickerBuilder = new TimePickerBuilder(context,listener).setLayoutRes(R.layout.common_pickerview_date, view -> {
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
            if(type != null && !type[2]){
                view.findViewById(R.id.ll_day).setVisibility(View.GONE);
            }
        }).isAlphaGradient(true)
                .setContentTextSize(21)
                .setLineSpacingMultiplier((float) 2)
                .setItemVisibleCount(5)
                .setDividerColor(0xFFF5F5F5)
                .isDialog(true)
                .setLabel("","","","","","");

    }

    public GyDatePickerBuilder setDate(Calendar date) {
        timePickerBuilder.setDate(date);
        return this;
    }

    public GyDatePickerBuilder setRangDate(Calendar startDate, Calendar endDate) {
        timePickerBuilder.setRangDate(startDate,endDate);
        return this;
    }

    public GyDatePickerBuilder setTitleText(String textContentTitle) {
        timePickerBuilder.setTitleText(textContentTitle);
        return this;
    }

    public GyDatePickerBuilder setType(boolean[] type) {
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
