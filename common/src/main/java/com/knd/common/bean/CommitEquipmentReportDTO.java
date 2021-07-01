package com.knd.common.bean;

public class CommitEquipmentReportDTO {
    private String equipmentNo;
    private String turnOnTime;
    private String hardVersion;
    private String mainboardVersion;
    private String appVersion;
    private String positionInfo;

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public String getTurnOnTime() {
        return turnOnTime;
    }

    public void setTurnOnTime(String turnOnTime) {
        this.turnOnTime = turnOnTime;
    }

    public String getHardVersion() {
        return hardVersion;
    }

    public void setHardVersion(String hardVersion) {
        this.hardVersion = hardVersion;
    }

    public String getMainboardVersion() {
        return mainboardVersion;
    }

    public void setMainboardVersion(String mainboardVersion) {
        this.mainboardVersion = mainboardVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getPositionInfo() {
        return positionInfo;
    }

    public void setPositionInfo(String positionInfo) {
        this.positionInfo = positionInfo;
    }
}
