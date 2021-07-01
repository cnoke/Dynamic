package com.knd.common.bean;

import java.util.ArrayList;
import java.util.List;

public class TrainProgramWeekDetailRequest {

    /**
     * 周训练日期不能为空
     */
    private Integer weekDayName;

    private List<TrainProgramDayItemRequest> trainProgramDayItemRequests = new ArrayList<>();

    public Integer getWeekDayName() {
        return weekDayName;
    }

    public void setWeekDayName(Integer weekDayName) {
        this.weekDayName = weekDayName;
    }

    public List<TrainProgramDayItemRequest> getTrainProgramDayItemRequests() {
        return trainProgramDayItemRequests;
    }

    public void setTrainProgramDayItemRequests(List<TrainProgramDayItemRequest> trainProgramDayItemRequests) {
        this.trainProgramDayItemRequests = trainProgramDayItemRequests;
    }
}
