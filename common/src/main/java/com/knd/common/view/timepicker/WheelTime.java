package com.knd.common.view.timepicker;

import android.view.View;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.knd.common.R;
import com.bigkoo.pickerview.adapter.NumericWheelAdapter;
import com.bigkoo.pickerview.listener.ISelectTimeCallback;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class WheelTime {

    private View view;
    private WheelView wv_year;
    private WheelView wv_week;
    private WheelView wv_day;
    private int gravity;

    private boolean[] type;
    private static final int DEFAULT_START_YEAR = 1900;
    private static final int DEFAULT_END_YEAR = 2100;

    private int startYear = DEFAULT_START_YEAR;
    private int endYear = DEFAULT_END_YEAR;
    private int currentYear;

    private int textSize;
    List<Week> weeklist;

    private ISelectTimeCallback mSelectChangeCallback;

    public WheelTime(View view, boolean[] type, int gravity, int textSize) {
        super();
        this.view = view;
        this.type = type;
        this.gravity = gravity;
        this.textSize = textSize;
    }

    public void setPicker(int year, Calendar week) {

        currentYear = year;
        // 年
        wv_year = (WheelView) view.findViewById(R.id.year);
        wv_year.setAdapter(new NumericWheelAdapter(startYear, endYear));// 设置"年"的显示数据


        wv_year.setCurrentItem(year - startYear);// 初始化时显示的数据
        wv_year.setGravity(gravity);
        // 周
        wv_week = (WheelView) view.findViewById(R.id.week);
        weeklist = WeekHelper.getWeeksByYear(currentYear);
        int current = WeekHelper.getWeeksByYear(weeklist,week.getTime());
        wv_week.setAdapter(new ArrayWheelAdapter(weeklist));// 设置"周"的显示数据
        wv_week.setCurrentItem(current);// 初始化时显示的数据
        wv_week.setGravity(gravity);

        weeklist = WeekHelper.getWeeksByYear(currentYear,1);
        wv_day = (WheelView) view.findViewById(R.id.day);
        wv_day.setAdapter(new ArrayWheelAdapter(weeklist));// 设置"日"的显示数据
        wv_day.setCurrentItem(current);// 初始化时显示的数据
        wv_day.setGravity(gravity);

        // 添加"年"监听
        wv_year.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                int year_num = index + startYear;
                currentYear = year_num;
                int currentMonthItem = wv_week.getCurrentItem();//记录上一次的item位置
                weeklist = WeekHelper.getWeeksByYear(currentYear);
                int size = weeklist.size();
                wv_week.setAdapter(new ArrayWheelAdapter(weeklist));// 设置"周"的显示数据
                if(currentMonthItem < size){
                    wv_week.setCurrentItem(currentMonthItem);
                }else{
                    wv_week.setCurrentItem(0);
                }

                weeklist = WeekHelper.getWeeksByYear(currentYear,1);
                wv_day.setAdapter(new ArrayWheelAdapter(weeklist));// 设置"周"的显示数据
                if(currentMonthItem < size){
                    wv_day.setCurrentItem(currentMonthItem);
                }else{
                    wv_day.setCurrentItem(0);
                }

                if (mSelectChangeCallback != null) {
                    mSelectChangeCallback.onTimeSelectChanged();
                }
            }
        });


        // 添加"周"监听
        wv_week.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                wv_day.setCurrentItem(index);
                if (mSelectChangeCallback != null) {
                    mSelectChangeCallback.onTimeSelectChanged();
                }
            }
        });

        wv_day.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                wv_week.setCurrentItem(index);
                if (mSelectChangeCallback != null) {
                    mSelectChangeCallback.onTimeSelectChanged();
                }
            }
        });

        setChangedListener(wv_week);
        setChangedListener(wv_day);

        if (type.length != 6) {
            throw new IllegalArgumentException("type[] length is not 6");
        }
        wv_year.setVisibility(type[0] ? View.VISIBLE : View.GONE);
        wv_week.setVisibility(type[1] ? View.VISIBLE : View.GONE);
        wv_day.setVisibility(type[2] ? View.VISIBLE : View.GONE);
        setContentTextSize();
    }

    private void setChangedListener(WheelView wheelView) {
        if (mSelectChangeCallback != null) {
            wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int index) {
                    mSelectChangeCallback.onTimeSelectChanged();
                }
            });
        }

    }

    private void setContentTextSize() {
        wv_week.setTextSize(textSize);
        wv_year.setTextSize(textSize);
        wv_day.setTextSize(textSize);
    }


    public void setLabels(String label_year, String label_week) {

        if (label_year != null) {
            wv_year.setLabel(label_year);
        } else {
            wv_year.setLabel(view.getContext().getString(R.string.pickerview_year));
        }
        if (label_week != null) {
            wv_week.setLabel(label_week);
        } else {
            wv_week.setLabel("周");
        }
        if (label_week != null) {
            wv_day.setLabel(label_week);
        } else {
            wv_day.setLabel("日");
        }
    }

    public void setTextXOffset(int x_offset_year, int x_offset_week) {
        wv_year.setTextXOffset(x_offset_year);
        wv_week.setTextXOffset(x_offset_week);
        wv_day.setTextXOffset(x_offset_week);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        wv_year.setCyclic(cyclic);
        wv_week.setCyclic(cyclic);
        wv_day.setCyclic(cyclic);
    }

    public Date getWeekBeginDate() {
        if(weeklist == null || weeklist.isEmpty()){
            return null;
        }
        return weeklist.get(wv_week.getCurrentItem()).getWeekBeginDate();
    }

    public Date getWeekEndDate() {
        if(weeklist == null || weeklist.isEmpty()){
            return null;
        }
        return weeklist.get(wv_week.getCurrentItem()).getWeekEndDate();
    }

    public View getView() {
        return view;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }


    public void setRangDate(Calendar startDate, Calendar endDate) {

        if (startDate == null && endDate != null) {
            int year = endDate.get(Calendar.YEAR);

            if (year < startYear) {
                this.endYear = startYear;
            } else{
                this.endYear = year;
            }

        } else if (startDate != null && endDate == null) {
            int year = startDate.get(Calendar.YEAR);
            if (year > endYear) {
                this.startYear = endYear;
            } else {
                this.startYear = year;
            }
        } else if (startDate != null && endDate != null) {
            this.startYear = startDate.get(Calendar.YEAR);
            this.endYear = endDate.get(Calendar.YEAR);
        }

    }

    /**
     * 设置间距倍数,但是只能在1.0-4.0f之间
     *
     * @param lineSpacingMultiplier
     */
    public void setLineSpacingMultiplier(float lineSpacingMultiplier) {
        wv_week.setLineSpacingMultiplier(lineSpacingMultiplier);
        wv_year.setLineSpacingMultiplier(lineSpacingMultiplier);
        wv_day.setLineSpacingMultiplier(lineSpacingMultiplier);
    }

    /**
     * 设置分割线的颜色
     *
     * @param dividerColor
     */
    public void setDividerColor(int dividerColor) {
        wv_week.setDividerColor(dividerColor);
        wv_year.setDividerColor(dividerColor);
        wv_day.setDividerColor(dividerColor);
    }

    /**
     * 设置分割线的类型
     *
     * @param dividerType
     */
    public void setDividerType(WheelView.DividerType dividerType) {
        wv_week.setDividerType(dividerType);
        wv_year.setDividerType(dividerType);
        wv_day.setDividerType(dividerType);
    }

    /**
     * 设置分割线之间的文字的颜色
     *
     * @param textColorCenter
     */
    public void setTextColorCenter(int textColorCenter) {
        wv_week.setTextColorCenter(textColorCenter);
        wv_year.setTextColorCenter(textColorCenter);
        wv_day.setTextColorCenter(textColorCenter);
    }

    /**
     * 设置分割线以外文字的颜色
     *
     * @param textColorOut
     */
    public void setTextColorOut(int textColorOut) {
        wv_week.setTextColorOut(textColorOut);
        wv_year.setTextColorOut(textColorOut);
        wv_day.setTextColorOut(textColorOut);
    }

    /**
     * @param isCenterLabel 是否只显示中间选中项的
     */
    public void isCenterLabel(boolean isCenterLabel) {
        wv_week.isCenterLabel(isCenterLabel);
        wv_year.isCenterLabel(isCenterLabel);
        wv_day.isCenterLabel(isCenterLabel);
    }

    public void setSelectChangeCallback(ISelectTimeCallback mSelectChangeCallback) {
        this.mSelectChangeCallback = mSelectChangeCallback;
    }

    public void setItemsVisible(int itemsVisibleCount) {
        wv_week.setItemsVisibleCount(itemsVisibleCount);
        wv_year.setItemsVisibleCount(itemsVisibleCount);
        wv_day.setItemsVisibleCount(itemsVisibleCount);
    }

    public void setAlphaGradient(boolean isAlphaGradient) {
        wv_week.setAlphaGradient(isAlphaGradient);
        wv_year.setAlphaGradient(isAlphaGradient);
        wv_day.setAlphaGradient(isAlphaGradient);
    }
}
