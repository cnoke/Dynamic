package com.knd.dynamicpage.bean;

import androidx.annotation.ColorInt;

public class DayContent {

    private String start;
    private String end;
    private String headUrl;
    private String name;
    private String title;
    private String leader;
    private String detail;
    private String right;
    private @ColorInt int rightColor = 0xFF25C688;
    private @ColorInt int rightTextColor = 0xFFFFFFFF;
    private String skipUrl;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public @ColorInt int getRightColor() {
        return rightColor;
    }

    public void setRightColor(@ColorInt int rightColor) {
        this.rightColor = rightColor;
    }

    public @ColorInt int getRightTextColor() {
        return rightTextColor;
    }

    public void setRightTextColor(@ColorInt int rightTextColor) {
        this.rightTextColor = rightTextColor;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }
}
