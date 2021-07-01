package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ActionBean  implements Parcelable {
    private String actionId;
    private String action;
    private String countMode;
    private String aimDuration;
    private String aimTimes;
    private String videoAttachUrl;
    private String picAttachUrl;
    private boolean isChecked = false;
    //下载百分比
    private int percent;
    //下载状态
    private int state;
    private List<CommonBean> targetList;
    private List<CommonBean> partList;

    public ActionBean(Parcel in) {
        actionId = in.readString();
        action = in.readString();
        countMode = in.readString();
        aimDuration = in.readString();
        aimTimes = in.readString();
        videoAttachUrl = in.readString();
        picAttachUrl = in.readString();
        partList = in.createTypedArrayList(CommonBean.CREATOR);
    }

    public ActionBean() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(actionId);
        dest.writeString(action);
        dest.writeString(countMode);
        dest.writeString(aimDuration);
        dest.writeString(aimTimes);
        dest.writeString(videoAttachUrl);
        dest.writeString(picAttachUrl);
        dest.writeTypedList(partList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ActionBean> CREATOR = new Creator<ActionBean>() {
        @Override
        public ActionBean createFromParcel(Parcel in) {
            return new ActionBean(in);
        }

        @Override
        public ActionBean[] newArray(int size) {
            return new ActionBean[size];
        }
    };

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCountMode() {
        return countMode;
    }

    public void setCountMode(String countMode) {
        this.countMode = countMode;
    }

    public String getAimDuration() {
        return aimDuration;
    }

    public void setAimDuration(String aimDuration) {
        this.aimDuration = aimDuration;
    }

    public String getAimTimes() {
        return aimTimes;
    }

    public void setAimTimes(String aimTimes) {
        this.aimTimes = aimTimes;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ActionBean{" +
                "actionId='" + actionId + '\'' +
                ", action='" + action + '\'' +
                ", countMode='" + countMode + '\'' +
                ", aimDuration='" + aimDuration + '\'' +
                ", aimTimes='" + aimTimes + '\'' +
                ", videoAttachUrl='" + videoAttachUrl + '\'' +
                ", picAttachUrl='" + picAttachUrl + '\'' +
                ", targetList=" + targetList +
                ", partList=" + partList +
                '}';
    }
}
