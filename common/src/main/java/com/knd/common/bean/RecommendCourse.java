package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.JSectionEntity;

import java.util.List;

public class RecommendCourse implements Parcelable {
    //视频课程Id
    private String id;
    //课程名称
    private String course;
    //训练课程标识
    private String trainingFlag;
    //课程时长（分钟）
    private String videoDurationMinutes;
    // 封面图片地址
    private String picAttachUrl;

    public RecommendCourse() {
    }

    protected RecommendCourse(Parcel in) {
        id = in.readString();
        course = in.readString();
        trainingFlag = in.readString();
        videoDurationMinutes = in.readString();
        picAttachUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(course);
        dest.writeString(trainingFlag);
        dest.writeString(videoDurationMinutes);
        dest.writeString(picAttachUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecommendCourse> CREATOR = new Creator<RecommendCourse>() {
        @Override
        public RecommendCourse createFromParcel(Parcel in) {
            return new RecommendCourse(in);
        }

        @Override
        public RecommendCourse[] newArray(int size) {
            return new RecommendCourse[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTrainingFlag() {
        return trainingFlag;
    }

    public void setTrainingFlag(String trainingFlag) {
        this.trainingFlag = trainingFlag;
    }

    public String getVideoDurationMinutes() {
        return videoDurationMinutes;
    }

    public void setVideoDurationMinutes(String videoDurationMinutes) {
        this.videoDurationMinutes = videoDurationMinutes;
    }

    public String getPicAttachUrl() {
        return picAttachUrl;
    }

    public void setPicAttachUrl(String picAttachUrl) {
        this.picAttachUrl = picAttachUrl;
    }
}
