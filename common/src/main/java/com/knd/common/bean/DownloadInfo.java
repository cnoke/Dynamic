package com.knd.common.bean;

import java.util.List;

public class DownloadInfo {
    //组合
    public static final int COMBINATION = 0;
    //课程
    public static final int COURSE = 1;
    //动作
    public static final int ACTION = 2;
    private String id;
    //类型
    private int type;
    //图片连接
    private String imageUrl;
    //视频连接
    private List<String> videoUrls;
    //时长
    private String totalDuration;
    //名称
    private String name;
    //详情
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public List<String> getVideoUrls() {
        return videoUrls;
    }

    public void setVideoUrls(List<String> videoUrls) {
        this.videoUrls = videoUrls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
