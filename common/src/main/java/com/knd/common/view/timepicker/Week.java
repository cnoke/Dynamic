package com.knd.common.view.timepicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Week {

    private String weekNum;
    private int beginYear;
    private int endYear;
    // 周开始日期 格式: MM.dd
    private String weekBegin;
    // 周结束日期 格式: MM.dd
    private String weekEnd;
    private int type = 0;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

    /**
     * 获取周开始日期 (yyyy.MM.dd)
     * @return
     */
    public Date getWeekBeginDate() {
        String strDate = beginYear + "." + weekBegin;
        Date date = new Date();
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取周结束日期 (yyyy.MM.dd)
     * @return
     */
    public Date getWeekEndDate() {
        String strDate = endYear + "." + weekEnd;
        Date date = new Date();
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public int getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(int beginYear) {
        this.beginYear = beginYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getWeekBegin() {
        return weekBegin;
    }

    public void setWeekBegin(String weekBegin) {
        this.weekBegin = weekBegin;
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if(type == 0){
            return "第" + weekNum + "周";
        }else{
            return weekBegin + "~" + weekEnd;
        }
    }

    public String getSelectWeekBeginAndEnd() {
        return beginYear + "/" + weekBegin + " ~ "
                + endYear + "/" + weekEnd + "(第" + weekNum + "周)";
    }
}
