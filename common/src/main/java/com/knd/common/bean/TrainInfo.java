package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TrainInfo implements Parcelable {

    /**
     * 训练类型1课程 2自由训练
     */
    private String trainType;
    /**
     *记录ID
     */
    private String trainReportId;
    /**
     * 训练名称
     */
    private String trainName;
    /**
     * 训练时间
     */
    private String trainTime;
    /**
     * 训练时长
     */
    private String actualTrainSeconds;
    /**
     * 训练总力
     */
    private String finishTotalPower;

    protected TrainInfo(Parcel in) {
        trainType = in.readString();
        trainReportId = in.readString();
        trainName = in.readString();
        trainTime = in.readString();
        actualTrainSeconds = in.readString();
        finishTotalPower = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trainType);
        dest.writeString(trainReportId);
        dest.writeString(trainName);
        dest.writeString(trainTime);
        dest.writeString(actualTrainSeconds);
        dest.writeString(finishTotalPower);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TrainInfo> CREATOR = new Creator<TrainInfo>() {
        @Override
        public TrainInfo createFromParcel(Parcel in) {
            return new TrainInfo(in);
        }

        @Override
        public TrainInfo[] newArray(int size) {
            return new TrainInfo[size];
        }
    };

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainReportId() {
        return trainReportId;
    }

    public void setTrainReportId(String trainReportId) {
        this.trainReportId = trainReportId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public String getActualTrainSeconds() {
        return actualTrainSeconds;
    }

    public void setActualTrainSeconds(String actualTrainSeconds) {
        this.actualTrainSeconds = actualTrainSeconds;
    }

    public String getFinishTotalPower() {
        return finishTotalPower;
    }

    public void setFinishTotalPower(String finishTotalPower) {
        this.finishTotalPower = finishTotalPower;
    }

    public static Creator<TrainInfo> getCREATOR() {
        return CREATOR;
    }
}
