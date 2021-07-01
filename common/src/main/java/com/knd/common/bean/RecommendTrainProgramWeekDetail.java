package com.knd.common.bean;

import java.util.ArrayList;
import java.util.List;

public class RecommendTrainProgramWeekDetail {

    /**
     * 周训练日期不能为空
     */
    private Integer weekDayName;

    private List<RecommendTrainProgramDayItem> trainCourseList = new ArrayList<>();

    public Integer getWeekDayName() {
        return weekDayName;
    }

    public void setWeekDayName(Integer weekDayName) {
        this.weekDayName = weekDayName;
    }

    public List<RecommendTrainProgramDayItem> getTrainCourseList() {
        return trainCourseList;
    }

    public void setTrainCourseList(List<RecommendTrainProgramDayItem> trainCourseList) {
        this.trainCourseList = trainCourseList;
    }
}
