package com.knd.common.bean;

import java.io.Serializable;

public class PowerTestBean implements Serializable {
    private BaseAction userTestActionDto;
    private BasePowerStandardUse basePowerStandardUse;

    public PowerTestBean(){
    }

    public BaseAction getBaseAction() {
        return userTestActionDto;
    }

    public void setBaseAction(BaseAction baseAction) {
        this.userTestActionDto = baseAction;
    }

    public BasePowerStandardUse getBasePowerStandardUse() {
        return basePowerStandardUse;
    }

    public void setBasePowerStandardUse(BasePowerStandardUse basePowerStandardUse) {
        this.basePowerStandardUse = basePowerStandardUse;
    }

    public class BaseAction implements Serializable {
        private String actionId;
        private String actionName;
        private String actionTypeId;
        private String actionTypeName;
        private String videoUrl;
        private String picUrl;

        public BaseAction() {
        }

        public String getActionId() {
            return actionId;
        }

        public void setActionId(String actionId) {
            this.actionId = actionId;
        }

        public String getActionName() {
            return actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        public String getActionTypeId() {
            return actionTypeId;
        }

        public void setActionTypeId(String actionTypeId) {
            this.actionTypeId = actionTypeId;
        }

        public String getActionTypeName() {
            return actionTypeName;
        }

        public void setActionTypeName(String actionTypeName) {
            this.actionTypeName = actionTypeName;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        @Override
        public String toString() {
            return "BaseAction{" +
                    "actionId='" + actionId + '\'' +
                    ", actionName='" + actionName + '\'' +
                    ", actionTypeId='" + actionTypeId + '\'' +
                    ", actionTypeName='" + actionTypeName + '\'' +
                    ", videoUrl='" + videoUrl + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    '}';
        }
    }

    public class BasePowerStandardUse implements Serializable {
        private String elementary;//一级
        private String novice;// 二级
        private String intermediate;// 三级
        private String senior;// 四级
        private String elite;// 五级

        public BasePowerStandardUse() {
        }

        public String getElementary() {
            return elementary;
        }

        public void setElementary(String elementary) {
            this.elementary = elementary;
        }

        public String getNovice() {
            return novice;
        }

        public void setNovice(String novice) {
            this.novice = novice;
        }

        public String getIntermediate() {
            return intermediate;
        }

        public void setIntermediate(String intermediate) {
            this.intermediate = intermediate;
        }

        public String getSenior() {
            return senior;
        }

        public void setSenior(String senior) {
            this.senior = senior;
        }

        public String getElite() {
            return elite;
        }

        public void setElite(String elite) {
            this.elite = elite;
        }

        @Override
        public String toString() {
            return "BasePowerStandardUse{" +
                    "elementary='" + elementary + '\'' +
                    ", novice='" + novice + '\'' +
                    ", intermediate='" + intermediate + '\'' +
                    ", senior='" + senior + '\'' +
                    ", elite='" + elite + '\'' +
                    '}';
        }
    }
}
