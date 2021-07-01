package com.knd.dynamicpage.dto;

import com.knd.common.bean.RecommendTrainProgramWeekDetail;

import java.util.List;

public class RecommendTrainProgramDto {

    /**
     * 计划名称
     */
    private String programName;
    /**
     * 计划开始时间不能为空，格式为yyyy-MM-dd
     */
    private String beginTime;
    /**
     * 循环周数不能为空
     */
    private Integer trainWeekNum;
    private List<RecommendTrainProgramWeekDetail> trainProgramWeekDetail;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getTrainWeekNum() {
        return trainWeekNum;
    }

    public void setTrainWeekNum(Integer trainWeekNum) {
        this.trainWeekNum = trainWeekNum;
    }

    public List<RecommendTrainProgramWeekDetail> getTrainProgramWeekDetail() {
        return trainProgramWeekDetail;
    }

    public void setTrainProgramWeekDetail(List<RecommendTrainProgramWeekDetail> trainProgramWeekDetail) {
        this.trainProgramWeekDetail = trainProgramWeekDetail;
    }
}
