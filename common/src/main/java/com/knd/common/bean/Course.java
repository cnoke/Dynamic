package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.JSectionEntity;

import java.util.List;

public class Course  extends JSectionEntity implements Parcelable {
    private boolean isHeader; //
    //视频课程Id
    private String courseId;
    //课程名称
    private String course;
    //训练课程标识
    private String trainingFlag;
    //介绍视频地址
    private String videoAttachUrl;
    // 封面图片地址
    private String picAttachUrl;

    private String videoDurationMinutes;
    private String videoDurationSeconds;


    private List<BlockBean> blockList;
    private String remark;
    private String fitCrowdRemark;
    private String unFitCrowdRemark;
    private String trainCourseHeadInfoId;
    private String videoDuration;
    private String vedioBeginTime;

    private List<CommonBean> typeList;
    private List<CommonBean> targetList;
    private List<CommonBean> partList;

    public Course() {
    }

    protected Course(Parcel in) {
        isHeader = in.readByte() != 0;
        courseId = in.readString();
        course = in.readString();
        trainingFlag = in.readString();
        videoAttachUrl = in.readString();
        picAttachUrl = in.readString();
        videoDurationMinutes = in.readString();
        videoDurationSeconds = in.readString();
        blockList = in.createTypedArrayList(BlockBean.CREATOR);
        remark = in.readString();
        fitCrowdRemark = in.readString();
        unFitCrowdRemark = in.readString();
        trainCourseHeadInfoId = in.readString();
        videoDuration = in.readString();
        vedioBeginTime = in.readString();
        partList = in.createTypedArrayList(CommonBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isHeader ? 1 : 0));
        dest.writeString(courseId);
        dest.writeString(course);
        dest.writeString(trainingFlag);
        dest.writeString(videoAttachUrl);
        dest.writeString(picAttachUrl);
        dest.writeString(videoDurationMinutes);
        dest.writeString(videoDurationSeconds);
        dest.writeTypedList(blockList);
        dest.writeString(remark);
        dest.writeString(fitCrowdRemark);
        dest.writeString(unFitCrowdRemark);
        dest.writeString(trainCourseHeadInfoId);
        dest.writeString(videoDuration);
        dest.writeString(vedioBeginTime);
        dest.writeTypedList(partList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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

    public String getVideoAttachUrl() {
        return videoAttachUrl;
    }

    public void setVideoAttachUrl(String videoAttachUrl) {
        this.videoAttachUrl = videoAttachUrl;
    }

    public String getPicAttachUrl() {
        return picAttachUrl;
    }

    public void setPicAttachUrl(String picAttachUrl) {
        this.picAttachUrl = picAttachUrl;
    }

    public String getVideoDurationMinutes() {
        return videoDurationMinutes;
    }

    public void setVideoDurationMinutes(String videoDurationMinutes) {
        this.videoDurationMinutes = videoDurationMinutes;
    }

    public String getVideoDurationSeconds() {
        return videoDurationSeconds;
    }

    public void setVideoDurationSeconds(String videoDurationSeconds) {
        this.videoDurationSeconds = videoDurationSeconds;
    }

    public List<BlockBean> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<BlockBean> blockList) {
        this.blockList = blockList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFitCrowdRemark() {
        return fitCrowdRemark;
    }

    public void setFitCrowdRemark(String fitCrowdRemark) {
        this.fitCrowdRemark = fitCrowdRemark;
    }

    public String getUnFitCrowdRemark() {
        return unFitCrowdRemark;
    }

    public void setUnFitCrowdRemark(String unFitCrowdRemark) {
        this.unFitCrowdRemark = unFitCrowdRemark;
    }

    public String getTrainCourseHeadInfoId() {
        return trainCourseHeadInfoId;
    }

    public void setTrainCourseHeadInfoId(String trainCourseHeadInfoId) {
        this.trainCourseHeadInfoId = trainCourseHeadInfoId;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVedioBeginTime() {
        return vedioBeginTime;
    }

    public void setVedioBeginTime(String vedioBeginTime) {
        this.vedioBeginTime = vedioBeginTime;
    }

    public List<CommonBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<CommonBean> typeList) {
        this.typeList = typeList;
    }

    public List<CommonBean> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<CommonBean> targetList) {
        this.targetList = targetList;
    }

    public List<CommonBean> getPartList() {
        return partList;
    }

    public void setPartList(List<CommonBean> partList) {
        this.partList = partList;
    }

    public static Creator<Course> getCREATOR() {
        return CREATOR;
    }

    @Override
    public boolean isHeader() {
        return false;
    }
}
