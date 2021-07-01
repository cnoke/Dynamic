package com.knd.common.bean;

public class UpdateInfoBean {
    private String appType;//0:app  1:固件
    private String appVersion;
    private String apkUrl;
    private String forceFlag;//0不更新 1强制更新 2更新
    private String content;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getForceFlag() {
        return forceFlag;
    }

    public void setForceFlag(String forceFlag) {
        this.forceFlag = forceFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
