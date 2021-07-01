package com.knd.common.bean;

import java.util.List;

public class OtherUserDto {
    /**
     * 粉丝数量
     */
    private int fansNum;
    /**
     * 关注数
     */
    private int followNum;
    /**
     * 关注状态 [ 为关注 NOT_FOLLOW,已关注 IN_FOLLOW,被关注 BE_FOLLOW,好友 FRIEND ]
     */
    private String followStatus;
    /**
     * 是否是vip 0 否 1是
     */
    private int isVip;

    private List<String> labelList;
    /**
     * 点赞数
     */
    private int thumbupNum;

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public String getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(String followStatus) {
        this.followStatus = followStatus;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public List<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }

    public int getThumbupNum() {
        return thumbupNum;
    }

    public void setThumbupNum(int thumbupNum) {
        this.thumbupNum = thumbupNum;
    }
}
