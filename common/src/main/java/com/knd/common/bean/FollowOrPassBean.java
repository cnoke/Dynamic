package com.knd.common.bean;

public class FollowOrPassBean {

    /**
     * 关注操作 关注 FOLLOW,取关 PASS,回关 BACK_FOLLOW,移除 DELETE
     */
    private String followOperation;
    /**
     * 关注用户ID
     */
    private String targetUserId;
    /**
     * 用户id
     */
    private String userId;

    public String getFollowOperation() {
        return followOperation;
    }

    public void setFollowOperation(String followOperation) {
        this.followOperation = followOperation;
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
