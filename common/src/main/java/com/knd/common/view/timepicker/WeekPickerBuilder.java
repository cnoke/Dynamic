package com.knd.common.view.timepicker;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.CustomListener;
import com.knd.common.R;

public class WeekPickerBuilder implements CustomListener {

    private final WeekPickerView.OnTimeSelectChangeListener listener;
    private PickerOptions mPickerOptions;
    private WeekPickerView weekPicker;

    //Required
    public WeekPickerBuilder(Context context, WeekPickerView.OnTimeSelectChangeListener listener) {
        this.listener = listener;
        mPickerOptions = new PickerOptions(PickerOptions.TYPE_PICKER_TIME);
        mPickerOptions.context = context;
        mPickerOptions.isDialog = true;
        mPickerOptions.textSizeContent = 21;
        mPickerOptions.lineSpacingMultiplier = (float) 2;
        mPickerOptions.itemsVisibleCount = 5;
        mPickerOptions.dividerColor = 0xFFF5F5F5;
        mPickerOptions.label_year = "";
        mPickerOptions.label_month = "";
        mPickerOptions.label_day = "";
        mPickerOptions.label_hours = "";
        mPickerOptions.label_minutes = "";
        mPickerOptions.label_seconds = "";
        mPickerOptions.layoutRes = R.layout.common_pickerview_week;
        mPickerOptions.customListener = this;
    }

    //Option
    public WeekPickerBuilder setGravity(int gravity) {
        mPickerOptions.textGravity = gravity;
        return this;
    }


    public WeekPickerView build() {
        weekPicker = new WeekPickerView(mPickerOptions);
        weekPicker.getDialogContainerLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        weekPicker.setOnListenter(listener);
        return weekPicker;
    }

    @Override
    public void customLayout(View view) {
        view.findViewById(R.id.ll_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weekPicker != null){
                    weekPicker.dismiss();
                }
            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weekPicker != null){
                    weekPicker.dismiss();
                }
            }
        });
        view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weekPicker != null){
                    weekPicker.returnData();
                    weekPicker.dismiss();
                }

            }
        });
    }
}
